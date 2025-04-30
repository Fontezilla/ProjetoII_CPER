package com.example.cper_core.dtos.pedido_material;

import com.example.cper_core.entities.PedidoMaterial;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link PedidoMaterial}
 */

@Data
@NoArgsConstructor
public class PedidoMaterialDto implements Serializable {
    private Integer id;

    public PedidoMaterialDto(Integer id) {
        this.id = id;
    }
}