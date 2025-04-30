package com.example.cper_core.dtos.endereco;

import com.example.cper_core.dtos.funcionario.FuncionarioDto;
import com.example.cper_core.entities.Endereco;

import java.io.Serializable;
import java.util.Set;

/**
 * DTO for {@link Endereco}
 */
public class EnderecoWithFuncionarioDto extends EnderecoDto implements Serializable {
    private Set<FuncionarioDto> funcionarios;

    public EnderecoWithFuncionarioDto(Integer id, Set<FuncionarioDto> funcionarios) {
        super(id);
        this.funcionarios = funcionarios;
    }
}