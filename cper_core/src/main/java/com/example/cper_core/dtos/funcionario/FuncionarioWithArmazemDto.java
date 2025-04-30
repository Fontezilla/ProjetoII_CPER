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
public class FuncionarioWithArmazemDto extends FuncionarioDto implements Serializable {
    private Set<ArmazemDto> armazems;

    public FuncionarioWithArmazemDto(Integer id, Set<ArmazemDto> armazems) {
        super(id);
        this.armazems = armazems;
    }
}