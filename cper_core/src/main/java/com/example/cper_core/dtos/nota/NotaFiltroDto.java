package com.example.cper_core.dtos.nota;

import com.example.cper_core.enums.Prioridade;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
public class NotaFiltroDto {

    private Integer id;

    private String titulo;
    private String descricao;

    private LocalDate dataCriacaoInicio;
    private LocalDate dataCriacaoFim;

    private Prioridade prioridade;

    private Boolean isDeleted = false;

    private Set<Integer> idsInspecao;
    private Set<Integer> idsAnomalia;
    private Set<Integer> idsAvaria;
    private Set<Integer> idsSolicitacaoEnergetica;
}