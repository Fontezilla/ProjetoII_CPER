package com.example.cper_core.dtos.pedido_geracao;

import com.example.cper_core.dtos.centro_producao.CentroProducaoDto;
import com.example.cper_core.dtos.contrato.ContratoDto;
import com.example.cper_core.dtos.funcionario.FuncionarioDto;
import com.example.cper_core.entities.PedidoGeracao;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/**
 * DTO for {@link PedidoGeracao}
 */

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PedidoGeracaoDetailsExtendedDto extends PedidoGeracaoDetailsDto implements Serializable {
    @NotNull(message = "A data de criação não pode ser nula")
    private LocalDate dataCriacao;

    private LocalDate dataPrevisao;

    private String obs;

    private String relatorioFinal;

    @NotNull(message = "O contrato associado não pode ser nulo")
    private ContratoDto contrato;

    @NotNull(message = "O centro de produção não pode ser nulo")
    private CentroProducaoDto centroProducao;

    @NotNull(message = "O funcionário responsável não pode ser nulo")
    private FuncionarioDto funcionario;

    public PedidoGeracaoDetailsExtendedDto(Integer id, BigDecimal qtdEnergia, String tipoEnergia, String prioridade, String estado, LocalDate dataCriacao, LocalDate dataPrevisao, String obs, String relatorioFinal, ContratoDto contrato, CentroProducaoDto centroProducao, FuncionarioDto funcionario) {
        super(id, qtdEnergia, tipoEnergia, prioridade, estado);
        this.dataCriacao = dataCriacao != null ? dataCriacao : LocalDate.now();
        this.dataPrevisao = dataPrevisao;
        this.obs = obs;
        this.relatorioFinal = relatorioFinal;
        this.contrato = contrato;
        this.centroProducao = centroProducao;
        this.funcionario = funcionario;
    }
}