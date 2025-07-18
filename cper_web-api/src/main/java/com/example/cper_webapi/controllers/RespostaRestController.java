package com.example.cper_webapi.controllers;

import com.example.cper_core.dtos.resposta.RespostaDetailsDto;
import com.example.cper_core.dtos.resposta.RespostaDetailsExtendedDto;
import com.example.cper_core.dtos.resposta.RespostaFiltroDto;
import com.example.cper_core.services.interfaces.IRespostaService;
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
@RequestMapping("/api/respostas")
@Tag(name = "Respostas", description = "Consulta e criação de respostas a notificações ou tickets")
public class RespostaRestController {

    private final IRespostaService respostaService;
    private final ITicketService ticketService;
    private final JwtRequestUtils jwtRequestUtils;

    public RespostaRestController(IRespostaService respostaService, ITicketService ticketService, JwtRequestUtils jwtRequestUtils) {
        this.respostaService = respostaService;
        this.ticketService = ticketService;

        this.jwtRequestUtils = jwtRequestUtils;
    }

    @GetMapping
    @Operation(summary = "Listar respostas de um ticket do cliente")
    public ResponseEntity<Page<RespostaDetailsDto>> listarPorTicket(
            @Parameter(hidden = true) Pageable pageable,
            @RequestParam Integer ticketId,
            HttpServletRequest request
    ) {
        Integer idCliente = jwtRequestUtils.getUserIdFromRequest(request);


        if (!ticketService.ticketBelongsToClient(ticketId, idCliente)) {
            return ResponseEntity.status(403).build();
        }

        RespostaFiltroDto filtro = new RespostaFiltroDto();
        filtro.setIdsTicket(Set.of(ticketId));

        return ResponseEntity.ok(respostaService.listFiltered(pageable, filtro));
    }


    @PostMapping
    @Operation(summary = "Criar nova resposta a um ticket")
    public ResponseEntity<RespostaDetailsExtendedDto> criar(
            @Valid @RequestBody RespostaDetailsExtendedDto dto,
            HttpServletRequest request
    ) {
        Integer idCliente = jwtRequestUtils.getUserIdFromRequest(request);

        if (dto.getTicket() == null || !ticketService.ticketBelongsToClient(dto.getTicket().getId(), idCliente)) {
            return ResponseEntity.status(403).build();
        }

        return ResponseEntity.ok(respostaService.create(dto));
    }
}
