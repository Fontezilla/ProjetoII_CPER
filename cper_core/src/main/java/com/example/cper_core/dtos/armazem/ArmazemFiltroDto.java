package com.example.cper_core.dtos.armazem;

import com.example.cper_core.enums.EstadoArmazem;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Set;

@Data
@NoArgsConstructor
public class ArmazemFiltroDto {

    private Integer id;

    private String nome;

    private BigDecimal capacidadeTotal;

    private BigDecimal capacidadeOcupada;

    private EstadoArmazem estado;

    private String nPorta;

    private Boolean isDeleted = false;

    private Set<Integer> idsDepartamento;
    private Set<Integer> idsEndereco;
    private Set<Integer> idsFuncionario;
}