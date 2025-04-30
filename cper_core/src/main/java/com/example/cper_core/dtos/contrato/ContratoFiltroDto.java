package com.example.cper_core.dtos.contrato;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Data
public class ContratoFiltroDto {

    private Integer id;
    private LocalDate dataInicioInicio;
    private LocalDate dataInicioFim;
    private LocalDate dataFimInicio;
    private LocalDate dataFimFim;
    private Integer tipoContrato;
    private BigDecimal qtdEnergiaMin;
    private BigDecimal qtdEnergiaMax;
    private Integer prazoPagamentoMin;
    private Integer prazoPagamentoMax;
    private Integer multaAtrasoMin;
    private Integer multaAtrasoMax;

    private Integer nPorta;

    // Relationships

    private Set<Integer> idFuncionario;
    private Set<Integer> idEndereco;
    private Set<Integer> estado;
}

