package com.example.cper_core.services.interfaces;

import com.example.cper_core.dtos.pedido_material.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IPedidoMaterialService {

    // Generic CRUD operations

    Page<PedidoMaterialDetailsDto> listAll(Pageable pageable);

    Page<PedidoMaterialDetailsDto> listFiltered(Pageable pageable, PedidoMaterialDto filter);

    Optional<PedidoMaterialDetailsDto> getById(Integer id);

    PedidoMaterialDetailsDto create(PedidoMaterialDetailsDto dto);

    PedidoMaterialDetailsDto update(Integer id, PedidoMaterialDetailsDto dto);

    void softDelete(Integer id);

    // Association operations: Avaria

    PedidoMaterialWithAvariaDto linkAvaria(Integer idPedidoMaterial, Integer idAvaria);

    PedidoMaterialWithAvariaDto unlinkAvaria(Integer idPedidoMaterial, Integer idAvaria);

    // Association operations: MaterialPedidoMaterial

    PedidoMaterialWithPedidoMaterialDto linkPedidoMaterial(Integer idPedidoMaterial, Integer idPedidoMaterialLink);

    PedidoMaterialWithPedidoMaterialDto unlinkPedidoMaterial(Integer idPedidoMaterial, Integer idPedidoMaterialLink);
}