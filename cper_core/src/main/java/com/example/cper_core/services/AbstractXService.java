package com.example.cper_core.services;

import com.example.cper_core.services.interfaces.IXService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Set;

public abstract class AbstractXService<
        TEntity,
        TDto,
        TDetailsDto,
        TExtendedDto,
        TFiltroDto,
        TWithRelationshipsDto,
        TId
        > implements IXService<TDto, TDetailsDto, TExtendedDto, TFiltroDto, TWithRelationshipsDto, TId> {

    protected final JpaRepository<TEntity, TId> repository;
    protected final JpaSpecificationExecutor<TEntity> specRepository;
    protected final Validator validator;

    protected AbstractXService(JpaRepository<TEntity, TId> repository,
                               JpaSpecificationExecutor<TEntity> specRepository,
                               Validator validator) {
        this.repository = repository;
        this.specRepository = specRepository;
        this.validator = validator;
    }

    protected abstract TEntity toEntity(TExtendedDto dto);

    protected abstract void updateEntityFromDto(TExtendedDto dto, TEntity entity);

    protected abstract TExtendedDto toExtendedDto(TEntity entity);

    protected abstract TDetailsDto toDetailsDto(TEntity entity);

    protected abstract Specification<TEntity> getSpecificationFromFiltro(TFiltroDto filtro);

    protected abstract void marcarComoEliminado(TEntity entity);

    protected void validateDto(TExtendedDto dto) {
        Set<ConstraintViolation<TExtendedDto>> violations = validator.validate(dto);
        if (!violations.isEmpty()) {
            StringBuilder sb = new StringBuilder("Validação falhou:\n");
            for (ConstraintViolation<?> violation : violations) {
                sb.append(" • ").append(violation.getPropertyPath()).append(": ").append(violation.getMessage()).append("\n");
            }
            throw new IllegalArgumentException(sb.toString());
        }
    }

    @Override
    public TExtendedDto create(TExtendedDto dto) {
        validateDto(dto);
        TEntity entity = toEntity(dto);
        return toExtendedDto(repository.save(entity));
    }

    @Override
    public TDetailsDto getById(TId id) {
        return repository.findById(id)
                .map(this::toDetailsDto)
                .orElseThrow(() -> new IllegalArgumentException("Registo não encontrado com ID " + id));
    }

    @Override
    public Page<TDetailsDto> listAll(Pageable pageable) {
        return repository.findAll(pageable).map(this::toDetailsDto);
    }

    @Override
    public Page<TDetailsDto> listFiltered(Pageable pageable, TFiltroDto filtro) {
        return specRepository.findAll(getSpecificationFromFiltro(filtro), pageable)
                .map(this::toDetailsDto);
    }

    @Override
    public TExtendedDto update(TId id, TExtendedDto dto) {
        validateDto(dto);
        TEntity existing = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Registo não encontrado com ID " + id));
        updateEntityFromDto(dto, existing);
        return toExtendedDto(repository.save(existing));
    }

    public void delete(TId id) {
        TEntity entity = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Registo não encontrado com ID " + id));
        try {
            marcarComoEliminado(entity);
            repository.save(entity);
        } catch (UnsupportedOperationException e) {
            validateBeforeDeleting(entity);
            repository.delete(entity);
        }
    }
    protected void validateBeforeDeleting(TEntity entity) {
        // Por defeito não faz nada. Subclasses podem implementar.
    }
}