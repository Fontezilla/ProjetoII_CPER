package com.example.cper_core.dtos.solicitacao_energetica;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class SolicitacaoEnergeticaFiltroDto {

    private Integer id;
    private LocalDate dataSolicitacaoInicio;
    private LocalDate dataSolicitacaoFim;
    private Integer tipoEnergia;
    private BigDecimal qtdSolicitadaMin;
    private BigDecimal qtdSolicitadaMax;
    private LocalDate prazoEntregaInicio;
    private LocalDate prazoEntregaFim;
    private Integer prioridade;
    private Integer estado;
    private Integer idCliente;
    private Integer idContrato;
}

