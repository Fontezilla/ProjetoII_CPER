package com.example.cper_core.dtos.equipa;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
public class EquipaFiltroDto {

    private Integer id;

    private String nome;

    private String areaAtuacao;

    private OffsetDateTime dataCriacaoInicio;
    private OffsetDateTime dataCriacaoFim;

    private Boolean isDeleted = false;

    private Set<Integer> idsDepartamento;
    private Set<Integer> idsFuncionario;
}

