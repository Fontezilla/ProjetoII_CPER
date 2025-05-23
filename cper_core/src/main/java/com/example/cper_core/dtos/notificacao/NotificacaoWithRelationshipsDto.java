package com.example.cper_core.dtos.notificacao;

import com.example.cper_core.dtos.notificacao_destinario.NotificacaoDestinatarioDto;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class NotificacaoWithRelationshipsDto extends NotificacaoDto {

    @Valid
    private Set<NotificacaoDestinatarioDto> notificacaoUsuarios;
}