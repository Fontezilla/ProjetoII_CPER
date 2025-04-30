package com.example.cper_core.dtos.centro_producao;

import com.example.cper_core.dtos.avaria.AvariaDto;
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
public class CentroProducaoWithAvariaDto extends CentroProducaoDto implements Serializable {
    private Set<AvariaDto> avarias;

    public CentroProducaoWithAvariaDto(Integer id, Set<AvariaDto> avarias) {
        super(id);
        this.avarias = avarias;
    }
}