package com.example.cper_core.dtos.endereco;

import com.example.cper_core.dtos.cliente.ClienteDto;
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
public class EnderecoWithClienteDto extends EnderecoDto implements Serializable {
    private Set<ClienteDto> clientes;

    public EnderecoWithClienteDto(Integer id, Set<ClienteDto> clientes) {
        super(id);
        this.clientes = clientes;
    }
}