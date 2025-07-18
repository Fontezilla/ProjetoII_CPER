package com.example.cper_core.dtos.notificacao;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
public class NotificacaoFiltroDto {

    private Integer id;

    private String titulo;
    private String mensagem;

    private OffsetDateTime dataCriacaoInicio;
    private OffsetDateTime dataCriacaoFim;

    private String tipoDestinatario;

    private Integer clienteId;

    private Boolean isDeleted = false;
}