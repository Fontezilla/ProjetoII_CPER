package com.example.cper_webapi.controllers;

import com.example.cper_core.dtos.cliente.ClienteDetailsDto;
import com.example.cper_core.dtos.cliente.ClienteDetailsExtendedDto;
import com.example.cper_core.dtos.cliente.ClienteFiltroDto;
import com.example.cper_core.services.interfaces.IClienteService;
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

@RestController
@RequestMapping("/api/clientes")
@Tag(name = "Clientes", description = "Operações disponíveis para gestão de clientes")
public class ClienteRestController {

    private final IClienteService clienteService;
    private final JwtRequestUtils jwtRequestUtils;

    public ClienteRestController(IClienteService clienteService, JwtRequestUtils jwtRequestUtils) {
        this.clienteService = clienteService;
        this.jwtRequestUtils = jwtRequestUtils;
    }

    @PostMapping
    @Operation(summary = "Criar novo cliente")
    public ResponseEntity<ClienteDetailsExtendedDto> criar(@Valid @RequestBody ClienteDetailsExtendedDto dto) {
        return ResponseEntity.ok(clienteService.create(dto));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obter cliente por ID")
    public ResponseEntity<ClienteDetailsExtendedDto> getById(@PathVariable Integer id, HttpServletRequest request) {
        Integer clienteId = jwtRequestUtils.getUserIdFromRequest(request);
        if (!id.equals(clienteId)) {
            return ResponseEntity.status(403).build();
        }

        return ResponseEntity.ok(clienteService.getExtendedById(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar cliente")
    public ResponseEntity<ClienteDetailsExtendedDto> atualizar(@PathVariable Integer id,
                                                               @Valid @RequestBody ClienteDetailsExtendedDto dto,
                                                               HttpServletRequest request) {
        Integer clienteId = jwtRequestUtils.getUserIdFromRequest(request);
        if (!id.equals(clienteId)) {
            return ResponseEntity.status(403).build();
        }

        return ResponseEntity.ok(clienteService.update(id, dto));
    }

    @GetMapping
    @Operation(summary = "Listar clientes com paginação e filtros")
    public ResponseEntity<Page<ClienteDetailsDto>> listar(
            @Parameter(hidden = true) Pageable pageable,
            @Parameter(description = "Filtro por nome ou NIF") ClienteFiltroDto filtro,
            HttpServletRequest request
    ) {
        Integer clienteId = jwtRequestUtils.getUserIdFromRequest(request);
        filtro.setId(clienteId);
        return ResponseEntity.ok(clienteService.listFiltered(pageable, filtro));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar cliente (soft delete)")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id, HttpServletRequest request) {
        Integer clienteId = jwtRequestUtils.getUserIdFromRequest(request);
        if (!id.equals(clienteId)) {
            return ResponseEntity.status(403).build();
        }

        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}