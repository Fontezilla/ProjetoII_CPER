package com.example.cper_core.services.interfaces;

import com.example.cper_core.dtos.notificacao.*;
import com.example.cper_core.entities.Notificacao;

public interface INotificacaoService extends IXService<
        NotificacaoDto,
        NotificacaoDetailsDto,
        NotificacaoDetailsExtendedDto,
        NotificacaoFiltroDto,
        Integer
        >, IWithRelationshipsSupport<Notificacao, NotificacaoWithRelationshipsDto, Integer> {
}
