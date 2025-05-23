package com.example.cper_core.dtos.departamento;

import com.example.cper_core.dtos.armazem.ArmazemDto;
import com.example.cper_core.dtos.centro_producao.CentroProducaoDto;
import com.example.cper_core.dtos.equipa.EquipaDto;
import com.example.cper_core.dtos.funcionario.FuncionarioDto;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class DepartamentoWithRelationshipsDto extends DepartamentoDto {

    @Valid
    private Set<CentroProducaoDto> centrosProducao;

    @Valid
    private Set<EquipaDto> equipas;

    @Valid
    private Set<FuncionarioDto> funcionarios;

    @Valid
    private Set<ArmazemDto> armazens;

    @Valid
    private Set<FuncionarioDto> responsaveis;
}

