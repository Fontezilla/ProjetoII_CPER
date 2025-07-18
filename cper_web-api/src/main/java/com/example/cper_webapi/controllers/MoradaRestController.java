package com.example.cper_webapi.controllers;

import com.example.cper_core.dtos.endereco.EnderecoDetailsExtendedDto;
import com.example.cper_core.dtos.morada.MoradaSimplesDto;
import com.example.cper_core.services.interfaces.IEnderecoService;
import com.example.cper_core.services.interfaces.IMoradaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/moradas")
@Tag(name = "Moradas", description = "Gestão de moradas (endereço + localidade)")
public class MoradaRestController {

    private final IMoradaService moradaService;
    private final IEnderecoService enderecoService;

    public MoradaRestController(IMoradaService moradaService, IEnderecoService enderecoService) {
        this.moradaService = moradaService;
        this.enderecoService = enderecoService;
    }

    @GetMapping
    @Operation(summary = "Verificar se a morada já existe")
    public ResponseEntity<EnderecoDetailsExtendedDto> getMoradaExistente(
            @Valid MoradaSimplesDto dto) {
        EnderecoDetailsExtendedDto existente = moradaService.getEnderecoExistente(dto);
        return existente != null
                ? ResponseEntity.ok(existente)
                : ResponseEntity.notFound().build();
    }

    @PostMapping
    @Operation(summary = "Criar nova morada (endereço + localidade)")
    public ResponseEntity<EnderecoDetailsExtendedDto> criarMorada(
            @Valid @RequestBody MoradaSimplesDto dto) {
        return ResponseEntity.ok(moradaService.criarEnderecoSeNecessario(dto));
    }
}