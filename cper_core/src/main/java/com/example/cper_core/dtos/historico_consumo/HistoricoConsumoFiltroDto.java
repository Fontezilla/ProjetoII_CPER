package com.example.cper_core.dtos.historico_consumo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
public class HistoricoConsumoFiltroDto {

    private Integer id;

    private OffsetDateTime dataRegistoInicio;
    private OffsetDateTime dataRegistoFim;

    private BigDecimal energiaTotalMin;
    private BigDecimal energiaTotalMax;

    private BigDecimal consumoPorHoraMin;
    private BigDecimal consumoPorHoraMax;

    private Boolean isDeleted = false;

    private Set<Integer> idsCliente;
}
