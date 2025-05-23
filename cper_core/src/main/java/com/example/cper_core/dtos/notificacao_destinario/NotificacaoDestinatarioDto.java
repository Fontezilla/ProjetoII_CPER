package com.example.cper_core.dtos.notificacao_destinario;

import com.example.cper_core.dtos.OnUpdate;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificacaoDestinatarioDto implements Serializable {

    @NotNull(groups = OnUpdate.class, message = "O id da notificação_destinatário é obrigatório")
    private Integer id;
}