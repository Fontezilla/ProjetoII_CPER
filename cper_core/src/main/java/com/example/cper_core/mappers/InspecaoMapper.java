package com.example.cper_core.mappers;

import com.example.cper_core.enums.*;
import com.example.cper_core.dtos.inspecao.*;
import com.example.cper_core.entities.Inspecao;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface InspecaoMapper {

    // --- Métodos Auxiliares de Enum ---
    @Named("mapTipoInspecao")
    default TipoInspecao mapTipoInspecao(Integer id) {
        return id == null ? null : TipoInspecao.fromId(id);
    }

    @Named("mapEstadoInspecao")
    default EstadoInspecao mapEstadoInspecao(Integer id) {
        return id == null ? null : EstadoInspecao.fromId(id);
    }

    // --- To DTO ---
    @Named("toDto")
    InspecaoDto toDto(Inspecao entity);

    @IterableMapping(qualifiedByName = "toDto")
    List<InspecaoDto> toDtoList(List<Inspecao> entities);

    // --- To Details DTO ---
    @Named("toDetailsDto")
    @Mapping(target = "tipo", source = "tipo", qualifiedByName = "mapTipoInspecao")
    @Mapping(target = "estado", source = "estado", qualifiedByName = "mapEstadoInspecao")
    InspecaoDetailsDto toDetailsDto(Inspecao entity);

    @IterableMapping(qualifiedByName = "toDetailsDto")
    List<InspecaoDetailsDto> toDetailsDtoList(List<Inspecao> entities);

    // --- To Extended DTO ---
    @Named("toExtendedDto")
    @Mapping(target = "tipo", source = "tipo", qualifiedByName = "mapTipoInspecao")
    @Mapping(target = "estado", source = "estado", qualifiedByName = "mapEstadoInspecao")
    InspecaoDetailsExtendedDto toExtendedDto(Inspecao entity);

    @IterableMapping(qualifiedByName = "toExtendedDto")
    List<InspecaoDetailsExtendedDto> toExtendedDtoList(List<Inspecao> entities);

    // --- To WithRelationships DTO ---
    @Named("toWithRelationshipsDto")
    InspecaoWithRelationshipsDto toWithRelationshipsDto(Inspecao entity);

    @IterableMapping(qualifiedByName = "toWithRelationshipsDto")
    List<InspecaoWithRelationshipsDto> toWithRelationshipsDtoList(List<Inspecao> entities);

    // --- To Entity ---
    Inspecao toEntity(InspecaoDto dto);

    @Mapping(target = "tipo", expression = "java(dto.getTipo() != null ? dto.getTipo().getId() : null)")
    @Mapping(target = "estado", expression = "java(dto.getEstado() != null ? dto.getEstado().getId() : null)")
    Inspecao toEntity(InspecaoDetailsDto dto);

    @Mapping(target = "tipo", expression = "java(dto.getTipo() != null ? dto.getTipo().getId() : null)")
    @Mapping(target = "estado", expression = "java(dto.getEstado() != null ? dto.getEstado().getId() : null)")
    Inspecao toEntity(InspecaoDetailsExtendedDto dto);

    Inspecao toEntity(InspecaoWithRelationshipsDto dto);

    // --- Partial Update ---
    @Mapping(target = "tipo", expression = "java(dto.getTipo() != null ? dto.getTipo().getId() : entity.getTipo())")
    @Mapping(target = "estado", expression = "java(dto.getEstado() != null ? dto.getEstado().getId() : entity.getEstado())")
    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromExtendedDto(InspecaoDetailsExtendedDto dto, @MappingTarget Inspecao entity);
}