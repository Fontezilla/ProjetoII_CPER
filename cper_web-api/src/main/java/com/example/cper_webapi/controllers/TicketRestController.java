package com.example.cper_webapi.controllers;

import com.example.cper_core.dtos.cliente.ClienteDto;
import com.example.cper_core.dtos.ticket.TicketDetailsDto;
import com.example.cper_core.dtos.ticket.TicketDetailsExtendedDto;
import com.example.cper_core.dtos.ticket.TicketFiltroDto;
import com.example.cper_core.services.interfaces.ITicketService;
import com.example.cper_webapi.utils.JwtRequestUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/tickets")
@Tag(name = "Tickets de Suporte", description = "Criação e consulta de pedidos de ajuda/reclamações")
public class TicketRestController {

    private final ITicketService ticketService;
    private final JwtRequestUtils jwtRequestUtils;

    public TicketRestController(ITicketService ticketService, JwtRequestUtils jwtRequestUtils) {
        this.ticketService = ticketService;
        this.jwtRequestUtils = jwtRequestUtils;
    }

    @GetMapping
    @Operation(summary = "Listar tickets com filtros e paginação")
    public ResponseEntity<Page<TicketDetailsDto>> listar(
            @Parameter(hidden = true) Pageable pageable,
            @Parameter(description = "Filtro por estado, cliente, etc.") TicketFiltroDto filtro,
            HttpServletRequest request
    ) {
        Integer idCliente = jwtRequestUtils.getUserIdFromRequest(request);
        filtro.setIdsCliente(Set.of(idCliente));
        return ResponseEntity.ok(ticketService.listFiltered(pageable, filtro));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obter detalhes de um ticket por ID")
    public ResponseEntity<TicketDetailsExtendedDto> getById(@PathVariable Integer id, HttpServletRequest request) {
        Integer idCliente = jwtRequestUtils.getUserIdFromRequest(request);

        if (!ticketService.ticketBelongsToClient(id, idCliente)) {
            return ResponseEntity.status(403).build();
        }

        return ResponseEntity.ok(ticketService.getExtendedById(id));
    }

    @PostMapping
    @Operation(summary = "Criar novo ticket")
    public ResponseEntity<TicketDetailsExtendedDto> criar(@Valid @RequestBody TicketDetailsExtendedDto dto, HttpServletRequest request) {
        Integer idCliente = jwtRequestUtils.getUserIdFromRequest(request);
        dto.setCliente(new ClienteDto(idCliente));
        return ResponseEntity.ok(ticketService.create(dto));
    }
}