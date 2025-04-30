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
public class FuncionarioWithR_EquipaDto extends FuncionarioDto implements Serializable {
    private Set<EquipaDto> responsavelEquipas;

    public FuncionarioWithR_EquipaDto(Integer id, Set<EquipaDto> responsavelEquipas) {
        super(id);
        this.responsavelEquipas = responsavelEquipas;
    }
}