package com.example.cper_core.dtos.departamento;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class DepartamentoFiltroDto {

    private Integer id;
    private String nome;
    private String descricao;
    private Integer setor;
    private LocalDate dataCriacaoInicio;
    private LocalDate dataCriacaoFim;
    private Integer numFuncionariosMin;
    private Integer numFuncionariosMax;
    private BigDecimal orcamentoMin;
    private BigDecimal orcamentoMax;
}
