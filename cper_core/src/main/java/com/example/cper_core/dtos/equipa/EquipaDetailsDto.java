package com.example.cper_core.dtos.equipa;

import com.example.cper_core.dtos.departamento.DepartamentoDto;
import com.example.cper_core.dtos.funcionario.FuncionarioDto;
import com.example.cper_core.entities.Equipa;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link Equipa}
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class EquipaDetailsDto extends EquipaDto implements Serializable {
    @NotBlank(message = "O campo nome não pode estar vazio")
    private String nome;

    @NotNull(message = "O campo data de criação não pode ser nulo")
    private LocalDate dataCriacao;

    @NotBlank(message = "O campo área de atuação não pode estar vazio")
    private String areaAtuacao;

    @NotNull(message = "O campo departamento não pode ser nulo")
    private DepartamentoDto departamento;

    private FuncionarioDto funcionario;

    public EquipaDetailsDto(Integer id, String nome, LocalDate dataCriacao, String areaAtuacao, DepartamentoDto departamento, FuncionarioDto funcionario) {
        super(id);
        this.nome = nome;
        this.dataCriacao = dataCriacao != null ? dataCriacao : LocalDate.now();
        this.areaAtuacao = areaAtuacao;
        this.departamento = departamento;
        this.funcionario = funcionario;
    }
}