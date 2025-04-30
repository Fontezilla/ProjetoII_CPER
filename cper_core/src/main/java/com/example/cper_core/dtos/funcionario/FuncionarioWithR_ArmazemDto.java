package com.example.cper_core.dtos.funcionario;

import com.example.cper_core.dtos.armazem.ArmazemDto;
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
public class FuncionarioWithR_ArmazemDto extends FuncionarioDto implements Serializable {
    private Set<ArmazemDto> responsavelArmazens;

    public FuncionarioWithR_ArmazemDto(Integer id, Set<ArmazemDto> responsavelArmazens) {
        super(id);
        this.responsavelArmazens = responsavelArmazens;
    }
}