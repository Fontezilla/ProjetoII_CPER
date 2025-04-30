package com.example.cper_core.dtos.ticket;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link com.example.cper_core.entities.Ticket}
 */

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TicketDetailsDto extends TicketDto implements Serializable {

    @NotBlank(message = "A descrição não pode ser vazia")
    private String descricao;

    @NotNull(message = "O tipo de ticket não pode ser nulo")
    @Positive(message = "O tipo de ticket deve ser um valor positivo")
    private Integer tipoTicket;

    @NotNull(message = "A prioridade não pode ser nula")
    @Positive(message = "A prioridade deve ser um valor positivo")
    private String prioridade;

    @NotNull(message = "O estado não pode ser nulo")
    @Positive(message = "O estado deve ser um valor positivo")
    private String estado;

    public TicketDetailsDto(Integer id, String descricao, Integer tipoTicket, String prioridade, String estado) {
        super(id);
        this.descricao = descricao;
        this.tipoTicket = tipoTicket;
        this.prioridade = prioridade;
        this.estado = estado;
    }
}