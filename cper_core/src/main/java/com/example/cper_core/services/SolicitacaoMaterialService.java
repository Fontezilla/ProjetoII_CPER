package com.example.cper_core.services;

import com.example.cper_core.dtos.solicitacao_material.*;
import com.example.cper_core.entities.SolicitacaoMaterial;
import com.example.cper_core.mappers.SolicitacaoMaterialMapper;
import com.example.cper_core.repositories.SolicitacaoMaterialRepository;
import com.example.cper_core.services.interfaces.ISolicitacaoMaterialService;
import com.example.cper_core.specifications.SolicitacaoMaterialSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SolicitacaoMaterialService extends AbstractXService<
        SolicitacaoMaterial,
        SolicitacaoMaterialDto,
        SolicitacaoMaterialDetailsDto,
        SolicitacaoMaterialDetailsExtendedDto,
        SolicitacaoMaterialFiltroDto,
        SolicitacaoMaterialWithRelationshipsDto,
        Integer
        > implements ISolicitacaoMaterialService {

    private final SolicitacaoMaterialMapper solicitacaoMaterialMapper;


    public SolicitacaoMaterialService(
            SolicitacaoMaterialRepository solicitacaoMaterialRepository,
            SolicitacaoMaterialMapper solicitacaoMaterialMapper,
            jakarta.validation.Validator validator
    ) {
        super(solicitacaoMaterialRepository, solicitacaoMaterialRepository, validator);
        this.solicitacaoMaterialMapper = solicitacaoMaterialMapper;
    }

    @Override
    protected SolicitacaoMaterial toEntity(SolicitacaoMaterialDetailsExtendedDto dto) {
        return solicitacaoMaterialMapper.toEntity(dto);
    }

    @Override
    protected void updateEntityFromDto(SolicitacaoMaterialDetailsExtendedDto dto, SolicitacaoMaterial entity) {
        solicitacaoMaterialMapper.updateEntityFromExtendedDto(dto, entity);
    }

    @Override
    protected SolicitacaoMaterialDetailsExtendedDto toExtendedDto(SolicitacaoMaterial entity) {
        return solicitacaoMaterialMapper.toExtendedDto(entity);
    }

    @Override
    protected SolicitacaoMaterialDetailsDto toDetailsDto(SolicitacaoMaterial entity) {
        return solicitacaoMaterialMapper.toDetailsDto(entity);
    }

    @Override
    protected Specification<SolicitacaoMaterial> getSpecificationFromFiltro(SolicitacaoMaterialFiltroDto filtro) {
        return SolicitacaoMaterialSpecification.filter(filtro);
    }

    @Override
    protected void marcarComoEliminado(SolicitacaoMaterial entity) {
        entity.setIsDeleted(true);
    }
}
