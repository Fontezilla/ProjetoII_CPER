package com.example.cper_core.dtos.departamento;

import com.example.cper_core.entities.Departamento;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link Departamento}
 */

@Data
@NoArgsConstructor
public class DepartamentoDto implements Serializable {
    private Integer id;

    public DepartamentoDto(Integer id) {
        this.id = id;
    }
}