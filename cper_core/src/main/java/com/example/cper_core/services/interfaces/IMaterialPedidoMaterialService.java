package com.example.cper_core.services.interfaces;

import com.example.cper_core.dtos.material_pedido_material.*;
import com.example.cper_core.entities.MaterialPedidoMaterialId;

public interface IMaterialPedidoMaterialService extends IXService<
        MaterialPedidoMaterialDto,
        MaterialPedidoMaterialDetailsDto,
        MaterialPedidoMaterialDetailsDto,
        MaterialPedidoMaterialFiltroDto,
        MaterialPedidoMaterialDto,
        MaterialPedidoMaterialId
        > {
}