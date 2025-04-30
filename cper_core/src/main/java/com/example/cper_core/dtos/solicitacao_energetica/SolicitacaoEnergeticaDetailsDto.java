package com.example.cper_core.dtos.solicitacao_energetica;

import com.example.cper_core.entities.SolicitacaoEnergetica;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/**
 * DTO for {@link SolicitacaoEnergetica}
 */

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SolicitacaoEnergeticaDetailsDto extends SolicitacaoEnergeticaDto implements Serializable {

    @NotNull(message = "O campo data de solicitação não pode ser nulo")
    private LocalDate dataSolicitacao;

    @NotNull(message = "O campo tipo de energia não pode ser nulo")
    private Integer tipoEnergia;

    @NotNull(message = "O campo quantidade solicitada não pode ser nulo")
    @Positive(message = "A quantidade solicitada deve ser um valor positivo")
    private BigDecimal qtdSolicitada;

    @NotNull(message = "O campo prioridade não pode ser nulo")
    private String prioridade;

    @NotNull(message = "O campo estado não pode ser nulo")
    private String estado;

    public SolicitacaoEnergeticaDetailsDto(Integer id, LocalDate dataSolicitacao, Integer tipoEnergia, BigDecimal qtdSolicitada, String prioridade, String estado) {
        super(id);
        this.dataSolicitacao = dataSolicitacao;
        this.tipoEnergia = tipoEnergia;
        this.qtdSolicitada = qtdSolicitada;
        this.prioridade = prioridade;
        this.estado = estado;
    }
}