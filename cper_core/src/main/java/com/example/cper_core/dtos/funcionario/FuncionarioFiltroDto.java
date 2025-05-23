package com.example.cper_core.dtos.funcionario;

import com.example.cper_core.enums.EstadoFuncionario;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
public class FuncionarioFiltroDto {

    private Integer id;

    private String nome;
    private String nif;
    private String email;
    private String telefone;

    private OffsetDateTime dataContratacaoInicio;
    private OffsetDateTime dataContratacaoFim;

    private BigDecimal salarioMin;
    private BigDecimal salarioMax;

    private EstadoFuncionario estado;

    private Boolean isDeleted = false;

    private Set<Integer> idsDepartamento;
    private Set<Integer> idsEndereco;
}