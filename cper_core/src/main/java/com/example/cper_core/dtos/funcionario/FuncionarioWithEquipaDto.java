package com.example.cper_core.dtos.funcionario;

import com.example.cper_core.dtos.equipa.EquipaDto;
import com.example.cper_core.entities.Funcionario;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Set;

/**
 * DTO for {@link Funcionario}
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class FuncionarioWithEquipaDto extends FuncionarioDto implements Serializable {
    private Set<EquipaDto> equipas;

    public FuncionarioWithEquipaDto(Integer id, Set<EquipaDto> equipas) {
        super(id);
        this.equipas = equipas;
    }
}