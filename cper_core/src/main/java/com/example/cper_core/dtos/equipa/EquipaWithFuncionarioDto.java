package com.example.cper_core.dtos.equipa;

import com.example.cper_core.dtos.funcionario.FuncionarioDto;
import com.example.cper_core.entities.Equipa;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Set;

/**
 * DTO for {@link Equipa}
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class EquipaWithFuncionarioDto extends EquipaDto implements Serializable {
    private Set<FuncionarioDto> funcionarios;

    public EquipaWithFuncionarioDto(Integer id, Set<FuncionarioDto> funcionarios) {
        super(id);
        this.funcionarios = funcionarios;
    }
}