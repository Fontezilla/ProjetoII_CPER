package com.example.cper_core.dtos.comentario;

import com.example.cper_core.dtos.funcionario.FuncionarioDto;
import com.example.cper_core.dtos.solicitacao_energetica.SolicitacaoEnergeticaDto;
import com.example.cper_core.entities.Comentario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link Comentario}
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class ComentarioDetailsDto extends ComentarioDto implements Serializable {
    @NotBlank(message = "O campo descrição não pode estar vazio")
    private String descricao;

    @NotNull(message = "O campo data de criação não pode ser nulo")
    private LocalDate dataCriacao;

    @NotNull(message = "O campo funcionário não pode ser nulo")
    private FuncionarioDto funcionario;

    @NotNull(message = "O campo solicitação energética não pode ser nulo")
    private SolicitacaoEnergeticaDto solicitacaoEnergetica;

    public ComentarioDetailsDto(Integer id, String descricao, LocalDate dataCriacao, FuncionarioDto funcionario, SolicitacaoEnergeticaDto solicitacaoEnergetica) {
        super(id);
        this.descricao = descricao;
        this.dataCriacao = dataCriacao != null ? dataCriacao : LocalDate.now();
        this.funcionario = funcionario;
        this.solicitacaoEnergetica = solicitacaoEnergetica;
    }
}
