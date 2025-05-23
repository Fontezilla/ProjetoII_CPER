package com.example.cper_core.dtos.pedido_material;

import com.example.cper_core.dtos.avaria.AvariaDto;
import com.example.cper_core.dtos.material_pedido_material.MaterialPedidoMaterialDto;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PedidoMaterialWithRelationshipsDto extends PedidoMaterialDto {

    @Valid
    private Set<MaterialPedidoMaterialDto> materialPedidoMateriais;

    @Valid
    private Set<AvariaDto> avarias;
}