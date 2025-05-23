package com.example.cper_core.dtos.centro_producao;

import com.example.cper_core.dtos.OnCreate;
import com.example.cper_core.dtos.anomalia.AnomaliaDto;
import com.example.cper_core.dtos.avaria.AvariaDto;
import com.example.cper_core.dtos.departamento.DepartamentoDto;
import com.example.cper_core.dtos.endereco.EnderecoDto;
import com.example.cper_core.dtos.funcionario.FuncionarioDto;
import com.example.cper_core.dtos.inspecao.InspecaoDto;
import com.example.cper_core.dtos.pedido_geracao.PedidoGeracaoDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CentroProducaoDetailsExtendedDto extends CentroProducaoDetailsDto {

    @NotNull(groups = OnCreate.class, message = "A data de inicio e obrigatoria")
    private OffsetDateTime dataInicio;

    private BigDecimal custoOperacional;

    @NotNull(groups = OnCreate.class, message = "O numero da porta e obrigatorio")
    private String nPorta;

    @NotNull(groups = OnCreate.class, message = "O departamento e obrigatorio")
    @Valid
    private DepartamentoDto departamento;

    @NotNull(groups = OnCreate.class, message = "O endereco e obrigatorio")
    @Valid
    private EnderecoDto endereco;

    @Valid
    private FuncionarioDto responsavel;

    private Boolean isDeleted;
}
