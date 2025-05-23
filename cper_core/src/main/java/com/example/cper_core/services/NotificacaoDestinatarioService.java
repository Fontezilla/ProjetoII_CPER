package com.example.cper_core.services;

import com.example.cper_core.dtos.notificacao_destinario.*;
import com.example.cper_core.entities.NotificacaoDestinatario;
import com.example.cper_core.mappers.NotificacaoDestinatarioMapper;
import com.example.cper_core.repositories.NotificacaoDestinatarioRepository;
import com.example.cper_core.services.interfaces.INotificacaoDestinatarioService;
import com.example.cper_core.specifications.NotificacaoDestinatarioSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class NotificacaoDestinatarioService extends AbstractXService<
        NotificacaoDestinatario,
        NotificacaoDestinatarioDto,
        NotificacaoDestinatarioDetailsDto,
        NotificacaoDestinatarioDetailsDto,
        NotificacaoDestinatarioFiltroDto,
        NotificacaoDestinatarioDetailsDto,
        Integer
        > implements INotificacaoDestinatarioService {

    private final NotificacaoDestinatarioRepository notificacaoDestinatarioRepository;
    private final NotificacaoDestinatarioMapper notificacaoDestinatarioMapper;

    public NotificacaoDestinatarioService(
            NotificacaoDestinatarioRepository notificacaoDestinatarioRepository,
            NotificacaoDestinatarioMapper notificacaoDestinatarioMapper,
            jakarta.validation.Validator validator
    ) {
        super(notificacaoDestinatarioRepository, notificacaoDestinatarioRepository, validator);
        this.notificacaoDestinatarioRepository = notificacaoDestinatarioRepository;
        this.notificacaoDestinatarioMapper = notificacaoDestinatarioMapper;
    }

    @Override
    protected NotificacaoDestinatario toEntity(NotificacaoDestinatarioDetailsDto dto) {
        return notificacaoDestinatarioMapper.toEntity(dto);
    }

    @Override
    protected void updateEntityFromDto(NotificacaoDestinatarioDetailsDto dto, NotificacaoDestinatario entity) {
        notificacaoDestinatarioMapper.updateEntityFromDetailsDto(dto, entity);
    }

    @Override
    protected NotificacaoDestinatarioDetailsDto toExtendedDto(NotificacaoDestinatario entity) {
        return notificacaoDestinatarioMapper.toDetailsDto(entity);
    }

    @Override
    protected NotificacaoDestinatarioDetailsDto toDetailsDto(NotificacaoDestinatario entity) {
        return notificacaoDestinatarioMapper.toDetailsDto(entity);
    }

    @Override
    protected Specification<NotificacaoDestinatario> getSpecificationFromFiltro(NotificacaoDestinatarioFiltroDto filtro) {
        return NotificacaoDestinatarioSpecification.filter(filtro);
    }

    @Override
    protected void marcarComoEliminado(NotificacaoDestinatario entity) {
        throw new UnsupportedOperationException("Soft delete não suportado para associação de notificação e destinatário.");
    }

    @Override
    public Page<NotificacaoDestinatarioDetailsDto> listFiltered(Pageable pageable, NotificacaoDestinatarioFiltroDto filtro) {
        return notificacaoDestinatarioRepository.findAll(getSpecificationFromFiltro(filtro), pageable)
                .map(this::toDetailsDto);
    }
}
