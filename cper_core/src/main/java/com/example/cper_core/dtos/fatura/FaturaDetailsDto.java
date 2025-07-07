package com.example.cper_core.dtos.fatura;

import com.example.cper_core.dtos.OnCreate;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class FaturaDetailsDto extends FaturaDto {

    private String codigo;

    @NotNull(groups = OnCreate.class, message = "A data de emissao e obrigatoria")
    private OffsetDateTime dataEmissao;

    @NotNull(groups = OnCreate.class, message = "A data de vencimento e obrigatoria")
    private OffsetDateTime dataVencimento;

    @NotNull(message = "O valor da eletricidade é obrigatorio")
    @DecimalMin(value = "0.0", message = "O valor da eletricidade não pode ser negativo")
    private BigDecimal vElectricidade;

    @NotNull(groups = OnCreate.class, message = "A quantidade de energia e obrigatoria")
    @DecimalMin(value = "0.0", message = "A quantidade de energia não pode ser negativa")
    private BigDecimal qtdEnergia;

    @NotNull(groups = OnCreate.class, message = "O valor do IVA e obrigatorio")
    @Min(value = 0, message = "A taxa do IVA deve ser no mínimo 0%", groups = OnCreate.class)
    @Max(value = 100, message = "A tava do IVA deve ser no máximo 100%", groups = OnCreate.class)
    private Integer taxa;

    @NotNull(groups = OnCreate.class, message = "O estado e obrigatorio")
    private Integer estado;

    private BigDecimal valorTotal;
}