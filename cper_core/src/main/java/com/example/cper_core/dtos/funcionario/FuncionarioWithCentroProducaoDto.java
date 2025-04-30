package com.example.cper_core.dtos.funcionario;

import com.example.cper_core.dtos.centro_producao.CentroProducaoDto;
import com.example.cper_core.entities.Funcionario;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Set;

/**
 * DTO for {@link Funcionario}
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class FuncionarioWithCentroProducaoDto extends FuncionarioDto implements Serializable {
    private Set<CentroProducaoDto> centroProducoes;

    public FuncionarioWithCentroProducaoDto(Integer id, Set<CentroProducaoDto> centroProducoes) {
        super(id);
        this.centroProducoes = centroProducoes;
    }
}