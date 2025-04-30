package com.example.cper_core.dtos.avaria;

import com.example.cper_core.dtos.pedido_material.PedidoMaterialDto;
import com.example.cper_core.entities.Avaria;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

/**
 * DTO for {@link Avaria}
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class AvariaWithPedidoDto extends AvariaDto implements Serializable {
    private Set<PedidoMaterialDto> pedidoMateriais = new LinkedHashSet<>();

    public AvariaWithPedidoDto(Integer id, Set<PedidoMaterialDto> pedidoMateriais) {
        super(id);
        this.pedidoMateriais = pedidoMateriais;
    }
}