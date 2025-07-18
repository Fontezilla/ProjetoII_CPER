package com.example.cper_webapi.utils;

import com.example.cper_core.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

@Component
public class JwtRequestUtils {

    private final JwtUtils jwtUtils;

    public JwtRequestUtils(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    public Integer getUserIdFromRequest(HttpServletRequest request) {
        String token = extractTokenFromRequest(request);
        return jwtUtils.getId(token);
    }

    private String extractTokenFromRequest(HttpServletRequest request) {
        String header = request.getHeader("Authorization");

        if (header == null || !header.startsWith("Bearer ")) {
            throw new RuntimeException("Token JWT não fornecido ou mal formatado.");
        }

        return header.substring(7);
    }
}