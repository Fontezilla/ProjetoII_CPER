package com.example.cper_core.dtos.avaria;

import com.example.cper_core.entities.Avaria;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link Avaria}
 */

@Data
@NoArgsConstructor
public class AvariaDto implements Serializable {
    private Integer id;

    public AvariaDto(Integer id) {
        this.id = id;
    }
}