package com.example.cper_core.dtos.funcionario;

import com.example.cper_core.dtos.solicitacao_energetica.SolicitacaoEnergeticaDto;
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
public class FuncionarioWithSolicitacaoDto extends FuncionarioDto implements Serializable {
    private Set<SolicitacaoEnergeticaDto> solicitacaoEnergeticas;

    public FuncionarioWithSolicitacaoDto(Integer id, Set<SolicitacaoEnergeticaDto> solicitacaoEnergeticas) {
        super(id);
        this.solicitacaoEnergeticas = solicitacaoEnergeticas;
    }
}