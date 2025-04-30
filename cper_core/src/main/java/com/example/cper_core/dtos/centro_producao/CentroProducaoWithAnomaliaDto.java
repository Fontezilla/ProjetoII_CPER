package com.example.cper_core.dtos.centro_producao;

import com.example.cper_core.dtos.anomalia.AnomaliaDto;
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
public class CentroProducaoWithAnomaliaDto extends CentroProducaoDto implements Serializable {
    private Set<AnomaliaDto> anomalias;

    public CentroProducaoWithAnomaliaDto(Integer id, Set<AnomaliaDto> anomalias) {
        super(id);
        this.anomalias = anomalias;
    }
}