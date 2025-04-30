package com.example.cper_core.dtos.departamento;

import com.example.cper_core.dtos.equipa.EquipaDto;
import com.example.cper_core.entities.Departamento;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Set;

/**
 * DTO for {@link Departamento}
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class DepartamentoWithEquipaDto extends DepartamentoDto implements Serializable {
    private Set<EquipaDto> equipas;

    public DepartamentoWithEquipaDto(Integer id, Set<EquipaDto> equipas) {
        super(id);
        this.equipas = equipas;
    }
}