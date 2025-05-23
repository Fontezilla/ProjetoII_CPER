package com.example.cper_core.mappers;

import com.example.cper_core.enums.*;
import com.example.cper_core.dtos.centro_producao.*;
import com.example.cper_core.entities.CentroProducao;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CentroProducaoMapper {

    // --- Métodos Auxiliares de Enum ---
    @Named("mapTipoEnergiaRenovavel")
    default TipoEnergiaRenovavel mapTipoEnergiaRenovavel(Integer id) {
        return id == null ? null : TipoEnergiaRenovavel.fromId(id);
    }

    @Named("mapEstadoCentro")
    default EstadoCentro mapEstadoCentro(Integer id) {
        return id == null ? null : EstadoCentro.fromId(id);
    }

    // --- To DTO ---
    @Named("toDto")
    CentroProducaoDto toDto(CentroProducao entity);

    @IterableMapping(qualifiedByName = "toDto")
    List<CentroProducaoDto> toDtoList(List<CentroProducao> entities);

    // --- To Details DTO ---
    @Named("toDetailsDto")
    @Mapping(target = "tipoEnergia", source = "tipoEnergia", qualifiedByName = "mapTipoEnergiaRenovavel")
    @Mapping(target = "estado", source = "estado", qualifiedByName = "mapEstadoCentro")
    CentroProducaoDetailsDto toDetailsDto(CentroProducao entity);

    @IterableMapping(qualifiedByName = "toDetailsDto")
    List<CentroProducaoDetailsDto> toDetailsDtoList(List<CentroProducao> entities);

    // --- To Extended DTO ---
    @Named("toExtendedDto")
    @Mapping(target = "tipoEnergia", source = "tipoEnergia", qualifiedByName = "mapTipoEnergiaRenovavel")
    @Mapping(target = "estado", source = "estado", qualifiedByName = "mapEstadoCentro")
    CentroProducaoDetailsExtendedDto toExtendedDto(CentroProducao entity);

    @IterableMapping(qualifiedByName = "toExtendedDto")
    List<CentroProducaoDetailsExtendedDto> toExtendedDtoList(List<CentroProducao> entities);

    // --- To WithRelationships DTO ---
    @Named("toWithRelationshipsDto")
    CentroProducaoWithRelationshipsDto toWithRelationshipsDto(CentroProducao entity);

    @IterableMapping(qualifiedByName = "toWithRelationshipsDto")
    List<CentroProducaoWithRelationshipsDto> toWithRelationshipsDtoList(List<CentroProducao> entities);

    // --- To Entity ---
    CentroProducao toEntity(CentroProducaoDto dto);

    @Mapping(target = "tipoEnergia", expression = "java(dto.getTipoEnergia() != null ? dto.getTipoEnergia().getId() : null)")
    @Mapping(target = "estado", expression = "java(dto.getEstado() != null ? dto.getEstado().getId() : null)")
    CentroProducao toEntity(CentroProducaoDetailsDto dto);

    @Mapping(target = "tipoEnergia", expression = "java(dto.getTipoEnergia() != null ? dto.getTipoEnergia().getId() : null)")
    @Mapping(target = "estado", expression = "java(dto.getEstado() != null ? dto.getEstado().getId() : null)")
    CentroProducao toEntity(CentroProducaoDetailsExtendedDto dto);

    CentroProducao toEntity(CentroProducaoWithRelationshipsDto dto);

    // --- Partial Update ---
    @Mapping(target = "tipoEnergia", expression = "java(dto.getTipoEnergia() != null ? dto.getTipoEnergia().getId() : entity.getTipoEnergia())")
    @Mapping(target = "estado", expression = "java(dto.getEstado() != null ? dto.getEstado().getId() : entity.getEstado())")
    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromExtendedDto(CentroProducaoDetailsExtendedDto dto, @MappingTarget CentroProducao entity);
}