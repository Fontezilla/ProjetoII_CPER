package com.example.cper_core.services.interfaces;

import com.example.cper_core.dtos.IJwtUser;
import com.example.cper_core.enums.JwtTipoUtilizador;
import io.jsonwebtoken.Claims;

public interface IJwtService {

     String generateToken(IJwtUser userInfo);

     Integer extractUserId(String token);

     JwtTipoUtilizador extractTipoUtilizador(String token);

     boolean isTokenValid(String token);
}
