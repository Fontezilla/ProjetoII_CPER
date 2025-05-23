package com.example.cper_core.dtos.notificacao;

import com.example.cper_core.dtos.OnUpdate;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class NotificacaoDetailsExtendedDto extends NotificacaoDetailsDto {

    @NotBlank(message = "O tipo de destinatário é obrigatório")
    private String tipoDestinatario;

    private Boolean isDeleted;
}