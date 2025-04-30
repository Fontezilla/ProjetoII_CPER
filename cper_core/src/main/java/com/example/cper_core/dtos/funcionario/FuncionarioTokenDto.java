package com.example.cper_core.dtos.funcionario;

import com.example.cper_core.dtos.IJwtUser;
import com.example.cper_core.enums.JwtTipoUtilizador;
import lombok.Data;

import java.util.List;

@Data
public class FuncionarioTokenDto implements IJwtUser {
    private Integer id;
    private String nome;
    private List<String> setores;
    private JwtTipoUtilizador tipo;

    public FuncionarioTokenDto(Integer id, String nome, List<String> setores) {
        this.id = id;
        this.nome = nome;
        this.setores = setores;
        this.tipo = JwtTipoUtilizador.FUNCIONARIO;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public JwtTipoUtilizador getTipo() {
        return tipo;
    }
}
