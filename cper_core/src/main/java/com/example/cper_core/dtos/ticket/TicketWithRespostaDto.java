package com.example.cper_core.dtos.ticket;

import com.example.cper_core.dtos.resposta.RespostaDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Set;

/**
 * DTO for {@link com.example.cper_core.entities.Ticket}
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class TicketWithRespostaDto extends TicketDto implements Serializable {
    private Set<RespostaDto> respostas;

    public TicketWithRespostaDto(Integer id, Set<RespostaDto> respostas) {
        super(id);
        this.respostas = respostas;
    }
}