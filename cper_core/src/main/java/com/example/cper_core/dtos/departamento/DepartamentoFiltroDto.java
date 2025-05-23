package com.example.cper_core.dtos.departamento;

import com.example.cper_core.enums.Setor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
public class DepartamentoFiltroDto {

    private Integer id;

    private String nome;

    private Setor setor;

    private OffsetDateTime dataCriacaoInicio;
    private OffsetDateTime dataCriacaoFim;

    private Integer numFuncionariosMin;
    private Integer numFuncionariosMax;

    private Boolean isDeleted = false;

    private BigDecimal orcamentoMin;
    private BigDecimal orcamentoMax;
}
