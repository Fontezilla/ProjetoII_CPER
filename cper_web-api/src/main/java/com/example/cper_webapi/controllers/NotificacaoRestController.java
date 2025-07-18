package com.example.cper_webapi.controllers;

import com.example.cper_core.dtos.notificacao.*;
import com.example.cper_core.services.interfaces.INotificacaoDestinatarioService;
import com.example.cper_core.services.interfaces.INotificacaoService;
import com.example.cper_webapi.utils.JwtRequestUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notificacoes")
@Tag(name = "Notificações", description = "Consulta de notificações enviadas ao cliente")
public class NotificacaoRestController {

    private final INotificacaoService notificacaoService;
    private final INotificacaoDestinatarioService notificacaoDestinatarioService;
    private final JwtRequestUtils jwtRequestUtils;

    public NotificacaoRestController(INotificacaoService notificacaoService, INotificacaoDestinatarioService notificacaoDestinatarioService, JwtRequestUtils jwtRequestUtils) {
        this.notificacaoService = notificacaoService;
        this.notificacaoDestinatarioService = notificacaoDestinatarioService;
        this.jwtRequestUtils = jwtRequestUtils;
    }

    @GetMapping
    @Operation(summary = "Listar notificações com filtros e paginação")
    public ResponseEntity<Page<NotificacaoDetailsDto>> listar(
            @Parameter(hidden = true) Pageable pageable,
            @Parameter(description = "Filtro por título, data, etc.") NotificacaoFiltroDto filtro,
            HttpServletRequest request
    ) {
        Integer idCliente = jwtRequestUtils.getUserIdFromRequest(request);
        filtro.setClienteId(idCliente);
        return ResponseEntity.ok(notificacaoService.listFiltered(pageable, filtro));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obter detalhes de uma notificação por ID")
    public ResponseEntity<NotificacaoDetailsExtendedDto> getById(@PathVariable Integer id, HttpServletRequest request) {
        Integer idCliente = jwtRequestUtils.getUserIdFromRequest(request);
        NotificacaoDetailsExtendedDto dto = notificacaoService.getExtendedById(id);

        if (!notificacaoDestinatarioService.findClient(id, idCliente)) {
            return ResponseEntity.status(403).build();
        }

        return ResponseEntity.ok(dto);
    }
}