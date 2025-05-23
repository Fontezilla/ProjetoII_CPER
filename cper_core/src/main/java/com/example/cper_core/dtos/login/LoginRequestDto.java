package com.example.cper_core.dtos.login;

import lombok.Data;

@Data
public class LoginRequestDto {
    private String email;
    private String password;
}