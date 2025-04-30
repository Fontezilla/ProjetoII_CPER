package com.example.cper_core.dtos.pedido_material;

import com.example.cper_core.dtos.avaria.AvariaDto;
import com.example.cper_core.entities.PedidoMaterial;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

/**
 * DTO for {@link PedidoMaterial}
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class PedidoMaterialWithAvariaDto extends PedidoMaterialDto implements Serializable {
    private Set<AvariaDto> avarias = new LinkedHashSet<>();

    public PedidoMaterialWithAvariaDto(Integer id, Set<AvariaDto> avarias) {
        super(id);
        this.avarias = avarias;
    }
}