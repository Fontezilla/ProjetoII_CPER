package com.example.cper_core.dtos.lote;

import com.example.cper_core.dtos.OnCreate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class LoteDetailsDto extends LoteDto {

    @NotBlank(groups = OnCreate.class, message = "O código do lote é obrigatório")
    private String codigoLote;

    @NotNull(groups = OnCreate.class, message = "A quantidade total é obrigatória")
    private BigDecimal quantidadeTotal;

    private BigDecimal quantidadeDisponivel;
}