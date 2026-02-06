package com.Banco.CajerosService.Filter;

import com.Banco.CajerosService.Service.JwtService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    public JwtAuthFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    /**
     * Valida el JWT y carga el SecurityContext con ROLE_<ROL>. Solo excluye los
     * endpoints de login para permitir autenticación inicial sin token.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        String path = request.getServletPath();
        String method = request.getMethod();

        boolean isLogin
                = "POST".equalsIgnoreCase(method)
                && ("/auth/login".equals(path)
                || "/auth/login/tarjeta".equals(path)
                || "/auth/login/email".equals(path));

        if (isLogin) {
            filterChain.doFilter(request, response);
            return;
        }

        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (header == null || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = header.substring(7).trim();
        if (token.isEmpty()) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            Claims claims = jwtService.parseClaims(token);

            Long usuarioId = claims.get("usuarioId", Long.class);
            Long cuentaId = claims.get("cuentaId", Long.class);
            String role = claims.get("role", String.class);
            String nombre = claims.get("nombre", String.class);

            if (usuarioId == null || role == null || role.isBlank()) {
                filterChain.doFilter(request, response);
                return;
            }

            SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + role);

            UsernamePasswordAuthenticationToken auth
                    = new UsernamePasswordAuthenticationToken(
                            String.valueOf(usuarioId),
                            null,
                            List.of(authority)
                    );

            Map<String, Object> details = new HashMap<>();
            details.put("usuarioId", usuarioId);
            details.put("cuentaId", cuentaId);
            details.put("role", role);
            details.put("nombre", nombre);
            auth.setDetails(details);

            SecurityContextHolder.getContext().setAuthentication(auth);

        } catch (Exception ex) {
            SecurityContextHolder.clearContext();
        }

        filterChain.doFilter(request, response);
    }
}
