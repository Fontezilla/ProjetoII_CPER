package com.example.cper_core.dtos.contrato;

import com.example.cper_core.dtos.fatura.FaturaDto;
import com.example.cper_core.entities.Contrato;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Set;

/**
 * DTO for {@link Contrato}
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class ContratoWithFaturaDto extends ContratoDto implements Serializable {
    private Set<FaturaDto> faturas;

    public ContratoWithFaturaDto(Integer id, Set<FaturaDto> faturas) {
        super(id);
        this.faturas = faturas;
    }
}