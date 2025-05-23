package com.example.cper_core.services.interfaces;

import com.example.cper_core.dtos.pedido_material.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Set;

public interface IPedidoMaterialService extends IXService<
        PedidoMaterialDto,
        PedidoMaterialDetailsDto,
        PedidoMaterialDetailsExtendedDto,
        PedidoMaterialFiltroDto,
        PedidoMaterialWithRelationshipsDto,
        Integer
        > {

    void linkToAvarias(Integer idPedidoMaterial, Set<Integer> idsAvarias);
    void unlinkFromAvarias(Integer idPedidoMaterial, Set<Integer> idsAvarias);
}