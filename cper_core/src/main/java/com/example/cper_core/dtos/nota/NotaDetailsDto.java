package com.example.cper_core.dtos.nota;

import com.example.cper_core.entities.Nota;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link Nota}
 */

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class NotaDetailsDto extends NotaDto implements Serializable {
    @NotBlank(message = "A descrição não pode estar vazia")
    private String descricao;

    @NotNull(message = "A prioridade não pode ser nula")
    @PositiveOrZero(message = "A prioridade deve ser zero ou positiva")
    private String prioridade;

    @NotBlank(message = "O título não pode estar vazio")
    private String titulo;

    public NotaDetailsDto(Integer id, String descricao, String prioridade, String titulo) {
        super(id);
        this.descricao = descricao;
        this.prioridade = prioridade;
        this.titulo = titulo;
    }
}