package com.example.cper_core.dtos.centro_producao;

import com.example.cper_core.dtos.pedido_geracao.PedidoGeracaoDto;
import com.example.cper_core.entities.CentroProducao;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

/**
 * DTO for {@link CentroProducao}
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class CentroProducaoWithPedidoGeracaoDto extends CentroProducaoDto implements Serializable {
    private Set<PedidoGeracaoDto> pedidosGeracao = new LinkedHashSet<>();

    public CentroProducaoWithPedidoGeracaoDto(Integer id, Set<PedidoGeracaoDto> pedidosGeracao) {
        super(id);
        this.pedidosGeracao = pedidosGeracao;
    }
}