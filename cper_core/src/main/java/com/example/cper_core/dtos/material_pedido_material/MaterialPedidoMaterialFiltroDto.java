package com.example.cper_core.dtos.material_pedido_material;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MaterialPedidoMaterialFiltroDto {

    private Integer idPedido;

    private Integer idMaterial;

    private Integer qtdMin;
    private Integer qtdMax;
}