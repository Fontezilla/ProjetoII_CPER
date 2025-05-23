package com.example.cper_core.dtos.anomalia;

import com.example.cper_core.dtos.OnCreate;
import com.example.cper_core.enums.EstadoAnomalia;
import com.example.cper_core.enums.Prioridade;
import com.example.cper_core.enums.TipoAnomalia;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;
import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AnomaliaDetailsDto extends AnomaliaDto implements Serializable {

    @NotNull(groups = OnCreate.class, message = "O tipo de anomalia é obrigatório")
    private TipoAnomalia tipoAnomalia;

    @NotBlank(groups = OnCreate.class, message = "O título não pode estar vazio")
    @Size(max = 255, message = "O título deve ter no máximo 255 caracteres", groups = OnCreate.class)
    private String titulo;

    @NotNull(groups = OnCreate.class, message = "A data de identificação é obrigatória")
    private OffsetDateTime dataIdentificacao;

    @NotNull(groups = OnCreate.class, message = "A gravidade é obrigatória")
    private Prioridade gravidade;

    @NotNull(groups = OnCreate.class, message = "O estado é obrigatório")
    private EstadoAnomalia estado;
}
