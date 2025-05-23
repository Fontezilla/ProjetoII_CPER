package com.example.cper_core.dtos.armazem;

import com.example.cper_core.dtos.armazem_lote.ArmazemLoteDto;
import com.example.cper_core.dtos.departamento.DepartamentoDto;
import com.example.cper_core.dtos.endereco.EnderecoDto;
import com.example.cper_core.dtos.funcionario.FuncionarioDto;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ArmazemWithRelationshipsDto extends ArmazemDto {
    @Valid
    private Set<ArmazemLoteDto> lotes;

    @Valid
    private Set<FuncionarioDto> funcionarios;
}

