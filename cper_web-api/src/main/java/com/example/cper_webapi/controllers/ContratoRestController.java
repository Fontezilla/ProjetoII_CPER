package com.example.cper_webapi.controllers;

import com.example.cper_core.dtos.cliente.ClienteDetailsExtendedDto;
import com.example.cper_core.dtos.contrato.ContratoDetailsDto;
import com.example.cper_core.dtos.contrato.ContratoDetailsExtendedDto;
import com.example.cper_core.dtos.contrato.ContratoFiltroDto;
import com.example.cper_core.dtos.solicitacao_energetica.SolicitacaoEnergeticaDetailsExtendedDto;
import com.example.cper_core.services.ClienteService;
import com.example.cper_core.services.interfaces.IContratoService;
import com.example.cper_core.services.interfaces.ISolicitacaoEnergeticaService;
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
@RequestMapping("/api/contratos")
@Tag(name = "Contratos", description = "Consulta de contratos do cliente")
public class ContratoRestController {

    private final IContratoService contratoService;
    private final ISolicitacaoEnergeticaService solicitacaoEnergeticaService;
    private final JwtRequestUtils jwtRequestUtils;
    private final ClienteService clienteService;

    public ContratoRestController(
            IContratoService contratoService,
            ISolicitacaoEnergeticaService solicitacaoEnergeticaService,
            JwtRequestUtils jwtRequestUtils,
            ClienteService clienteService) {
        this.contratoService = contratoService;
        this.solicitacaoEnergeticaService = solicitacaoEnergeticaService;
        this.jwtRequestUtils = jwtRequestUtils;
        this.clienteService = clienteService;
    }

    @GetMapping
    @Operation(summary = "Listar contratos com paginação e filtros")
    public ResponseEntity<Page<ContratoDetailsDto>> listar(
            @Parameter(hidden = true) Pageable pageable,
            @Parameter(description = "Filtro avançado") ContratoFiltroDto filtro,
            HttpServletRequest request
    ) {
        Integer clienteId = jwtRequestUtils.getUserIdFromRequest(request);

        if (filtro == null) {
            filtro = new ContratoFiltroDto();
        }

        filtro.setClienteId(clienteId);

        return ResponseEntity.ok(contratoService.listFiltered(pageable, filtro));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obter detalhes de contrato por ID")
    public ResponseEntity<ContratoDetailsExtendedDto> getById(
            @PathVariable Integer id,
            HttpServletRequest request
    ) {
        Integer clienteId = jwtRequestUtils.getUserIdFromRequest(request);

        ContratoDetailsExtendedDto contrato = contratoService.getExtendedById(id);

        if (contrato == null || contrato.getSolicitacaoEnergetica() == null) {
            return ResponseEntity.status(404).build();
        }

        Integer solicitacaoId = contrato.getSolicitacaoEnergetica().getId();
        SolicitacaoEnergeticaDetailsExtendedDto solicitacao = solicitacaoEnergeticaService.getExtendedById(solicitacaoId);

        if (solicitacao == null || solicitacao.getCliente() == null ||
                !solicitacao.getCliente().getId().equals(clienteId)) {
            return ResponseEntity.status(403).build();
        }

        return ResponseEntity.ok(contrato);
    }

}