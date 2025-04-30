package com.example.cper_core.services.interfaces;

import com.example.cper_core.dtos.material_pedido_material.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IMaterialPedidoMaterialService {

    // Generic CRUD operations

    Page<MaterialPedidoMaterialDto> listAll(Pageable pageable);

    Page<MaterialPedidoMaterialDto> listFiltered(Pageable pageable, MaterialPedidoMaterialDto filter);

    Optional<MaterialPedidoMaterialDto> getById(Integer id);

    MaterialPedidoMaterialDto create(MaterialPedidoMaterialDto dto);

    MaterialPedidoMaterialDto update(Integer id, MaterialPedidoMaterialDto dto);

    void softDelete(Integer id);
}
