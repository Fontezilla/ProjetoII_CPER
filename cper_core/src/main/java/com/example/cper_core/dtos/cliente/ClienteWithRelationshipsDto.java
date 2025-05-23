package com.example.cper_core.dtos.cliente;

import com.example.cper_core.dtos.historico_consumo.HistoricoConsumoDto;
import com.example.cper_core.dtos.notificacao_destinario.NotificacaoDestinatarioDto;
import com.example.cper_core.dtos.resposta.RespostaDto;
import com.example.cper_core.dtos.solicitacao_energetica.SolicitacaoEnergeticaDto;
import com.example.cper_core.dtos.ticket.TicketDto;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ClienteWithRelationshipsDto extends ClienteDto {

    @Valid
    private Set<HistoricoConsumoDto> historicoConsumos;

    @Valid
    private Set<SolicitacaoEnergeticaDto> solicitacaoEnergeticas;

    @Valid
    private Set<TicketDto> tickets;

    @Valid
    private Set<RespostaDto> respostas;

    @Valid
    private Set<NotificacaoDestinatarioDto> notificacoes;
}
