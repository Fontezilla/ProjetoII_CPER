package com.example.cper_core.dtos.cliente;

import com.example.cper_core.dtos.perfil_consumo.PerfilConsumoDto;
import com.example.cper_core.entities.Cliente;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Set;

/**
 * DTO for {@link Cliente}
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class ClienteWithPerfilDto extends ClienteDto implements Serializable {
    private Set<PerfilConsumoDto> perfilConsumos;

    public ClienteWithPerfilDto(Integer id, Set<PerfilConsumoDto> perfilConsumos) {
        super(id);
        this.perfilConsumos = perfilConsumos;
    }
}