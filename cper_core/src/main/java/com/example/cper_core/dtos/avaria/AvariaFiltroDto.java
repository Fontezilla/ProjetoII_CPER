package com.example.cper_core.dtos.avaria;

import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class AvariaFiltroDto {

    private Integer id;
    private String descricao;
    private LocalDate dataInicioInicio;
    private LocalDate dataInicioFim;
    private LocalDate dataResolucaoInicio;
    private LocalDate dataResolucaoFim;
    private Integer gravidade;
    private String impactoProducao;
    private Integer estado;

    // Relationships

    private Set<Integer> idCentroProducao;
    private Set<Integer> idInspecao;
    private Set<Integer> idEquipa;
    private Set<Integer> idPedidoMaterial;
}