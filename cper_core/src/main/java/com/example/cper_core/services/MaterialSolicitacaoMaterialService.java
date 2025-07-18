package com.example.cper_core.services;

import com.example.cper_core.dtos.material_solicitacao_material.*;
import com.example.cper_core.entities.*;
import com.example.cper_core.mappers.MaterialSolicitacaoMaterialMapper;
import com.example.cper_core.repositories.*;
import com.example.cper_core.services.interfaces.IMaterialSolicitacaoMaterialService;
import com.example.cper_core.specifications.MaterialSolicitacaoMaterialSpecification;
import jakarta.validation.Validator;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MaterialSolicitacaoMaterialService extends AbstractXService<
        MaterialSolicitacaoMaterial,
        MaterialSolicitacaoMaterialDto,
        MaterialSolicitacaoMaterialDetailsDto,
        MaterialSolicitacaoMaterialDetailsDto,
        MaterialSolicitacaoMaterialFiltroDto,
        MaterialSolicitacaoMaterialId
        > implements IMaterialSolicitacaoMaterialService {

    private final MaterialSolicitacaoMaterialMapper mapper;
    private final MaterialRepository materialRepository;
    private final SolicitacaoMaterialRepository solicitacaoRepository;

    public MaterialSolicitacaoMaterialService(
            MaterialSolicitacaoMaterialRepository repository,
            MaterialSolicitacaoMaterialMapper mapper,
            MaterialRepository materialRepository,
            SolicitacaoMaterialRepository solicitacaoRepository,
            Validator validator
    ) {
        super(repository, repository, validator);
        this.mapper = mapper;
        this.materialRepository = materialRepository;
        this.solicitacaoRepository = solicitacaoRepository;
    }

    @Override
    protected MaterialSolicitacaoMaterial toEntity(MaterialSolicitacaoMaterialDetailsDto dto) {
        MaterialSolicitacaoMaterial entity = mapper.toEntity(dto);
        entity.setMaterial(materialRepository.findById(dto.getId().getIdMaterial())
                .orElseThrow(() -> new RuntimeException("Material não encontrado")));
        entity.setSolicitacaoMaterial(solicitacaoRepository.findById(dto.getId().getIdSolicitacao())
                .orElseThrow(() -> new RuntimeException("Solicitação não encontrada")));
        return entity;
    }

    @Override
    protected void updateEntityFromDto(MaterialSolicitacaoMaterialDetailsDto dto, MaterialSolicitacaoMaterial entity) {
        entity.setQtd(dto.getQtd());
    }

    @Override
    protected MaterialSolicitacaoMaterialDetailsDto toExtendedDto(MaterialSolicitacaoMaterial entity) {
        return mapper.toDetailsDto(entity);
    }

    @Override
    protected MaterialSolicitacaoMaterialDetailsDto toDetailsDto(MaterialSolicitacaoMaterial entity) {
        return mapper.toDetailsDto(entity);
    }

    @Override
    protected Specification<MaterialSolicitacaoMaterial> getSpecificationFromFiltro(MaterialSolicitacaoMaterialFiltroDto filtro) {
        return MaterialSolicitacaoMaterialSpecification.filter(filtro);
    }

    @Override
    protected void markedDeleted(MaterialSolicitacaoMaterial entity) {
        throw new UnsupportedOperationException("Eliminação lógica não suportada para MaterialSolicitacaoMaterial.");
    }
}