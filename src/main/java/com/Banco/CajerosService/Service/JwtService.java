package com.Banco.CajerosService.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

    private final Key key;
    private final long expirationMinutes;
    private final String issuer;

    public JwtService(
            @Value("${security.jwt.secret}") String secret,
            @Value("${security.jwt.expiration-minutes}") long expirationMinutes,
            @Value("${security.jwt.issuer}") String issuer
    ) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.expirationMinutes = expirationMinutes;
        this.issuer = issuer;
    }

    public String generateToken(Long usuarioId, String role, Long cuentaId) {
        long now = System.currentTimeMillis();
        Date issuedAt = new Date(now);
        Date exp = new Date(now + (expirationMinutes * 60_000));

        Map<String, Object> claims = new java.util.HashMap<>();
        claims.put("usuarioId", usuarioId);
        if (cuentaId != null) {
            claims.put("cuentaId", cuentaId);
        }
        claims.put("role", role);

        return Jwts.builder()
                .setIssuer(issuer)
                .setSubject(String.valueOf(usuarioId))
                .setIssuedAt(issuedAt)
                .setExpiration(exp)
                .addClaims(claims)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public Claims parseClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .requireIssuer(issuer)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
