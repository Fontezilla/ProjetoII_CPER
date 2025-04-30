package com.example.cper_core.dtos.departamento;

import com.example.cper_core.entities.Departamento;
import com.example.cper_core.enums.Setor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link Departamento}
 */

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class DepartamentoDetailsDto extends DepartamentoDto implements Serializable {
    @NotBlank(message = "O campo nome não pode estar vazio")
    private String nome;

    @NotBlank(message = "O campo descrição não pode estar vazio")
    private String descricao;

    @NotNull(message = "O campo setor não pode estar vazio")
    private String setor;


    public DepartamentoDetailsDto(Integer id, String nome, String descricao, String setor) {
        super(id);
        this.nome = nome;
        this.descricao = descricao;
        this.setor = setor != null ? setor : Setor.TEMPORARIO.toString();
    }
}