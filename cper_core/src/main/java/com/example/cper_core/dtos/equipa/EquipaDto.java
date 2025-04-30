package com.example.cper_core.dtos.equipa;

import com.example.cper_core.entities.Equipa;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link Equipa}
 */

@Data
@NoArgsConstructor
public class EquipaDto implements Serializable {
    private Integer id;

    public EquipaDto(Integer id) {
        this.id = id;
    }
}