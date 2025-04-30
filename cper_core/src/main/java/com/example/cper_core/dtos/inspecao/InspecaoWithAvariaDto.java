package com.example.cper_core.dtos.inspecao;

import com.example.cper_core.dtos.avaria.AvariaDto;
import com.example.cper_core.entities.Inspecao;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Set;

/**
 * DTO for {@link Inspecao}
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class InspecaoWithAvariaDto extends InspecaoDto implements Serializable {
    private Set<AvariaDto> avarias;

    public InspecaoWithAvariaDto(Integer id, Set<AvariaDto> avarias) {
        super(id);
        this.avarias = avarias;
    }
}