package com.example.cper_core.dtos.contrato;

import com.example.cper_core.entities.Contrato;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link Contrato}
 */

@Data
@NoArgsConstructor
public class ContratoDto implements Serializable {
    private Integer id;

    public ContratoDto(Integer id) {
        this.id = id;
    }
}