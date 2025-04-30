package com.example.cper_core.dtos.cliente;

import lombok.Data;

import java.io.Serializable;

@Data
public class ClienteLoginResponseDto implements Serializable {
    private String token;
    private Integer clienteId;
    private String nome;

    public ClienteLoginResponseDto(String token, Integer clienteId, String nome) {
        this.token = token;
        this.clienteId = clienteId;
        this.nome = nome;
    }
}