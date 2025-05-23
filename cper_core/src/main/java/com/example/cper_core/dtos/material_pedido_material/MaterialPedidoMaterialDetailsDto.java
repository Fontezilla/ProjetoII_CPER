package com.example.cper_core.dtos.material_pedido_material;

import com.example.cper_core.dtos.OnCreate;
import com.example.cper_core.dtos.pedido_material.PedidoMaterialDto;
import com.example.cper_core.dtos.material.MaterialDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MaterialPedidoMaterialDetailsDto extends MaterialPedidoMaterialDto {

    @NotNull(groups = OnCreate.class, message = "A quantidade é obrigatória")
    @Min(value = 1, message = "A quantidade não pode ser menor do 1", groups = OnCreate.class)
    private Integer qtd;
}
