package com.example.cper_core.dtos.solicitacao_material;

import com.example.cper_core.dtos.OnCreate;
import com.example.cper_core.enums.EstadoSolicitacaoMaterial;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SolicitacaoMaterialDetailsDto extends SolicitacaoMaterialDto {

    @NotNull(groups = OnCreate.class, message = "A data do pedido é obrigatório.")
    private OffsetDateTime dataPedido;

    @NotNull(groups = OnCreate.class, message = "A descrição é obrigatória.")
    private String descricao;

    @NotNull(groups = OnCreate.class, message = "O estado é obrigatório.")
    private EstadoSolicitacaoMaterial estado;

}