package com.example.cper_core.dtos.armazem;

import com.example.cper_core.entities.Armazem;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link Armazem}
 */

@Data
@NoArgsConstructor
public class ArmazemDto implements Serializable {
    private Integer id;

    public ArmazemDto(Integer id) {
        this.id = id;
    }
}