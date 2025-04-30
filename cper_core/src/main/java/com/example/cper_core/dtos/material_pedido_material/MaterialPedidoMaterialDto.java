package com.example.cper_core.dtos.material_pedido_material;

import com.example.cper_core.dtos.material.MaterialDto;
import com.example.cper_core.dtos.pedido_material.PedidoMaterialDto;
import com.example.cper_core.entities.MaterialPedidoMaterial;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.io.Serializable;

/**
 * DTO for {@link MaterialPedidoMaterial}
 */

@Data
public class MaterialPedidoMaterialDto implements Serializable {
    @NotNull(message = "O pedido de material não pode ser nulo")
    private PedidoMaterialDto pedidoMaterial;

    @NotNull(message = "O material não pode ser nulo")
    private MaterialDto material;

    @NotNull(message = "A quantidade não pode ser nula")
    @Positive(message = "A quantidade deve ser um valor positivo")
    private Integer qtd;

    public MaterialPedidoMaterialDto() {
    }

    public MaterialPedidoMaterialDto(PedidoMaterialDto pedidoMaterial, MaterialDto material, Integer qtd) {
        this.pedidoMaterial = pedidoMaterial;
        this.material = material;
        this.qtd = qtd;
    }
}