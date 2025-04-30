package com.example.cper_core.dtos.avaria;

import com.example.cper_core.dtos.equipa.EquipaDto;
import com.example.cper_core.entities.Avaria;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * DTO for {@link Avaria}
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class AvariaWithEquipaDto extends AvariaDto implements Serializable {
    private Set<EquipaDto> equipas;

    public AvariaWithEquipaDto(Integer id, Set<EquipaDto> equipas) {
        super(id);
        this.equipas = equipas;
    }
}