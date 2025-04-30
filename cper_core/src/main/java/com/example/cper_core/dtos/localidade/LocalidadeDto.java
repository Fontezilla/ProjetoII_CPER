package com.example.cper_core.dtos.localidade;

import com.example.cper_core.entities.Localidade;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link Localidade}
 */

@Data
@NoArgsConstructor
public class LocalidadeDto implements Serializable {
    private Integer id;

    public LocalidadeDto(Integer id) {
        this.id = id;
    }
}