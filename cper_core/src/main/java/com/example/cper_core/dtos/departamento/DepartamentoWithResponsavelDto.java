package com.example.cper_core.dtos.departamento;

import com.example.cper_core.dtos.funcionario.FuncionarioDto;
import com.example.cper_core.entities.Departamento;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Set;

/**
 * DTO for {@link Departamento}
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class DepartamentoWithResponsavelDto extends DepartamentoDto implements Serializable {
    private Set<FuncionarioDto> responsaveis;

    public DepartamentoWithResponsavelDto(Integer id, Set<FuncionarioDto> responsaveis) {
        super(id);
        this.responsaveis = responsaveis;
    }
}