package com.example.cper_core.services.interfaces;

import com.example.cper_core.dtos.notificacao.*;

public interface INotificacaoService extends IXService<
        NotificacaoDto,
        NotificacaoDetailsDto,
        NotificacaoDetailsExtendedDto,
        NotificacaoFiltroDto,
        NotificacaoWithRelationshipsDto,
        Integer
        > {
}
