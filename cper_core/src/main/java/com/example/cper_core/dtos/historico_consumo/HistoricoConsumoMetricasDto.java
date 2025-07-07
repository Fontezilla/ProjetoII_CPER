package com.example.cper_core.dtos.historico_consumo;

import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
public class HistoricoConsumoMetricasDto {
    private int totalRegistos;
    private BigDecimal energiaTotal;
    private BigDecimal consumoMedioPorHora;
    private BigDecimal energiaMaxima;
    private BigDecimal energiaMinima;
    private OffsetDateTime dataMaisAntiga;
    private OffsetDateTime dataMaisRecente;
}