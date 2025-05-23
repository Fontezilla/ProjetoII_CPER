package com.example.cper_core.dtos.material_pedido_material;

import com.example.cper_core.dtos.OnCreate;
import com.example.cper_core.dtos.OnUpdate;
import com.example.cper_core.entities.MaterialPedidoMaterialId;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MaterialPedidoMaterialDto implements Serializable {

    @Valid
    @NotNull(message = "O identificador composto é obrigatório")
    private MaterialPedidoMaterialId id;
}
