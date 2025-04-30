package com.example.cper_core.dtos.contrato;

import com.example.cper_core.dtos.pedido_geracao.PedidoGeracaoDto;
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
public class ContratoWithPedidoDto extends ContratoDto implements Serializable {
    private Set<PedidoGeracaoDto> pedidosGeracao;

    public ContratoWithPedidoDto(Integer id, Set<PedidoGeracaoDto> pedidosGeracao) {
        super(id);
        this.pedidosGeracao = pedidosGeracao;
    }
}