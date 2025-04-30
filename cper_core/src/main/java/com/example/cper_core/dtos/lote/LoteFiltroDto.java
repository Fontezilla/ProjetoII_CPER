package com.example.cper_core.dtos.lote;

import lombok.Data;

import java.time.LocalDate;

@Data
public class LoteFiltroDto {

    private Integer idStock;
    private Integer idMaterial;
    private Integer qtdMin;
    private Integer qtdMax;
    private LocalDate dataValidadeInicio;
    private LocalDate dataValidadeFim;
    private LocalDate dataAdicaoInicio;
    private LocalDate dataAdicaoFim;
}

