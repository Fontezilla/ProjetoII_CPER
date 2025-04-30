package com.example.cper_core.dtos.inspecao;

import com.example.cper_core.dtos.equipa.EquipaDto;
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
public class InspecaoWithEquipaDto extends InspecaoDto implements Serializable {
    private Set<EquipaDto> equipas;

    public InspecaoWithEquipaDto(Integer id, Set<EquipaDto> equipas) {
        super(id);
        this.equipas = equipas;
    }
}