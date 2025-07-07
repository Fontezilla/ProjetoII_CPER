package com.example.cper_core.dtos.inspecao;

import com.example.cper_core.dtos.OnCreate;
import com.example.cper_core.enums.EstadoInspecao;
import com.example.cper_core.enums.TipoInspecao;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class InspecaoDetailsDto extends InspecaoDto {

    private String codigo;

    @NotNull(groups = OnCreate.class, message = "O título é obrigatório")
    private String titulo;

    @NotNull(groups = OnCreate.class, message = "A data da inspeção é obrigatória")
    private OffsetDateTime data;

    @NotNull(groups = OnCreate.class, message = "O tipo de inspeção é obrigatório")
    private TipoInspecao tipo;


    @NotNull(groups = OnCreate.class, message = "O estado é obrigatório")
    private EstadoInspecao estado;
}