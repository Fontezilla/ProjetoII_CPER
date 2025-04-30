package com.example.cper_core.dtos.centro_producao;

import com.example.cper_core.dtos.funcionario.FuncionarioDto;
import com.example.cper_core.entities.CentroProducao;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

/**
 * DTO for {@link CentroProducao}
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class CentroProducaoWithFuncionarioDto extends CentroProducaoDto implements Serializable {
    private Set<FuncionarioDto> funcionarios;

    public CentroProducaoWithFuncionarioDto(Integer id, Set<FuncionarioDto> funcionarios) {
        super(id);
        this.funcionarios = funcionarios;
    }
}