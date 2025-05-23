package com.example.cper_core.dtos.pedido_material;

import com.example.cper_core.dtos.OnCreate;
import com.example.cper_core.enums.EstadoPedidoMaterial;
import com.example.cper_core.enums.Prioridade;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PedidoMaterialDetailsDto extends PedidoMaterialDto {

    @NotNull(groups = OnCreate.class, message = "A data de criação é obrigatória")
    private OffsetDateTime dataCriacao;

    @NotNull(groups = OnCreate.class, message = "A descricao é obrigatória")
    private String descricao;

    @NotNull(groups = OnCreate.class, message = "A prioridade é obrigatória")
    private Prioridade prioridade;

    @NotNull(groups = OnCreate.class, message = "O estado é obrigatório")
    private EstadoPedidoMaterial estado;
}

