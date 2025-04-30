package com.example.cper_core.dtos.inspecao;

import com.example.cper_core.entities.Inspecao;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link Inspecao}
 */

@Data
@NoArgsConstructor
public class InspecaoDto implements Serializable {
    private Integer id;

    public InspecaoDto(Integer id) {
        this.id = id;
    }
}