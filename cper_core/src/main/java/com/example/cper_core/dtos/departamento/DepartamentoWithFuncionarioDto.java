package com.example.cper_core.dtos.departamento;

import com.example.cper_core.dtos.funcionario.FuncionarioDto;
import com.example.cper_core.entities.Departamento;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

/**
 * DTO for {@link Departamento}
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class DepartamentoWithFuncionarioDto extends DepartamentoDto implements Serializable {
    private Set<FuncionarioDto> funcionarios;

    public DepartamentoWithFuncionarioDto(Integer id, Set<FuncionarioDto> funcionarios) {
        super(id);
        this.funcionarios = funcionarios;
    }
}