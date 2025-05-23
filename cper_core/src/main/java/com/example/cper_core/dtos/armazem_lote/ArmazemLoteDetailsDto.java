package com.example.cper_core.dtos.armazem_lote;

import com.example.cper_core.dtos.OnCreate;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ArmazemLoteDetailsDto extends ArmazemLoteDto {

    @NotNull(groups = OnCreate.class, message = "A quantidade armazenada é obrigatória")
    @DecimalMin(value = "0", inclusive = true, message = "A quantidade armazenada não pode ser negativa", groups = OnCreate.class)
    private BigDecimal quantidadeArmazenada;
}
