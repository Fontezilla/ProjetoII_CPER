package com.example.cper_core.services;

import com.example.cper_core.dtos.material.*;
import com.example.cper_core.entities.*;
import com.example.cper_core.mappers.MaterialMapper;
import com.example.cper_core.repositories.*;
import com.example.cper_core.services.interfaces.IMaterialService;
import com.example.cper_core.specifications.MaterialSpecification;
import jakarta.validation.Validator;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MaterialService extends AbstractXService<
        Material,
        MaterialDto,
        MaterialDetailsDto,
        MaterialDetailsExtendedDto,
        MaterialFiltroDto,
        Integer
        > implements IMaterialService {

    private final MaterialMapper materialMapper;

    public MaterialService(
            MaterialRepository materialRepository,
            MaterialMapper materialMapper,
            Validator validator
    ) {
        super(materialRepository, materialRepository, validator);
        this.materialMapper = materialMapper;
    }

    @Override
    protected Material toEntity(MaterialDetailsExtendedDto dto) {
        return materialMapper.toEntity(dto);
    }

    @Override
    protected void updateEntityFromDto(MaterialDetailsExtendedDto dto, Material entity) {
        materialMapper.updateEntityFromExtendedDto(dto, entity);
    }

    @Override
    protected MaterialDetailsExtendedDto toExtendedDto(Material entity) {
        return materialMapper.toExtendedDto(entity);
    }

    @Override
    protected MaterialDetailsDto toDetailsDto(Material entity) {
        return materialMapper.toDetailsDto(entity);
    }

    @Override
    protected Specification<Material> getSpecificationFromFiltro(MaterialFiltroDto filtro) {
        return MaterialSpecification.filter(filtro);
    }

    @Override
    protected void markedDeleted(Material entity) {
        entity.setIsDeleted(true);
    }
}