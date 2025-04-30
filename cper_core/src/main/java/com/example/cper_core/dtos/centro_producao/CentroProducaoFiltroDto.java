package com.example.cper_core.dtos.centro_producao;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Data
public class CentroProducaoFiltroDto {

    private Integer id;
    private String nome;
    private Integer tipoEnergia;
    private BigDecimal capacidadeMaxMin;
    private BigDecimal capacidadeMaxMax;
    private LocalDate dataInicioInicio;
    private LocalDate dataInicioFim;
    private BigDecimal custoOperacionalMin;
    private BigDecimal custoOperacionalMax;
    private Integer estado;
    private Integer nPorta;

    // Relationships

    private Set<Integer> idDepartamento;
    private Set<Integer> idEndereco;
}

