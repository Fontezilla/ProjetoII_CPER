package com.example.cper_core.dtos.contrato;

import com.example.cper_core.dtos.OnCreate;
import com.example.cper_core.enums.EstadoContrato;
import com.example.cper_core.enums.TipoContrato;
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
public class ContratoDetailsDto extends ContratoDto {

    @NotNull(groups = OnCreate.class, message = "O tipo de contrato é obrigatório")
    private TipoContrato tipoContrato;

    @NotNull(groups = OnCreate.class, message = "A quantidade de energia é obrigatória")
    @DecimalMin(value = "0.0", message = "A quantidade de energia não pode ser negativa")
    private BigDecimal qtdEnergia;

    @NotNull(groups = OnCreate.class, message = "A quantidade de energia por hora é obrigatória")
    @DecimalMin(value = "0.0", message = "A quantidade de energia por hora não pode ser negativa")
    private BigDecimal qtdEnergiaH;

    @NotNull(groups = OnCreate.class, message = "O estado é obrigatório")
    private EstadoContrato estado;
}

