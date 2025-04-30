package com.example.cper_core.dtos.solicitacao_energetica;

import com.example.cper_core.entities.SolicitacaoEnergetica;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link SolicitacaoEnergetica}
 */

@Data
@NoArgsConstructor
public class SolicitacaoEnergeticaDto implements Serializable {
    private Integer id;

    public SolicitacaoEnergeticaDto(Integer id) {
        this.id = id;
    }
}