package com.example.cper_webapi.controllers;

import com.example.cper_core.dtos.funcionario.FuncionarioFiltroDto;
import com.example.cper_core.dtos.funcionario.FuncionarioPublicDto;
import com.example.cper_core.services.interfaces.IFuncionarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/funcionarios")
@Tag(name = "Funcionários", description = "Consulta de dados públicos de funcionários")
public class FuncionarioRestController {

    private final IFuncionarioService funcionarioService;

    public FuncionarioRestController(IFuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @GetMapping
    @Operation(summary = "Listar funcionários com filtros e paginação (dados públicos)")
    public ResponseEntity<Page<FuncionarioPublicDto>> listar(
            @Parameter(hidden = true) Pageable pageable,
            @Parameter(description = "Filtro por nome, setor, etc.") FuncionarioFiltroDto filtro
    ) {
        return ResponseEntity.ok(funcionarioService.listarPublicamente(pageable, filtro));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obter dados públicos de um funcionário por ID")
    public ResponseEntity<FuncionarioPublicDto> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(funcionarioService.getPublicById(id));
    }
}