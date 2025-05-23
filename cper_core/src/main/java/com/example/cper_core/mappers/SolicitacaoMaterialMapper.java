package com.example.cper_core.mappers;

import com.example.cper_core.enums.*;
import com.example.cper_core.dtos.solicitacao_material.*;
import com.example.cper_core.entities.SolicitacaoMaterial;
import org.mapstruct.*;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SolicitacaoMaterialMapper {

    // --- Método Auxiliar de Enum ---
    @Named("mapEstadoSolicitacaoMaterial")
    default EstadoSolicitacaoMaterial mapEstadoSolicitacaoMaterial(Integer id) {
        return id == null ? null : EstadoSolicitacaoMaterial.fromId(id);
    }

    // --- To DTO ---
    @Named("toDto")
    SolicitacaoMaterialDto toDto(SolicitacaoMaterial entity);

    @IterableMapping(qualifiedByName = "toDto")
    List<SolicitacaoMaterialDto> toDtoList(List<SolicitacaoMaterial> entities);

    // --- To Details DTO ---
    @Named("toDetailsDto")
    @Mapping(target = "estado", source = "estado", qualifiedByName = "mapEstadoSolicitacaoMaterial")
    SolicitacaoMaterialDetailsDto toDetailsDto(SolicitacaoMaterial entity);

    @IterableMapping(qualifiedByName = "toDetailsDto")
    List<SolicitacaoMaterialDetailsDto> toDetailsDtoList(List<SolicitacaoMaterial> entities);

    // --- To Extended DTO ---
    @Named("toExtendedDto")
    @Mapping(target = "estado", source = "estado", qualifiedByName = "mapEstadoSolicitacaoMaterial")
    SolicitacaoMaterialDetailsExtendedDto toExtendedDto(SolicitacaoMaterial entity);

    @IterableMapping(qualifiedByName = "toExtendedDto")
    List<SolicitacaoMaterialDetailsExtendedDto> toExtendedDtoList(List<SolicitacaoMaterial> entities);

    // --- To WithRelationships DTO ---
    @Named("toWithRelationshipsDto")
    SolicitacaoMaterialWithRelationshipsDto toWithRelationshipsDto(SolicitacaoMaterial entity);

    @IterableMapping(qualifiedByName = "toWithRelationshipsDto")
    List<SolicitacaoMaterialWithRelationshipsDto> toWithRelationshipsDtoList(List<SolicitacaoMaterial> entities);

    // --- To Entity ---
    SolicitacaoMaterial toEntity(SolicitacaoMaterialDto dto);

    @Mapping(target = "estado", expression = "java(dto.getEstado() != null ? dto.getEstado().getId() : null)")
    SolicitacaoMaterial toEntity(SolicitacaoMaterialDetailsDto dto);

    @Mapping(target = "estado", expression = "java(dto.getEstado() != null ? dto.getEstado().getId() : null)")
    SolicitacaoMaterial toEntity(SolicitacaoMaterialDetailsExtendedDto dto);

    SolicitacaoMaterial toEntity(SolicitacaoMaterialWithRelationshipsDto dto);

    // --- Partial Update ---
    @Mapping(target = "estado", expression = "java(dto.getEstado() != null ? dto.getEstado().getId() : entity.getEstado())")
    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromExtendedDto(SolicitacaoMaterialDetailsExtendedDto dto, @MappingTarget SolicitacaoMaterial entity);
}