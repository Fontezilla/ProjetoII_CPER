package com.example.cper_core.dtos.centro_producao;

import com.example.cper_core.entities.CentroProducao;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link CentroProducao}
 */

@Data
@NoArgsConstructor
public class CentroProducaoDto implements Serializable {
    private Integer id;

    public CentroProducaoDto(Integer id) {
        this.id = id;
    }
}