package com.example.cper_webapi.controllers;

import com.example.cper_core.dtos.cliente.ClienteDto;
import com.example.cper_core.dtos.solicitacao_energetica.SolicitacaoEnergeticaDetailsDto;
import com.example.cper_core.dtos.solicitacao_energetica.SolicitacaoEnergeticaDetailsExtendedDto;
import com.example.cper_core.dtos.solicitacao_energetica.SolicitacaoEnergeticaFiltroDto;
import com.example.cper_core.services.interfaces.ISolicitacaoEnergeticaService;
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
@RequestMapping("/api/solicitacoes-energeticas")
@Tag(name = "Solicitações Energéticas", description = "Registo e consulta de pedidos de fornecimento ou alteração energética")
public class SolicitacaoEnergeticaRestController {

    private final ISolicitacaoEnergeticaService service;
    private final JwtRequestUtils jwtRequestUtils;

    public SolicitacaoEnergeticaRestController(ISolicitacaoEnergeticaService service, JwtRequestUtils jwtRequestUtils) {
        this.service = service;
        this.jwtRequestUtils = jwtRequestUtils;
    }

    @GetMapping
    @Operation(summary = "Listar solicitações com filtros e paginação")
    public ResponseEntity<Page<SolicitacaoEnergeticaDetailsDto>> listar(
            @Parameter(hidden = true) Pageable pageable,
            @Parameter(description = "Filtro por cliente, estado, etc.") SolicitacaoEnergeticaFiltroDto filtro,
            HttpServletRequest request
    ) {
        Integer idCliente = jwtRequestUtils.getUserIdFromRequest(request);
        filtro.setIdsCliente(Set.of(idCliente));
        return ResponseEntity.ok(service.listFiltered(pageable, filtro));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obter detalhes de uma solicitação por ID")
    public ResponseEntity<SolicitacaoEnergeticaDetailsExtendedDto> getById(
            @PathVariable Integer id,
            HttpServletRequest request
    ) {
        Integer idCliente = jwtRequestUtils.getUserIdFromRequest(request);
        SolicitacaoEnergeticaDetailsExtendedDto dto = service.getExtendedById(id);

        if (dto.getCliente() == null || !idCliente.equals(dto.getCliente().getId())) {
            return ResponseEntity.status(403).build();
        }

        return ResponseEntity.ok(dto);
    }

    @PostMapping
    @Operation(summary = "Criar nova solicitação energética")
    public ResponseEntity<SolicitacaoEnergeticaDetailsExtendedDto> criar(
            @Valid @RequestBody SolicitacaoEnergeticaDetailsExtendedDto dto,
            HttpServletRequest request
    ) {
        Integer idCliente = jwtRequestUtils.getUserIdFromRequest(request);
        dto.setCliente(new ClienteDto(idCliente));
        return ResponseEntity.ok(service.create(dto));
    }
}
