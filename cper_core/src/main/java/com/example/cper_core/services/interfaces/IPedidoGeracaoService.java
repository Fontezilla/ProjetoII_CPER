package com.example.cper_core.services.interfaces;

import com.example.cper_core.dtos.pedido_geracao.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IPedidoGeracaoService extends IXService<
        PedidoGeracaoDto,
        PedidoGeracaoDetailsDto,
        PedidoGeracaoDetailsExtendedDto,
        PedidoGeracaoFiltroDto,
        PedidoGeracaoWithRelationshipsDto,
        Integer
        > {
}