package com.example.cper_core.dtos.fatura;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
public class FaturaFiltroDto {

    private Integer id;

    private OffsetDateTime dataEmissaoInicio;
    private OffsetDateTime dataEmissaoFim;

    private OffsetDateTime dataVencimentoInicio;
    private OffsetDateTime dataVencimentoFim;

    private BigDecimal vElectricidadeMin;
    private BigDecimal vElectricidadeMax;

    private BigDecimal vMultaMin;
    private BigDecimal vMultaMax;

    private BigDecimal qtdEnergiaMin;
    private BigDecimal qtdEnergiaMax;

    private Integer estado;

    private Integer taxa;

    private Boolean isDeleted = false;

    private Set<Integer> idsContrato;
    private Set<Integer> idsFuncionario;
}

