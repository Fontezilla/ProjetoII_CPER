package com.example.cper_core.dtos.cliente;

import com.example.cper_core.dtos.solicitacao_energetica.SolicitacaoEnergeticaDto;
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
public class ClienteWithSolicitacaoDto extends ClienteDto implements Serializable {
    private Set<SolicitacaoEnergeticaDto> solicitacaoEnergeticas;

    public ClienteWithSolicitacaoDto(Integer id, Set<SolicitacaoEnergeticaDto> solicitacaoEnergeticas) {
        super(id);
        this.solicitacaoEnergeticas = solicitacaoEnergeticas;
    }
}