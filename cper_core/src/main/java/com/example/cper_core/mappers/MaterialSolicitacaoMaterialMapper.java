package com.example.cper_core.mappers;

import com.example.cper_core.enums.*;
import com.example.cper_core.dtos.material_solicitacao_material.MaterialSolicitacaoMaterialDetailsDto;
import com.example.cper_core.dtos.material_solicitacao_material.MaterialSolicitacaoMaterialDto;
import com.example.cper_core.entities.MaterialSolicitacaoMaterial;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MaterialSolicitacaoMaterialMapper {

    // --- To DTO ---
    @Named("toDto")
    MaterialSolicitacaoMaterialDto toDto(MaterialSolicitacaoMaterial entity);

    @IterableMapping(qualifiedByName = "toDto")
    List<MaterialSolicitacaoMaterialDto> toDtoList(List<MaterialSolicitacaoMaterial> entities);

    // --- To Details DTO ---
    @Named("toDetailsDto")
    @Mapping(source = "qtd", target = "qtd")
    MaterialSolicitacaoMaterialDetailsDto toDetailsDto(MaterialSolicitacaoMaterial entity);

    @IterableMapping(qualifiedByName = "toDetailsDto")
    List<MaterialSolicitacaoMaterialDetailsDto> toDetailsDtoList(List<MaterialSolicitacaoMaterial> entities);

    // --- To Entity ---
    @Mapping(target = "material", ignore = true)
    @Mapping(target = "solicitacaoMaterial", ignore = true)
    MaterialSolicitacaoMaterial toEntity(MaterialSolicitacaoMaterialDto dto);
}
