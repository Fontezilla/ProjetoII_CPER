package com.example.cper_core.dtos.localidade;

import com.example.cper_core.dtos.endereco.EnderecoDto;
import com.example.cper_core.entities.Localidade;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Set;

/**
 * DTO for {@link Localidade}
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class LocalidadeWithEnderecosDto extends LocalidadeDto implements Serializable {
    private Set<EnderecoDto> enderecos;

    public LocalidadeWithEnderecosDto(Integer id, Set<EnderecoDto> enderecos) {
        super(id);
        this.enderecos = enderecos;
    }
}