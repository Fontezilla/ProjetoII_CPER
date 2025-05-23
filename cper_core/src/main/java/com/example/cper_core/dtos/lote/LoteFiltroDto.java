package com.example.cper_core.dtos.lote;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
public class LoteFiltroDto {

    private Integer id;

    private String codigoLote;

    private OffsetDateTime dataProducaoInicio;
    private OffsetDateTime dataProducaoFim;

    private OffsetDateTime dataValidadeInicio;
    private OffsetDateTime dataValidadeFim;

    private BigDecimal quantidadeTotalMin;
    private BigDecimal quantidadeTotalMax;

    private BigDecimal quantidadeDisponivelMin;
    private BigDecimal quantidadeDisponivelMax;

    private Boolean isDeleted = false;

    private Set<Integer> idsMaterial;
}

