package com.example.cper_core.dtos.anomalia;

import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class AnomaliaFiltroDto {

    private Integer id;

    private Integer tipoAnomalia;

    private String descricao;

    private LocalDate dataIdentificacaoInicio;

    private LocalDate dataIdentificacaoFim;

    private String localizacao;

    private Integer gravidade;

    private Integer estado;

    // Relationships

    private Set<Integer> idsCentroProducao;

    private Set<Integer> idsFuncionario;
}