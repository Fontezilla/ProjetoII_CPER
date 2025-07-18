package com.example.cper_webapi.controllers;

import com.example.cper_core.dtos.fatura.FaturaDetailsDto;
import com.example.cper_core.dtos.fatura.FaturaDetailsExtendedDto;
import com.example.cper_core.dtos.fatura.FaturaFiltroDto;
import com.example.cper_core.dtos.solicitacao_energetica.SolicitacaoEnergeticaDetailsExtendedDto;
import com.example.cper_core.services.interfaces.IFaturaService;
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
@RequestMapping("/api/faturas")
@Tag(name = "Faturas", description = "Consulta de faturas emitidas")
public class FaturaRestController {

    private final IFaturaService faturaService;
    private final IContratoService contratoService;
    private final ISolicitacaoEnergeticaService solicitacaoEnergeticaService;
    private final JwtRequestUtils jwtRequestUtils;

    public FaturaRestController(
            IFaturaService faturaService,
            IContratoService contratoService,
            ISolicitacaoEnergeticaService solicitacaoEnergeticaService,
            JwtRequestUtils jwtRequestUtils
    ) {
        this.faturaService = faturaService;
        this.contratoService = contratoService;
        this.solicitacaoEnergeticaService = solicitacaoEnergeticaService;
        this.jwtRequestUtils = jwtRequestUtils;
    }

    @GetMapping
    @Operation(summary = "Listar faturas com filtros e paginação")
    public ResponseEntity<Page<FaturaDetailsDto>> listar(
            @Parameter(hidden = true) Pageable pageable,
            @Parameter(description = "Filtro por contrato, datas, etc.") FaturaFiltroDto filtro,
            HttpServletRequest request
    ) {
        Integer clienteId = jwtRequestUtils.getUserIdFromRequest(request);
        filtro.setClienteId(clienteId); // precisa estar mapeado no filtro e no serviço
        return ResponseEntity.ok(faturaService.listFiltered(pageable, filtro));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obter detalhes de uma fatura por ID")
    public ResponseEntity<FaturaDetailsExtendedDto> getById(@PathVariable Integer id, HttpServletRequest request) {
        Integer clienteId = jwtRequestUtils.getUserIdFromRequest(request);
        FaturaDetailsExtendedDto fatura = faturaService.getExtendedById(id);

        if (fatura == null || fatura.getContrato() == null) {
            return ResponseEntity.notFound().build();
        }

        var contrato = contratoService.getExtendedById(fatura.getContrato().getId());
        if (contrato == null || contrato.getSolicitacaoEnergetica() == null) {
            return ResponseEntity.status(403).build();
        }

        SolicitacaoEnergeticaDetailsExtendedDto solicitacao =
                solicitacaoEnergeticaService.getExtendedById(contrato.getSolicitacaoEnergetica().getId());

        if (solicitacao == null || !solicitacao.getCliente().getId().equals(clienteId)) {
            return ResponseEntity.status(403).build();
        }

        return ResponseEntity.ok(fatura);
    }
}