package com.example.cper_core.dtos.inspecao;

import lombok.Data;

import java.time.LocalDate;

@Data
public class InspecaoFiltroDto {

    private Integer id;
    private String descricao;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private Integer tipo;
    private String areaInspecionada;
    private String resultados;
    private Integer estado;

    // Relacionamentos (IDs para filtro)
    private Integer idCentroProducao;
    private Integer idAnomalia;
    private Integer idEquipa;
}
