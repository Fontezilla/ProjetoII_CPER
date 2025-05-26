package com.example.cper_core.dtos.login;

import com.example.cper_core.enums.JwtTipoUtilizador;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDto {
    private String token;
    private OffsetDateTime expiration;
}