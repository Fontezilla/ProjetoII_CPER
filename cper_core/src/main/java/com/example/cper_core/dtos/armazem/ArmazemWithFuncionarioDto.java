package com.example.cper_core.dtos.armazem;

import com.example.cper_core.dtos.funcionario.FuncionarioDto;
import com.example.cper_core.entities.Armazem;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

/**
 * DTO for {@link Armazem}
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class ArmazemWithFuncionarioDto extends ArmazemDto implements Serializable {
    private Set<FuncionarioDto> funcionarios = new LinkedHashSet<>();

    public ArmazemWithFuncionarioDto(Integer id, Set<FuncionarioDto> funcionarios) {
        super(id);
        this.funcionarios = funcionarios;
    }
}