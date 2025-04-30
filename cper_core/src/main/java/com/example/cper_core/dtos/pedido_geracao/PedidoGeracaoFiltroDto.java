package com.example.cper_core.dtos.pedido_geracao;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class PedidoGeracaoFiltroDto {

    private Integer id;
    private LocalDate dataCriacaoInicio;
    private LocalDate dataCriacaoFim;
    private LocalDate dataPrevisaoInicio;
    private LocalDate dataPrevisaoFim;
    private BigDecimal qtdEnergiaMin;
    private BigDecimal qtdEnergiaMax;
    private Integer tipoEnergia;
    private Integer prioridade;
    private Integer estado;
    private Integer idContrato;
    private Integer idCentroProducao;
    private Integer idFuncionario;
}

