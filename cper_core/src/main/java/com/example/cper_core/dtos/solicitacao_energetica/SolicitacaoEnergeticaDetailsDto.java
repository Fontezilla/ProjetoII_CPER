package com.example.cper_core.dtos.solicitacao_energetica;

import com.example.cper_core.dtos.OnCreate;
import com.example.cper_core.enums.EstadoSolicitacaoEnergetica;
import com.example.cper_core.enums.Prioridade;
import com.example.cper_core.enums.TipoEnergiaRenovavel;
import jakarta.validation.Valid;
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
public class SolicitacaoEnergeticaDetailsDto extends SolicitacaoEnergeticaDto {

    private String codigo;

    @NotNull(message = "O tipo de energia é obrigatório")
    private TipoEnergiaRenovavel tipoEnergia;

    @NotNull(message = "A quantidade solicitada é obrigatória")
    @DecimalMin(value = "0", inclusive = false, message = "A quantidade solicitada deve ser maior que zero", groups = OnCreate.class)
    private BigDecimal qtdSolicitada;

    @NotNull(message = "A quantidade solicitada por hora é obrigatória")
    @DecimalMin(value = "0", inclusive = false, message = "A quantidade solicitada por hora deve ser maior que zero", groups = OnCreate.class)
    private BigDecimal qtdSolicitadaH;

    @NotNull(groups = OnCreate.class, message = "A prioridade é obrigatória")
    private Prioridade prioridade;

    @NotNull(groups = OnCreate.class, message = "O estado é obrigatório")
    private EstadoSolicitacaoEnergetica estado;
}
