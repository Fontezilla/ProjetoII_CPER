package com.example.cper_core.dtos.armazem;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class ArmazemFiltroDto {

    private Integer id;

    private String nome;

    private LocalDate dataCriacaoInicio;

    private LocalDate dataCriacaoFim;

    private LocalDate dataUpdateInicio;

    private LocalDate dataUpdateFim;

    private Integer capacidadeTotalMin;

    private Integer capacidadeTotalMax;

    private Integer capacidadeOcupadaMin;

    private Integer capacidadeOcupadaMax;

    private Integer nPorta;

    private Integer estado;

    // Relationships

    private Set<Integer> idDepartamento;

    private Set<Integer> idEndereco;

    private Set<Integer> idResponsavel;
}
