package com.example.cper_core.dtos.fatura;

import com.example.cper_core.dtos.contrato.ContratoDto;
import com.example.cper_core.dtos.funcionario.FuncionarioDto;
import com.example.cper_core.entities.Fatura;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * DTO for {@link Fatura}
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class FaturaDetailsExtendedDto extends FaturaDetailsDto implements Serializable {
    @NotNull(message = "O campo data de emissão não pode ser nulo")
    private LocalDate dataEmissao;

    @NotNull(message = "O campo data de vencimento não pode ser nulo")
    private LocalDate dataVencimento;

    @Positive(message = "O valor da multa deve ser positivo")
    private BigDecimal vMulta;

    @NotNull(message = "O campo quantidade de energia não pode ser nulo")
    @Positive(message = "A quantidade de energia deve ser positiva")
    private BigDecimal qtdEnergia;

    @NotNull(message = "O campo funcionário não pode ser nulo")
    private FuncionarioDto funcionario;

    public FaturaDetailsExtendedDto(Integer id, BigDecimal vTotal, String estado, ContratoDto contrato, LocalDate dataEmissao, LocalDate dataVencimento, BigDecimal vMulta, BigDecimal qtdEnergia, FuncionarioDto funcionario) {
        super(id, vTotal, estado, contrato);
        this.dataEmissao = dataEmissao;
        this.dataVencimento = dataVencimento;
        this.vMulta = vMulta;
        this.qtdEnergia = qtdEnergia;
        this.funcionario = funcionario;
    }
}