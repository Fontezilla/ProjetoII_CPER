package com.example.cper_core.dtos.avaria;

import com.example.cper_core.dtos.OnCreate;
import com.example.cper_core.enums.EstadoAvaria;
import com.example.cper_core.enums.Prioridade;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AvariaDetailsDto extends AvariaDto {

    private String codigo;

    @NotBlank(groups = OnCreate.class, message = "O título é obrigatório")
    @Size(max = 255, message = "O título deve ter no máximo 255 caracteres", groups = OnCreate.class)
    private String titulo;

    @NotNull(groups = OnCreate.class, message = "A data de início é obrigatória")
    private OffsetDateTime dataInicio;

    @NotNull(groups = OnCreate.class, message = "A gravidade é obrigatória")
    private Prioridade gravidade;

    @NotNull(groups = OnCreate.class, message = "O impacto na produção é obrigatório")
    @DecimalMin(value = "0.0", inclusive = true, message = "O impacto na produção não pode ser negativo", groups = OnCreate.class)
    private BigDecimal impactoProducao;

    @NotNull(groups = OnCreate.class, message = "O impacto percentual é obrigatório")
    @Min(value = 0, message = "O impacto percentual deve ser no mínimo 0%", groups = OnCreate.class)
    @Max(value = 100, message = "O impacto percentual deve ser no máximo 100%", groups = OnCreate.class)
    private Integer impactoPercentual;

    @NotNull(groups = OnCreate.class, message = "O estado e obrigatorio")
    private EstadoAvaria estado;
}

