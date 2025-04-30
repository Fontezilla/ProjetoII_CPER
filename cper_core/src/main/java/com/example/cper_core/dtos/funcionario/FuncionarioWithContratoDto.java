package com.example.cper_core.dtos.funcionario;

import com.example.cper_core.dtos.contrato.ContratoDto;
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
public class FuncionarioWithContratoDto extends FuncionarioDto implements Serializable {
    private Set<ContratoDto> responsavelContratos;

    public FuncionarioWithContratoDto(Integer id, Set<ContratoDto> responsavelContratos) {
        super(id);
        this.responsavelContratos = responsavelContratos;
    }
}