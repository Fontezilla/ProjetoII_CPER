package com.example.cper_webapi.controllers;

import com.example.cper_core.dtos.login.LoginRequestDto;
import com.example.cper_core.dtos.login.LoginResponseDto;
import com.example.cper_core.enums.JwtTipoUtilizador;
import com.example.cper_core.services.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Autenticação", description = "Login do cliente")
public class AuthRestController {

    private final AuthService authService;

    public AuthRestController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    @Operation(summary = "Autenticar cliente")
    public ResponseEntity<LoginResponseDto> login(
            @Valid @RequestBody LoginRequestDto request
    ) {
        return ResponseEntity.ok(authService.login(JwtTipoUtilizador.CLIENTE, request));
    }
}