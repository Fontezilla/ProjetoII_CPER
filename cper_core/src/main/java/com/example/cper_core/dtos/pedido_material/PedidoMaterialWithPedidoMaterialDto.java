package com.example.cper_core.dtos.pedido_material;

import com.example.cper_core.entities.MaterialPedidoMaterial;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

/**
 * DTO for {@link com.example.cper_core.entities.PedidoMaterial}
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PedidoMaterialWithPedidoMaterialDto extends PedidoMaterialDto implements Serializable {
    private Set<MaterialPedidoMaterial> materialPedidomateriais = new LinkedHashSet<>();

    public PedidoMaterialWithPedidoMaterialDto(Integer id, Set<MaterialPedidoMaterial> materialPedidomateriais) {
        super(id);
        this.materialPedidomateriais = materialPedidomateriais;
    }
}