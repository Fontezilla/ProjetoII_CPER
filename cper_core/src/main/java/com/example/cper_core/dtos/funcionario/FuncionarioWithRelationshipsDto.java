package com.example.cper_core.dtos.funcionario;

import com.example.cper_core.dtos.anomalia.AnomaliaDto;
import com.example.cper_core.dtos.armazem.ArmazemDto;
import com.example.cper_core.dtos.contrato.ContratoDto;
import com.example.cper_core.dtos.departamento.DepartamentoDto;
import com.example.cper_core.dtos.equipa.EquipaDto;
import com.example.cper_core.dtos.fatura.FaturaDto;
import com.example.cper_core.dtos.notificacao_destinario.NotificacaoDestinatarioDto;
import com.example.cper_core.dtos.pedido_geracao.PedidoGeracaoDto;
import com.example.cper_core.dtos.resposta.RespostaDto;
import com.example.cper_core.dtos.solicitacao_energetica.SolicitacaoEnergeticaDto;
import com.example.cper_core.dtos.ticket.TicketDto;
import com.example.cper_core.dtos.centro_producao.CentroProducaoDto;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class FuncionarioWithRelationshipsDto extends FuncionarioDto {

    @Valid
    private Set<AnomaliaDto> responsavelAnomalias;

    @Valid
    private Set<ContratoDto> responsavelContratos;

    @Valid
    private Set<PedidoGeracaoDto> responsavelPedidos;

    @Valid
    private Set<TicketDto> responsavelTickets;

    @Valid
    private Set<EquipaDto> responsavelEquipas;

    @Valid
    private Set<FaturaDto> responsavelFaturas;

    @Valid
    private Set<RespostaDto> respostas;

    @Valid
    private Set<ArmazemDto> responsavelArmazens;

    @Valid
    private Set<CentroProducaoDto> responsavelCentros;

    @Valid
    private Set<NotificacaoDestinatarioDto> notificacoes;

    @Valid
    private Set<ArmazemDto> armazens;

    @Valid
    private Set<CentroProducaoDto> centrosProducao;

    @Valid
    private Set<SolicitacaoEnergeticaDto> solicitacoesEnergeticas;

    @Valid
    private Set<EquipaDto> equipas;

    @Valid
    private Set<DepartamentoDto> departamentos;
}