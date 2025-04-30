package com.example.cper_core.mappers;

import com.example.cper_core.dtos.solicitacao_material.*;
import com.example.cper_core.entities.SolicitacaoMaterial;
import com.example.cper_core.enums.EstadoSolicitacaoMaterial;
import org.mapstruct.*;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SolicitacaoMaterialMapper {

    // -------- To DTO --------

    @Named("toDto")
    SolicitacaoMaterialDto toDto(SolicitacaoMaterial entity);

    @IterableMapping(qualifiedByName = "toDto")
    List<SolicitacaoMaterialDto> toDtoList(List<SolicitacaoMaterial> entities);

    @Named("toDetailsDto")
    @Mapping(source = "estado", target = "estado", qualifiedByName = "mapEstadoToString")
    SolicitacaoMaterialDetailsDto toDetailsDto(SolicitacaoMaterial entity);

    @IterableMapping(qualifiedByName = "toDetailsDto")
    List<SolicitacaoMaterialDetailsDto> toDetailsDtoList(List<SolicitacaoMaterial> entities);

    @Named("toWithSolicitacaoDto")
    SolicitacaoMaterialWithSolicitacaoDto toWithSolicitacaoDto(SolicitacaoMaterial entity);

    @IterableMapping(qualifiedByName = "toWithSolicitacaoDto")
    List<SolicitacaoMaterialWithSolicitacaoDto> toWithSolicitacaoDtoList(List<SolicitacaoMaterial> entities);

    // -------- To Entity --------

    @Named("toEntity")
    SolicitacaoMaterial toEntity(SolicitacaoMaterialDto dto);

    @Named("toEntity")
    @Mapping(source = "estado", target = "estado", qualifiedByName = "mapStringToEstado")
    SolicitacaoMaterial toEntity(SolicitacaoMaterialDetailsDto dto);

    @Named("toEntity")
    SolicitacaoMaterial toEntity(SolicitacaoMaterialWithSolicitacaoDto dto);

    // -------- Conversões de listas inversas --------

    @IterableMapping(qualifiedByName = "toEntity")
    List<SolicitacaoMaterial> toEntityList(List<SolicitacaoMaterialDto> dtos);

    @IterableMapping(qualifiedByName = "toEntity")
    List<SolicitacaoMaterial> toEntityDetailsList(List<SolicitacaoMaterialDetailsDto> dtos);

    @IterableMapping(qualifiedByName = "toEntity")
    List<SolicitacaoMaterial> toEntityWithSolicitacaoList(List<SolicitacaoMaterialWithSolicitacaoDto> dtos);

    // -------- Métodos auxiliares --------

    @Named("mapEstadoToString")
    default String mapEstadoToString(Integer estadoId) {
        if (estadoId == null) return null;
        return EstadoSolicitacaoMaterial.fromId(estadoId).name();
    }

    @Named("mapStringToEstado")
    default Integer mapStringToEstado(String estadoName) {
        if (estadoName == null) return null;
        return EstadoSolicitacaoMaterial.fromName(estadoName).getId();
    }

    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromExtendedDto(SolicitacaoMaterialDetailsDto dto, @MappingTarget SolicitacaoMaterial entity);
}
