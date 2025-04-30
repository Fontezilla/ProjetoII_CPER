package com.example.cper_core.dtos.resposta;

import com.example.cper_core.dtos.cliente.ClienteDto;
import com.example.cper_core.dtos.funcionario.FuncionarioDto;
import com.example.cper_core.dtos.ticket.TicketDto;
import com.example.cper_core.entities.Resposta;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link Resposta}
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class RespostaDetailsDto extends RespostaDto implements Serializable {
    @NotBlank(message = "O campo resposta não pode estar vazio")
    private String resposta;

    @NotNull(message = "O campo data da resposta não pode ser nulo")
    private LocalDate dataResposta;

    @NotNull(message = "O ticket associado não pode ser nulo")
    private TicketDto ticket;

    private FuncionarioDto funcionario;

    private ClienteDto cliente;

    public RespostaDetailsDto(Integer id, String resposta, LocalDate dataResposta, TicketDto ticket, FuncionarioDto funcionario, ClienteDto cliente) {
        super(id);
        this.resposta = resposta;
        this.dataResposta = dataResposta != null ? dataResposta : LocalDate.now();
        this.ticket = ticket;
        this.funcionario = funcionario;
        this.cliente = cliente;
    }
}