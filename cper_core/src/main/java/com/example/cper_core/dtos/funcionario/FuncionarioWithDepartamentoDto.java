package com.example.cper_core.dtos.funcionario;

import com.example.cper_core.dtos.departamento.DepartamentoDto;
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
public class FuncionarioWithDepartamentoDto extends FuncionarioDto implements Serializable {
    private Set<DepartamentoDto> Departamentos;

    public FuncionarioWithDepartamentoDto(Integer id, Set<DepartamentoDto> Departamentos) {
        super(id);
        this.Departamentos = Departamentos;
    }
}