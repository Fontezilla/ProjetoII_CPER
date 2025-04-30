package com.example.cper_core.dtos.equipa;

import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class EquipaFiltroDto {

    private Integer id;
    private String nome;
    private String areaAtuacao;
    private LocalDate dataCriacaoInicio;
    private LocalDate dataCriacaoFim;


    private Set<Integer> idDepartamento;
    private Set<Integer> idFuncionario;
}
