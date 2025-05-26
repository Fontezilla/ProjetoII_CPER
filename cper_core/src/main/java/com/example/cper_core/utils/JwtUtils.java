package com.example.cper_core.utils;

import com.example.cper_core.enums.JwtTipoUtilizador;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.*;

@Component
public class JwtUtils {

    private static final String SECRET = "G7yKj7uUZtMBFe2K0yQ5xEZEv6z0YvdmZfDpLxS9Fg1iNbaQUKFVUy9mWTdL+gU7wZjZHZ8Zzox7KcMmbTmw==";
    private static final long EXPIRATION = 86400000; // 1 dia

    private final SecretKey key = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));

    // Geração do token com todos os claims relevantes
    public String generateToken(String email, String nome, Integer id, JwtTipoUtilizador tipo,
                                Integer setorPrincipal, Set<Integer> setoresAssociados) {

        return Jwts.builder()
                .setSubject(email)
                .claim("nome", nome)
                .claim("id", id)
                .claim("tipo", tipo.name())
                .claim("setorPrincipal", setorPrincipal)
                .claim("setoresAssociados", setoresAssociados)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // Parser base
    public Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // Validação segura
    public boolean isTokenValid(String token) {
        try {
            getClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public boolean isTokenExpirado(String token) {
        Date expiration = getClaims(token).getExpiration();
        return expiration.before(new Date());
    }

    public OffsetDateTime getExpiracao(String token) {
        return getClaims(token)
                .getExpiration()
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toOffsetDateTime();
    }

    // Extração dos dados
    public String getEmail(String token) {
        return getClaims(token).getSubject();
    }

    public String getNome(String token) {
        return getClaims(token).get("nome", String.class);
    }

    public Integer getId(String token) {
        return getClaims(token).get("id", Integer.class);
    }

    public JwtTipoUtilizador getTipo(String token) {
        String tipoStr = getClaims(token).get("tipo", String.class);
        try {
            return JwtTipoUtilizador.valueOf(tipoStr);
        } catch (Exception e) {
            return null; // ou lançar exceção, conforme preferires
        }
    }

    public Integer getSetorPrincipal(String token) {
        return getClaims(token).get("setorPrincipal", Integer.class);
    }

    public Set<Integer> getSetoresAssociados(String token) {
        Object raw = getClaims(token).get("setoresAssociados");
        if (raw instanceof Iterable<?>) {
            Set<Integer> setores = new HashSet<>();
            for (Object item : (Iterable<?>) raw) {
                if (item instanceof Integer i) {
                    setores.add(i);
                } else if (item instanceof Number n) {
                    setores.add(n.intValue());
                }
            }
            return setores;
        }
        return Collections.emptySet();
    }
}