package com.example.cper_core.dtos.funcionario;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class FuncionarioLoginResponseDto implements Serializable {
    private String token;
    private Integer funcionarioId;
    private String nome;
    private List<String> setores;

    public FuncionarioLoginResponseDto(String token, Integer funcionarioId, String nome, List<String> setores) {
        this.token = token;
        this.funcionarioId = funcionarioId;
        this.nome = nome;
        this.setores = setores;
    }
}
