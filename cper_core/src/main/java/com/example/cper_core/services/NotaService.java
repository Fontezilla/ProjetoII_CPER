package com.example.cper_core.services;

import com.example.cper_core.dtos.nota.*;
import com.example.cper_core.entities.*;
import com.example.cper_core.mappers.NotaMapper;
import com.example.cper_core.repositories.*;
import com.example.cper_core.services.interfaces.INotaService;
import com.example.cper_core.specifications.NotaSpecification;
import jakarta.validation.Validator;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class NotaService extends AbstractXService<
        Nota,
        NotaDto,
        NotaDetailsDto,
        NotaDetailsExtendedDto,
        NotaFiltroDto,
        Integer
        > implements INotaService {

    private final NotaMapper notaMapper;

    public NotaService(
            NotaRepository notaRepository,
            NotaMapper notaMapper,
            Validator validator
    ) {
        super(notaRepository, notaRepository, validator);
        this.notaMapper = notaMapper;
    }

    @Override
    protected Nota toEntity(NotaDetailsExtendedDto dto) {
        return notaMapper.toEntity(dto);
    }

    @Override
    protected void updateEntityFromDto(NotaDetailsExtendedDto dto, Nota entity) {
        notaMapper.updateEntityFromExtendedDto(dto, entity);
    }

    @Override
    protected NotaDetailsExtendedDto toExtendedDto(Nota entity) {
        return notaMapper.toExtendedDto(entity);
    }

    @Override
    protected NotaDetailsDto toDetailsDto(Nota entity) {
        return notaMapper.toDetailsDto(entity);
    }

    @Override
    protected Specification<Nota> getSpecificationFromFiltro(NotaFiltroDto filtro) {
        return NotaSpecification.filter(filtro);
    }

    @Override
    protected void markedDeleted(Nota entity) {
        entity.setIsDeleted(true);
    }
}