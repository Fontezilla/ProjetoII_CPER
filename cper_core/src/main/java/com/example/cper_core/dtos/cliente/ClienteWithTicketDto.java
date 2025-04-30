package com.example.cper_core.dtos.cliente;

import com.example.cper_core.dtos.ticket.TicketDto;
import com.example.cper_core.entities.Cliente;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Set;

/**
 * DTO for {@link Cliente}
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class ClienteWithTicketDto extends ClienteDto implements Serializable {
    private Set<TicketDto> tickets;

    public ClienteWithTicketDto(Integer id, Set<TicketDto> tickets) {
        super(id);
        this.tickets = tickets;
    }
}