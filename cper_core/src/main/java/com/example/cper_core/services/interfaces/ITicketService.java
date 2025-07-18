package com.example.cper_core.services.interfaces;

import com.example.cper_core.dtos.ticket.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ITicketService extends IXService<
        TicketDto,
        TicketDetailsDto,
        TicketDetailsExtendedDto,
        TicketFiltroDto,
        Integer
        > {
    public boolean ticketBelongsToClient(Integer ticketId, Integer clientId);
}
