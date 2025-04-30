package com.example.cper_core.dtos.equipa;

import com.example.cper_core.dtos.avaria.AvariaDto;
import com.example.cper_core.entities.Equipa;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Set;

/**
 * DTO for {@link Equipa}
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class EquipaWithAvariaDto extends EquipaDto implements Serializable {
    private Set<AvariaDto> avarias;

    public EquipaWithAvariaDto(Integer id, Set<AvariaDto> avarias) {
        super(id);
        this.avarias = avarias;
    }
}