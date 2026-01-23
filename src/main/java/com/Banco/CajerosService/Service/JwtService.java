package com.banco.cajerosservice.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

    private final Key signingKey;
    private final long expirationMs;

    public JwtService(
            @Value("${security.jwt.secret}") String secret,
            @Value("${security.jwt.expiration-ms:3600000}") long expirationMs
    ) {
        this.signingKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.expirationMs = expirationMs;
    }

    public String generateTokenUser(String correo, int idUsuario, int idRol) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id_usuario", idUsuario);
        claims.put("id_rol", idRol);

        long now = System.currentTimeMillis();

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(correo)
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + expirationMs))
                .signWith(signingKey, SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValid(String token) {
        try {
            Claims claims = getAllClaims(token);
            Date exp = claims.getExpiration();
            return exp != null && exp.after(new Date());
        } catch (JwtException | IllegalArgumentException ex) {
            return false;
        }
    }

    public Claims getAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(signingKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String getCorreoFromToken(String token) {
        return getAllClaims(token).getSubject();
    }

    public int getIdUsuarioFromToken(String token) {
        Object value = getAllClaims(token).get("id_usuario"); // <-- FIX
        return (value instanceof Number n) ? n.intValue() : Integer.parseInt(String.valueOf(value));
    }

    public int getIdRolFromToken(String token) {
        Object value = getAllClaims(token).get("id_rol");
        return (value instanceof Number n) ? n.intValue() : Integer.parseInt(String.valueOf(value));
    }

    public boolean isExpired(String token) {
        try {
            Date exp = getAllClaims(token).getExpiration();
            return exp == null || exp.before(new Date());
        } catch (JwtException | IllegalArgumentException ex) {
            return true;
        }
    }

    public String getJtiFromToken(String token) {
        return getAllClaims(token).getId();
    }
}
