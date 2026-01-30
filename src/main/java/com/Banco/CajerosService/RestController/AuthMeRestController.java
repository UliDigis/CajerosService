package com.Banco.CajerosService.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthMeRestController {

    /**
     * Endpoint de verificación del contexto de seguridad. Retorna principal y
     * authorities resueltas desde el SecurityContext.
     */
    @GetMapping("/me")
    public Map<String, Object> me(Authentication auth) {

        Map<String, Object> res = new HashMap<>();

        if (auth == null) {
            res.put("authenticated", false);
            res.put("principal", null);
            res.put("authorities", List.of());
            return res;
        }

        List<String> roles = auth.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        res.put("authenticated", auth.isAuthenticated());
        res.put("principal", auth.getName());
        res.put("authorities", roles);

        return res;
    }
}
