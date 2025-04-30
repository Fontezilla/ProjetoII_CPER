package com.example.cper_core.dtos.pedido_geracao;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link com.example.cper_core.entities.PedidoGeracao}
 */

@Data
@NoArgsConstructor
public class PedidoGeracaoDto implements Serializable {
    private Integer id;

    public PedidoGeracaoDto(Integer id) {
        this.id = id;
    }
}