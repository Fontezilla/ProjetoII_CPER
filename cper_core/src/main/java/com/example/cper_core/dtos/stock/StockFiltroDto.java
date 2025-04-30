package com.example.cper_core.dtos.stock;

import lombok.Data;

import java.time.LocalDate;

@Data
public class StockFiltroDto {

    private Integer id;
    private Integer qtdMinimaMin;
    private Integer qtdMinimaMax;
    private Integer qtdMaximaMin;
    private Integer qtdMaximaMax;
    private String localizacao;
    private LocalDate dataAdicaoInicio;
    private LocalDate dataAdicaoFim;
}

