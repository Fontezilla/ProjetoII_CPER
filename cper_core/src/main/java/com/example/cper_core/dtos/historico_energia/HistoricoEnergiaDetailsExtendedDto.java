package com.example.cper_core.dtos.historico_energia;

import com.example.cper_core.dtos.pedido_geracao.PedidoGeracaoDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class HistoricoEnergiaDetailsExtendedDto extends HistoricoEnergiaDetailsDto {

    @NotNull(message = "O pedido geracao é obrigatório")
    @Valid
    private PedidoGeracaoDto pedidoGeracao;
}
