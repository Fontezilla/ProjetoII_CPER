package com.example.cper_core.dtos.fatura;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class FaturaFiltroDto {

    private Integer id;
    private LocalDate dataEmissaoInicio;
    private LocalDate dataEmissaoFim;
    private LocalDate dataVencimentoInicio;
    private LocalDate dataVencimentoFim;
    private BigDecimal vMultaMin;
    private BigDecimal vMultaMax;
    private BigDecimal vTotalMin;
    private BigDecimal vTotalMax;
    private BigDecimal qtdEnergiaMin;
    private BigDecimal qtdEnergiaMax;
    private Integer estado;
    private Integer idContrato;
    private Integer idFuncionario;

}