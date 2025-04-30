package com.example.cper_core.dtos.endereco;

import com.example.cper_core.dtos.contrato.ContratoDto;
import com.example.cper_core.entities.Endereco;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Set;

/**
 * DTO for {@link Endereco}
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class EnderecoWithContratoDto extends EnderecoDto implements Serializable {
    private Set<ContratoDto> contratoes;

    public EnderecoWithContratoDto(Integer id, Set<ContratoDto> contratoes) {
        super(id);
        this.contratoes = contratoes;
    }
}