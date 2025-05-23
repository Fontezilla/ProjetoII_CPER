package com.example.cper_core.dtos.endereco;

import com.example.cper_core.dtos.armazem.ArmazemDto;
import com.example.cper_core.dtos.centro_producao.CentroProducaoDto;
import com.example.cper_core.dtos.cliente.ClienteDto;
import com.example.cper_core.dtos.contrato.ContratoDto;
import com.example.cper_core.dtos.funcionario.FuncionarioDto;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class EnderecoWithRelationshipsDto extends EnderecoDto {

    @Valid
    private Set<ClienteDto> clientes;

    @Valid
    private Set<FuncionarioDto> funcionarios;

    @Valid
    private Set<ArmazemDto> armazens;

    @Valid
    private Set<CentroProducaoDto> centrosProducao;

    @Valid
    private Set<ContratoDto> contratos;
}
