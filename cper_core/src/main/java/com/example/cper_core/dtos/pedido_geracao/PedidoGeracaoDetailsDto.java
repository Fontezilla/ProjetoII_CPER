package com.example.cper_core.dtos.pedido_geracao;

import com.example.cper_core.dtos.OnCreate;
import com.example.cper_core.enums.EstadoPedidoGeracao;
import com.example.cper_core.enums.Prioridade;
import com.example.cper_core.enums.TipoEnergiaRenovavel;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PedidoGeracaoDetailsDto extends PedidoGeracaoDto {

    private String codigo;

    @NotNull(groups = OnCreate.class, message = "A data de criação é obrigatória")
    private OffsetDateTime dataCriacao;

    @NotNull(groups = OnCreate.class, message = "A quantidade de energia é obrigatória")
    @DecimalMin(value = "0.0", groups = OnCreate.class, message = "A quantidade de energia deve ser maior que zero")
    private BigDecimal qtdEnergia;

    @NotNull(groups = OnCreate.class, message = "A quantidade de energia por hora é obrigatória")
    @DecimalMin(value = "0.0", groups = OnCreate.class, message = "A quantidade de energia por hora deve ser maior que zero")
    private BigDecimal qtdEnergiaH;

    @NotNull(groups = OnCreate.class, message = "O tipo de energia é obrigatório")
    private TipoEnergiaRenovavel tipoEnergia;

    @NotNull(groups = OnCreate.class, message = "A prioridade é obrigatória")
    private Prioridade prioridade;

    @NotNull(groups = OnCreate.class, message = "O estado é obrigatório")
    private EstadoPedidoGeracao estado;
}

