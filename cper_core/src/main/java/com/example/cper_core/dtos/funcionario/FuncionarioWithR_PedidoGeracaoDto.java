package com.example.cper_core.dtos.funcionario;

import com.example.cper_core.dtos.pedido_geracao.PedidoGeracaoDto;
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
public class FuncionarioWithR_PedidoGeracaoDto extends FuncionarioDto implements Serializable {
    private Set<PedidoGeracaoDto> responsavelPedidoGeracoes;

    public FuncionarioWithR_PedidoGeracaoDto(Integer id, Set<PedidoGeracaoDto> responsavelPedidoGeracoes) {
        super(id);
        this.responsavelPedidoGeracoes = responsavelPedidoGeracoes;
    }
}