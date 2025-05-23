package com.example.cper_core.utils;

import com.example.cper_core.enums.JwtTipoUtilizador;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Set;

@Component
public class JwtUtils {

    private static final String SECRET = "rT7#xvP!D@q8Wz4L9eMfA2NcB3YuK0hV";
    private static final long EXPIRATION = 86400000; // 1 dia

    private final SecretKey key = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));

    public String generateToken(String email, JwtTipoUtilizador tipo, Integer setorPrincipal, Set<Integer> setoresAssociados) {
        return Jwts.builder()
                .setSubject(email)
                .claim("tipo", tipo.name())
                .claim("setorPrincipal", setorPrincipal)
                .claim("setoresAssociados", setoresAssociados)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

    public Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean isTokenValid(String token) {
        try {
            getClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public String getEmail(String token) {
        return getClaims(token).getSubject();
    }

    public JwtTipoUtilizador getTipo(String token) {
        String tipo = getClaims(token).get("tipo", String.class);
        return JwtTipoUtilizador.valueOf(tipo);
    }

    public Integer getSetorPrincipal(String token) {
        return getClaims(token).get("setorPrincipal", Integer.class);
    }

    @SuppressWarnings("unchecked")
    public Set<Integer> getSetoresAssociados(String token) {
        return getClaims(token).get("setoresAssociados", Set.class);
    }
}