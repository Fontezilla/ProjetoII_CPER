package com.example.cper_core.dtos.solicitacao_energetica;

import com.example.cper_core.dtos.cliente.ClienteDto;
import com.example.cper_core.dtos.contrato.ContratoDto;
import com.example.cper_core.dtos.funcionario.FuncionarioDto;
import com.example.cper_core.dtos.nota.NotaDto;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SolicitacaoEnergeticaWithRelationshipsDto extends SolicitacaoEnergeticaDto {

    @Valid
    private Set<NotaDto> notas;

    @Valid
    private Set<FuncionarioDto> funcionarios;
}

