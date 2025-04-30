package com.example.cper_core.services.interfaces;

import com.example.cper_core.dtos.ticket.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ITicketService {

    // Generic CRUD operations

    Page<TicketDetailsDto> listAll(Pageable pageable);

    Page<TicketDetailsDto> listFiltered(Pageable pageable, TicketDto filter);

    Optional<TicketDetailsExtendedDto> getById(Integer id);

    TicketDetailsExtendedDto create(TicketDetailsExtendedDto dto);

    TicketDetailsExtendedDto update(Integer id, TicketDetailsExtendedDto dto);

    void softDelete(Integer id);

    // Association operations: Resposta

    TicketWithRespostaDto linkResposta(Integer idTicket, Integer idResposta);

    TicketWithRespostaDto unlinkResposta(Integer idTicket, Integer idResposta);
}