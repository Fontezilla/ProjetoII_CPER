package com.example.cper_core.dtos.centro_producao;

import com.example.cper_core.dtos.inspecao.InspecaoDto;
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
public class CentroProducaoWithInspecaoDto extends CentroProducaoDto implements Serializable {
    private Set<InspecaoDto> inspecoes;

    public CentroProducaoWithInspecaoDto(Integer id, Set<InspecaoDto> inspecoes) {
        super(id);
        this.inspecoes = inspecoes;
    }
}