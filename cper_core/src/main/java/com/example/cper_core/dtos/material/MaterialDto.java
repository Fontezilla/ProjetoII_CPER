package com.example.cper_core.dtos.material;

import com.example.cper_core.entities.Material;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link Material}
 */

@Data
@NoArgsConstructor
public class MaterialDto implements Serializable {
    private Integer id;

    public MaterialDto(Integer id) {
        this.id = id;
    }
}