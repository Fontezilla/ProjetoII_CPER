package com.example.cper_core.mappers;

import com.example.cper_core.enums.*;
import com.example.cper_core.dtos.armazem.*;
import com.example.cper_core.entities.Armazem;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ArmazemMapper {

    // --- Métodos Auxiliares de Enum ---
    @Named("mapEstadoArmazem")
    default EstadoArmazem mapEstadoArmazem(Integer id) {
        return id == null ? null : EstadoArmazem.fromId(id);
    }

    // --- To DTO ---
    @Named("toDto")
    ArmazemDto toDto(Armazem entity);

    @IterableMapping(qualifiedByName = "toDto")
    List<ArmazemDto> toDtoList(List<Armazem> entities);

    // --- To Details DTO ---
    @Named("toDetailsDto")
    @Mapping(target = "estado", source = "estado", qualifiedByName = "mapEstadoArmazem")
    ArmazemDetailsDto toDetailsDto(Armazem entity);

    @IterableMapping(qualifiedByName = "toDetailsDto")
    List<ArmazemDetailsDto> toDetailsDtoList(List<Armazem> entities);

    // --- To Extended DTO ---
    @Named("toExtendedDto")
    @Mapping(target = "estado", source = "estado", qualifiedByName = "mapEstadoArmazem")
    ArmazemDetailsExtendedDto toExtendedDto(Armazem entity);

    @IterableMapping(qualifiedByName = "toExtendedDto")
    List<ArmazemDetailsExtendedDto> toExtendedDtoList(List<Armazem> entities);

    // --- To WithRelationships DTO ---
    @Named("toWithRelationshipsDto")
    ArmazemWithRelationshipsDto toWithRelationshipsDto(Armazem entity);

    @IterableMapping(qualifiedByName = "toWithRelationshipsDto")
    List<ArmazemWithRelationshipsDto> toWithRelationshipsDtoList(List<Armazem> entities);

    // --- To Entity ---
    Armazem toEntity(ArmazemDto dto);

    @Mapping(target = "estado", expression = "java(dto.getEstado() != null ? dto.getEstado().getId() : null)")
    Armazem toEntity(ArmazemDetailsDto dto);

    @Mapping(target = "estado", expression = "java(dto.getEstado() != null ? dto.getEstado().getId() : null)")
    Armazem toEntity(ArmazemDetailsExtendedDto dto);

    Armazem toEntity(ArmazemWithRelationshipsDto dto);

    // --- Partial Update ---
    @Mapping(target = "estado", expression = "java(dto.getEstado() != null ? dto.getEstado().getId() : entity.getEstado())")
    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromExtendedDto(ArmazemDetailsExtendedDto dto, @MappingTarget Armazem entity);
}