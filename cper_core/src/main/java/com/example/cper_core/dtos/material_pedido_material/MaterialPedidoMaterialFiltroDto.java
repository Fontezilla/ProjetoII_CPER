package com.example.cper_core.dtos.material_pedido_material;

import lombok.Data;

@Data
public class MaterialPedidoMaterialFiltroDto {

    private Integer idPedido;
    private Integer idMaterial;
    private Integer qtdMin;
    private Integer qtdMax;
}

