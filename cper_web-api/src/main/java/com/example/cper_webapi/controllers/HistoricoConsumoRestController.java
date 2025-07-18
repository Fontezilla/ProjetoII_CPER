package com.example.cper_webapi.controllers;

import com.example.cper_core.dtos.historico_consumo.*;
import com.example.cper_core.services.interfaces.IHistoricoConsumoService;
import com.example.cper_webapi.utils.JwtRequestUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/historico-consumo")
@Tag(name = "Histórico de Consumo", description = "Consulta de registos e métricas de consumo energético")
public class HistoricoConsumoRestController {

    private final IHistoricoConsumoService historicoConsumoService;
    private final JwtRequestUtils jwtRequestUtils;

    public HistoricoConsumoRestController(IHistoricoConsumoService historicoConsumoService, JwtRequestUtils jwtRequestUtils) {
        this.historicoConsumoService = historicoConsumoService;
        this.jwtRequestUtils = jwtRequestUtils;
    }

    @GetMapping
    @Operation(summary = "Listar histórico de consumo com filtros e paginação")
    public ResponseEntity<Page<HistoricoConsumoDetailsDto>> listar(
            @Parameter(hidden = true) Pageable pageable,
            @Parameter(description = "Filtro por datas, etc.") HistoricoConsumoFiltroDto filtro,
            HttpServletRequest request
    ) {
        Integer clienteId = jwtRequestUtils.getUserIdFromRequest(request);
        filtro.setIdsCliente(Set.of(clienteId));
        return ResponseEntity.ok(historicoConsumoService.listFiltered(pageable, filtro));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obter detalhes de um registo de consumo por ID")
    public ResponseEntity<HistoricoConsumoDetailsExtendedDto> getById(
            @PathVariable Integer id,
            HttpServletRequest request
    ) {
        Integer clienteId = jwtRequestUtils.getUserIdFromRequest(request);
        HistoricoConsumoDetailsExtendedDto dto = historicoConsumoService.getExtendedById(id);

        if (dto == null || !dto.getCliente().getId().equals(clienteId)) {
            return ResponseEntity.status(403).build();
        }

        return ResponseEntity.ok(dto);
    }

    @GetMapping("/metricas")
    @Operation(summary = "Calcular métricas de consumo com base em filtros")
    public ResponseEntity<HistoricoConsumoMetricasDto> calcularMetricas(
            @Parameter(description = "Filtros por período, etc.") HistoricoConsumoFiltroDto filtro,
            HttpServletRequest request
    ) {
        Integer clienteId = jwtRequestUtils.getUserIdFromRequest(request);
        filtro.setIdsCliente(Set.of(clienteId));
        return ResponseEntity.ok(historicoConsumoService.calcularMetricas(filtro));
    }
}