package com.example.cper_core.services;

import com.example.cper_core.dtos.localidade.*;
import com.example.cper_core.entities.*;
import com.example.cper_core.mappers.LocalidadeMapper;
import com.example.cper_core.repositories.LocalidadeRepository;
import com.example.cper_core.services.interfaces.ILocalidadeService;
import com.example.cper_core.specifications.LocalidadeSpecification;
import jakarta.validation.Validator;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LocalidadeService extends AbstractXService<
        Localidade,
        LocalidadeDto,
        LocalidadeDetailsDto,
        LocalidadeDetailsExtendedDto,
        LocalidadeFiltroDto,
        LocalidadeWithRelationshipsDto,
        Integer
        > implements ILocalidadeService {

    private final LocalidadeMapper localidadeMapper;

    public LocalidadeService(
            LocalidadeRepository localidadeRepository,
            LocalidadeMapper localidadeMapper,
            Validator validator
    ) {
        super(localidadeRepository, localidadeRepository, validator);
        this.localidadeMapper = localidadeMapper;
    }

    @Override
    protected Localidade toEntity(LocalidadeDetailsExtendedDto dto) {
        return localidadeMapper.toEntity(dto);
    }

    @Override
    protected void updateEntityFromDto(LocalidadeDetailsExtendedDto dto, Localidade entity) {
        localidadeMapper.updateEntityFromExtendedDto(dto, entity);
    }

    @Override
    protected LocalidadeDetailsExtendedDto toExtendedDto(Localidade entity) {
        return localidadeMapper.toExtendedDto(entity);
    }

    @Override
    protected LocalidadeDetailsDto toDetailsDto(Localidade entity) {
        return localidadeMapper.toDetailsDto(entity);
    }

    @Override
    protected Specification<Localidade> getSpecificationFromFiltro(LocalidadeFiltroDto filtro) {
        return LocalidadeSpecification.filter(filtro);
    }

    @Override
    protected void marcarComoEliminado(Localidade entity) {
        throw new UnsupportedOperationException("Soft delete não suportado para Localidade.");
    }

    @Override
    protected void validateBeforeDeleting(Localidade entity) {
        if (!entity.getEnderecos().isEmpty()) {
            throw new IllegalStateException("O endereço está associado a outras entidades e não pode ser eliminado.");
        }
    }
}