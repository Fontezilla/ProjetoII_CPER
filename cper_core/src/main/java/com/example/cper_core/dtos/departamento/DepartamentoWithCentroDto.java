package com.example.cper_core.dtos.departamento;

import com.example.cper_core.dtos.centro_producao.CentroProducaoDto;
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
public class DepartamentoWithCentroDto extends DepartamentoDto implements Serializable {
    private Set<CentroProducaoDto> centrosProducao;

    public DepartamentoWithCentroDto(Integer id, Set<CentroProducaoDto> centrosProducao) {
        super(id);
        this.centrosProducao = centrosProducao;
    }
}