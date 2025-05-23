package com.example.cper_core.dtos.pedido_material;

import com.example.cper_core.dtos.OnUpdate;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoMaterialDto implements Serializable {
    @NotNull(groups = OnUpdate.class, message = "O id do pedido de material é obrigatório")
    private Integer id;
}