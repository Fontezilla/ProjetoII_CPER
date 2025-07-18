package com.example.cper_core.services;
import com.example.cper_core.dtos.ticket.*;
import com.example.cper_core.entities.Ticket;
import com.example.cper_core.mappers.TicketMapper;
import com.example.cper_core.repositories.TicketRepository;
import com.example.cper_core.services.interfaces.ITicketService;
import com.example.cper_core.specifications.TicketSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TicketService extends AbstractXService<
        Ticket,
        TicketDto,
        TicketDetailsDto,
        TicketDetailsExtendedDto,
        TicketFiltroDto,
        Integer
        > implements ITicketService {

    private final TicketMapper ticketMapper;
    private final TicketRepository ticketRepository;

    public TicketService(
            TicketRepository ticketRepository,
            TicketMapper ticketMapper,
            jakarta.validation.Validator validator
    ) {
        super(ticketRepository, ticketRepository, validator);
        this.ticketMapper = ticketMapper;
        this.ticketRepository = ticketRepository;
    }

    @Override
    protected Ticket toEntity(TicketDetailsExtendedDto dto) {
        return ticketMapper.toEntity(dto);
    }

    @Override
    protected void updateEntityFromDto(TicketDetailsExtendedDto dto, Ticket entity) {
        ticketMapper.updateEntityFromExtendedDto(dto, entity);
    }

    @Override
    protected TicketDetailsExtendedDto toExtendedDto(Ticket entity) {
        return ticketMapper.toExtendedDto(entity);
    }

    @Override
    protected TicketDetailsDto toDetailsDto(Ticket entity) {
        return ticketMapper.toDetailsDto(entity);
    }

    @Override
    protected Specification<Ticket> getSpecificationFromFiltro(TicketFiltroDto filtro) {
        return TicketSpecification.filter(filtro);
    }

    @Override
    protected void markedDeleted(Ticket entity) {
        entity.setIsDeleted(true);
    }

    public boolean ticketBelongsToClient(Integer ticketId, Integer clientId) {
        return ticketRepository.isTicketOwnedByClient(ticketId, clientId);
    }
}