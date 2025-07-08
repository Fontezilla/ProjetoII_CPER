package com.example.cper_core.dtos.historico_energia;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
public class HistoricoEnergiaFiltroDto {

    private Integer id;

    private OffsetDateTime dataIncio;
    private OffsetDateTime dataFIm;

    private BigDecimal energiaGeradaMin;
    private BigDecimal energiaGeradaMax;

    private BigDecimal energiaHoraMin;
    private BigDecimal energiaHoraMax;

    private Set<Integer> idsPedidosGeracao;
}
