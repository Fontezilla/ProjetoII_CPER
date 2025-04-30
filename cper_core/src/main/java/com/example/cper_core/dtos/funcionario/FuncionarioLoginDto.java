package com.example.cper_core.dtos.funcionario;

import lombok.Data;

import java.io.Serializable;

@Data
public class FuncionarioLoginDto implements Serializable {
    private String password;
    private String email;

    public FuncionarioLoginDto(String password, String email) {
        this.password = password;
        this.email = email;
    }
}