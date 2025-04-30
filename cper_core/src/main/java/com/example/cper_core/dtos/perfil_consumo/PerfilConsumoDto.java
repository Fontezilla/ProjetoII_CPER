package com.example.cper_core.dtos.perfil_consumo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link com.example.cper_core.entities.PerfilConsumo}
 */

@Data
@NoArgsConstructor
public class PerfilConsumoDto implements Serializable {
    private  Integer id;

    public PerfilConsumoDto(Integer id) {
        this.id = id;
    }
}