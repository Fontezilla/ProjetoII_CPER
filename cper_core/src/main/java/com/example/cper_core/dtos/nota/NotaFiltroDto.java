package com.example.cper_core.dtos.nota;

import lombok.Data;

import java.time.LocalDate;

@Data
public class NotaFiltroDto {

    private Integer id;
    private String descricao;
    private String titulo;
    private LocalDate dataCriacaoInicio;
    private LocalDate dataCriacaoFim;
    private Integer prioridade;
    private Integer idInspecao;
    private Integer idAnomalia;
    private Integer idAvaria;
}

