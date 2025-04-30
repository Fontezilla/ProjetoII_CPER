package com.example.cper_core.dtos.departamento;

import com.example.cper_core.dtos.armazem.ArmazemDto;
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
public class DepartamentoWithArmazemDto extends DepartamentoDto implements Serializable {
    private Set<ArmazemDto> armazems;

    public DepartamentoWithArmazemDto(Integer id, Set<ArmazemDto> armazems) {
        super(id);
        this.armazems = armazems;
    }
}