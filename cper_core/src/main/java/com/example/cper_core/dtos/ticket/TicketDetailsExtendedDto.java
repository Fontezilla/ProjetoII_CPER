package com.example.cper_core.dtos.ticket;

import com.example.cper_core.entities.Ticket;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import com.example.cper_core.dtos.cliente.ClienteDto;
import com.example.cper_core.dtos.funcionario.FuncionarioDto;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link Ticket}
 */
public class TicketDetailsExtendedDto extends TicketDetailsDto implements Serializable {

    @NotNull(message = "A data de início não pode ser nula")
    private LocalDate dataIni;

    @NotNull(message = "A data de fim não pode ser nula")
    private LocalDate dataFim;

    @NotBlank(message = "O comentário não pode ser vazio")
    private String comentario;

    @NotNull(message = "O funcionário não pode ser nulo")
    private FuncionarioDto funcionario;

    @NotNull(message = "O cliente não pode ser nulo")
    private ClienteDto cliente;

    public TicketDetailsExtendedDto(Integer id, String descricao, Integer tipoTicket, String prioridade, String estado, LocalDate dataIni, LocalDate dataFim, String comentario, FuncionarioDto funcionario, ClienteDto cliente) {
        super(id, descricao, tipoTicket, prioridade, estado);
        this.dataIni = dataIni;
        this.dataFim = dataFim;
        this.comentario = comentario;
        this.funcionario = funcionario;
        this.cliente = cliente;
    }
}