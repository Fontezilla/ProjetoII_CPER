package com.example.cper_core.mappers;

import com.example.cper_core.dtos.material_solicitacao_material.*;
import com.example.cper_core.entities.MaterialSolicitacaoMaterial;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MaterialSolicitacaoMaterialMapper {

    // -------- To DTO --------

    @Named("toDto")
    MaterialSolicitacaoMaterialDto toDto(MaterialSolicitacaoMaterial entity);

    @IterableMapping(qualifiedByName = "toDto")
    List<MaterialSolicitacaoMaterialDto> toDtoList(List<MaterialSolicitacaoMaterial> entities);

    // -------- To Entity --------

    @Named("toEntity")
    MaterialSolicitacaoMaterial toEntity(MaterialSolicitacaoMaterialDto dto);

    @IterableMapping(qualifiedByName = "toEntity")
    List<MaterialSolicitacaoMaterial> toEntityList(List<MaterialSolicitacaoMaterialDto> dtos);

    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromExtendedDto(MaterialSolicitacaoMaterialDto dto, @MappingTarget MaterialSolicitacaoMaterial entity);
}
