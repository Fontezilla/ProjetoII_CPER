package com.example.cper_core.dtos.departamento;

import com.example.cper_core.entities.Departamento;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * DTO for {@link Departamento}
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class DepartamentoDetailsExtendedDto extends DepartamentoDetailsDto implements Serializable {
    @NotNull(message = "O campo data de criação não pode ser nulo")
    private LocalDate dataCriacao;

    @NotNull(message = "O campo número de funcionários não pode ser nulo")
    @Positive(message = "O número de funcionários deve ser um valor positivo")
    private Integer numFuncionarios;

    @Positive(message = "O orçamento deve ser um valor positivo")
    private BigDecimal orcamento;


    public DepartamentoDetailsExtendedDto(Integer id, String nome, String descricao, String setor, LocalDate dataCriacao, Integer numFuncionarios, BigDecimal orcamento) {
        super(id, nome, descricao, setor);
        this.dataCriacao = dataCriacao;
        this.numFuncionarios = numFuncionarios != null ? numFuncionarios : 0;
        this.orcamento = orcamento != null ? orcamento : BigDecimal.ZERO;
    }
}