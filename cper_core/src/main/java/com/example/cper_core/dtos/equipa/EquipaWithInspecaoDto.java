package com.example.cper_core.dtos.equipa;

import com.example.cper_core.dtos.inspecao.InspecaoDto;
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
public class EquipaWithInspecaoDto extends EquipaDto implements Serializable {
    private Set<InspecaoDto> inspecoes;

    public EquipaWithInspecaoDto(Integer id, Set<InspecaoDto> inspecoes) {
        super(id);
        this.inspecoes = inspecoes;
    }
}