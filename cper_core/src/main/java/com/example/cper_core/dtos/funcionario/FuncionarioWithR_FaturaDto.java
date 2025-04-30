package com.example.cper_core.dtos.funcionario;

import com.example.cper_core.dtos.fatura.FaturaDto;
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
public class FuncionarioWithR_FaturaDto extends FuncionarioDto implements Serializable {
    private Set<FaturaDto> responsavelFaturas;

    public FuncionarioWithR_FaturaDto(Integer id, Set<FaturaDto> responsavelFaturas) {
        super(id);
        this.responsavelFaturas = responsavelFaturas;
    }
}