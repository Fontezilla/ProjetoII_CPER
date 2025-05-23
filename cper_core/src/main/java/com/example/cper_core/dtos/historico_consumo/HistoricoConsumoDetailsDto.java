package com.example.cper_core.dtos.historico_consumo;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class HistoricoConsumoDetailsDto extends HistoricoConsumoDto {

    @NotNull(message = "A data de registo é obrigatória")
    private OffsetDateTime dataRegisto;

    @NotNull(message = "A energia total é obrigatória")
    @DecimalMin(value = "0.0", message = "A energia total não pode ser negativa")
    private BigDecimal energiaTotal;

    @NotNull(message = "O consumo por hora é obrigatório")
    @DecimalMin(value = "0.0", message = "O consumo por hora não pode ser negativo")
    private BigDecimal consumoPorHora;
}
