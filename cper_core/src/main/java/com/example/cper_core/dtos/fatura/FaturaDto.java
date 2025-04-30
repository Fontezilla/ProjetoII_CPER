package com.example.cper_core.dtos.fatura;

import com.example.cper_core.entities.Fatura;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link Fatura}
 */

@Data
@NoArgsConstructor
public class FaturaDto implements Serializable {
    private Integer id;

    public FaturaDto(Integer id) {
        this.id = id;
    }
}