package com.example.cper_core.dtos.funcionario;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class FuncionarioFiltroDto {

    private Integer id;
    private String nome;
    private String nif;
    private String email;
    private String telefone;
    private LocalDate dataNascimentoInicio;
    private LocalDate dataNascimentoFim;
    private LocalDate dataContratacaoInicio;
    private LocalDate dataContratacaoFim;
    private BigDecimal salarioMin;
    private BigDecimal salarioMax;
    private String cargo;
    private Integer estado;
    private Integer idDepartamento;
    private Integer idEndereco;
    private Integer nPorta;
}
