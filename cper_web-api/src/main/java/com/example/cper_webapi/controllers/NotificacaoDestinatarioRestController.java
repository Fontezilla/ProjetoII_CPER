package com.example.cper_webapi.controllers;

import com.example.cper_core.dtos.notificacao_destinario.NotificacaoDestinatarioDetailsDto;
import com.example.cper_core.dtos.notificacao_destinario.NotificacaoDestinatarioFiltroDto;
import com.example.cper_core.services.interfaces.INotificacaoDestinatarioService;
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
@RequestMapping("/api/notificacao-destinatario")
@Tag(name = "Notificações Recebidas", description = "Consulta de notificações direcionadas ao cliente")
public class NotificacaoDestinatarioRestController {

    private final INotificacaoDestinatarioService service;
    private final JwtRequestUtils jwtRequestUtils;

    public NotificacaoDestinatarioRestController(INotificacaoDestinatarioService service, JwtRequestUtils jwtRequestUtils) {
        this.service = service;
        this.jwtRequestUtils = jwtRequestUtils;
    }

    @GetMapping
    @Operation(summary = "Listar notificações recebidas com filtros e paginação")
    public ResponseEntity<Page<NotificacaoDestinatarioDetailsDto>> listar(
            @Parameter(hidden = true) Pageable pageable,
            @Parameter(description = "Filtros por cliente, lida/não lida, etc.") NotificacaoDestinatarioFiltroDto filtro,
            HttpServletRequest request
    ) {
        Integer idCliente = jwtRequestUtils.getUserIdFromRequest(request);
        filtro.setIdCliente(idCliente); // força o filtro a usar apenas o cliente autenticado
        return ResponseEntity.ok(service.listFiltered(pageable, filtro));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obter detalhes de uma notificação recebida por ID")
    public ResponseEntity<NotificacaoDestinatarioDetailsDto> getById(@PathVariable Integer id, HttpServletRequest request) {
        Integer idCliente = jwtRequestUtils.getUserIdFromRequest(request);
        NotificacaoDestinatarioDetailsDto dto = service.getExtendedById(id);
        if (!dto.getCliente().equals(idCliente)) {
            return ResponseEntity.status(403).build();
        }
        return ResponseEntity.ok(dto);
    }
}
