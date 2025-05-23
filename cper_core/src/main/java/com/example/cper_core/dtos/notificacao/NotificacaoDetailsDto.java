package com.example.cper_core.dtos.notificacao;

import com.example.cper_core.dtos.OnUpdate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class NotificacaoDetailsDto extends NotificacaoDto {

    @NotBlank(groups = OnUpdate.class, message = "O título é obrigatório")
    private String titulo;

    @NotNull(groups = OnUpdate.class, message = "A data de criação é obrigatória")
    private OffsetDateTime dataCriacao;

    @NotBlank(groups = OnUpdate.class, message = "A mensagem é obrigatória")
    private String mensagem;
}