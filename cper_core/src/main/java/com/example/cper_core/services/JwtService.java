package com.example.cper_core.services;

import com.example.cper_core.dtos.IJwtUser;
import com.example.cper_core.enums.JwtTipoUtilizador;
import com.example.cper_core.services.interfaces.IJwtService;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtService implements IJwtService {

    private static final String SECRET_KEY = "8YZgBOG8IspLUIX4SgSrJDHROKhCbOYg";
    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 24; // 24 horas

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    @Override
    public String generateToken(IJwtUser userInfo) {
        JwtBuilder builder = Jwts.builder()
                .setSubject(userInfo.getId().toString())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .claim("nome", userInfo.getNome())
                .claim("tipo", userInfo.getTipo().name());

        if (userInfo instanceof com.example.cper_core.dtos.funcionario.FuncionarioTokenDto funcionarioTokenDto) {
            // Adicionar os setores
            builder.claim("setores", funcionarioTokenDto.getSetores());
        }

        return builder.compact();
    }

    @Override
    public Integer extractUserId(String token) {
        Claims claims = extractAllClaims(token);
        return Integer.parseInt(claims.getSubject());
    }

    @Override
    public JwtTipoUtilizador extractTipoUtilizador(String token) {
        Claims claims = extractAllClaims(token);
        String tipo = claims.get("tipo", String.class);
        return JwtTipoUtilizador.valueOf(tipo);
    }

    @Override
    public boolean isTokenValid(String token) {
        try {
            extractAllClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
