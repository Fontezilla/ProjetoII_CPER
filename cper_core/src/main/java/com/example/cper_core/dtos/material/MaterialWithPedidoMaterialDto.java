package com.example.cper_core.dtos.material;

import com.example.cper_core.entities.Material;
import com.example.cper_core.entities.MaterialPedidoMaterial;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Set;

/**
 * DTO for {@link Material}
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class MaterialWithPedidoMaterialDto extends MaterialDto implements Serializable {
    private Set<MaterialPedidoMaterial> materialPedidomateriais;

    public MaterialWithPedidoMaterialDto(Integer id, Set<MaterialPedidoMaterial> materialPedidomateriais) {
        super(id);
        this.materialPedidomateriais = materialPedidomateriais;
    }
}