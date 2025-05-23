package com.example.cper_core.dtos.notificacao_destinario;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NotificacaoDestinatarioFiltroDto {

    private Integer id;

    private Integer idNotificacao;

    private Integer idFuncionario;

    private Integer idCliente;

    private Boolean lida;

    private Boolean isDeleted = false;
}