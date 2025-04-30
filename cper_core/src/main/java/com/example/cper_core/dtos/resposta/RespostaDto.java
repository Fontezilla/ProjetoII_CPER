package com.example.cper_core.dtos.resposta;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link com.example.cper_core.entities.Resposta}
 */

@Data
@NoArgsConstructor
public class RespostaDto implements Serializable {
    private Integer id;

    public RespostaDto(Integer id) {
        this.id = id;
    }
}