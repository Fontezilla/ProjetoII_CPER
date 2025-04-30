package com.example.cper_core.dtos.comentario;

import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class ComentarioFiltroDto {

    private Integer id;
    private String descricao;
    private LocalDate dataCriacaoInicio;
    private LocalDate dataCriacaoFim;

    // Relationships

    private Set<Integer> idSolicitacaoEnergetica;
    private Set<Integer> idFuncionario;
}
