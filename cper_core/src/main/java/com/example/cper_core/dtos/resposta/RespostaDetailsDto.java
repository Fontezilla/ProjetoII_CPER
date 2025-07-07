package com.example.cper_core.dtos.resposta;

import com.example.cper_core.dtos.OnCreate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class RespostaDetailsDto extends RespostaDto {

    private String codigo;

    @NotBlank(groups = OnCreate.class, message = "A resposta é obrigatória")
    private String resposta;

    @NotNull(groups = OnCreate.class, message = "A data da resposta é obrigatória")
    private OffsetDateTime dataResposta;
}