package com.example.cper_core.dtos.centro_producao;

import com.example.cper_core.dtos.anomalia.AnomaliaDto;
import com.example.cper_core.dtos.avaria.AvariaDto;
import com.example.cper_core.dtos.departamento.DepartamentoDto;
import com.example.cper_core.dtos.endereco.EnderecoDto;
import com.example.cper_core.dtos.funcionario.FuncionarioDto;
import com.example.cper_core.dtos.inspecao.InspecaoDto;
import com.example.cper_core.dtos.pedido_geracao.PedidoGeracaoDto;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CentroProducaoWithRelationshipsDto extends CentroProducaoDto {
    @Valid
    private Set<AvariaDto> avarias;

    @Valid
    private Set<InspecaoDto> inspecoes;

    @Valid
    private Set<AnomaliaDto> anomalias;

    @Valid
    private Set<PedidoGeracaoDto> pedidosGeracao;

    @Valid
    private Set<FuncionarioDto> funcionarios;
}
