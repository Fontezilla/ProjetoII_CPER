package com.example.cper_core.services.interfaces;

import com.example.cper_core.dtos.notificacao_destinario.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface INotificacaoDestinatarioService extends IXService<
        NotificacaoDestinatarioDto,
        NotificacaoDestinatarioDetailsDto,
        NotificacaoDestinatarioDetailsDto,
        NotificacaoDestinatarioFiltroDto,
        NotificacaoDestinatarioDetailsDto,
        Integer
        > {
}

