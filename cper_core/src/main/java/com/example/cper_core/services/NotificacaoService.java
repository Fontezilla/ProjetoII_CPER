package com.example.cper_core.services;

import com.example.cper_core.dtos.notificacao.*;
import com.example.cper_core.entities.Notificacao;
import com.example.cper_core.mappers.NotificacaoMapper;
import com.example.cper_core.repositories.NotificacaoRepository;
import com.example.cper_core.services.interfaces.INotificacaoService;
import com.example.cper_core.specifications.NotificacaoSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class NotificacaoService extends AbstractXService<
        Notificacao,
        NotificacaoDto,
        NotificacaoDetailsDto,
        NotificacaoDetailsExtendedDto,
        NotificacaoFiltroDto,
        NotificacaoWithRelationshipsDto,
        Integer
        > implements INotificacaoService {

    private final NotificacaoMapper notificacaoMapper;

    public NotificacaoService(
            NotificacaoRepository notificacaoRepository,
            NotificacaoMapper notificacaoMapper,
            jakarta.validation.Validator validator
    ) {
        super(notificacaoRepository, notificacaoRepository, validator);
        this.notificacaoMapper = notificacaoMapper;
    }

    @Override
    protected Notificacao toEntity(NotificacaoDetailsExtendedDto dto) {
        return notificacaoMapper.toEntity(dto);
    }

    @Override
    protected void updateEntityFromDto(NotificacaoDetailsExtendedDto dto, Notificacao entity) {
        notificacaoMapper.updateEntityFromExtendedDto(dto, entity);
    }

    @Override
    protected NotificacaoDetailsExtendedDto toExtendedDto(Notificacao entity) {
        return notificacaoMapper.toExtendedDto(entity);
    }

    @Override
    protected NotificacaoDetailsDto toDetailsDto(Notificacao entity) {
        return notificacaoMapper.toDetailsDto(entity);
    }

    @Override
    protected void marcarComoEliminado(Notificacao entity) {
        entity.setIsDeleted(true);
    }

    @Override
    protected Specification<Notificacao> getSpecificationFromFiltro(NotificacaoFiltroDto filtro) {
        // Usando a especificação para criar a consulta filtrada
        return NotificacaoSpecification.filter(filtro);
    }
}
