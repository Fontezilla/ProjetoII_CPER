package com.example.cper_core.mappers;

import com.example.cper_core.enums.*;
import com.example.cper_core.dtos.equipa.*;
import com.example.cper_core.entities.Equipa;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EquipaMapper {

    // --- To DTO ---
    @Named("toDto")
    EquipaDto toDto(Equipa entity);

    @IterableMapping(qualifiedByName = "toDto")
    List<EquipaDto> toDtoList(List<Equipa> entities);

    // --- To Details DTO ---
    @Named("toDetailsDto")
    EquipaDetailsDto toDetailsDto(Equipa entity);

    @IterableMapping(qualifiedByName = "toDetailsDto")
    List<EquipaDetailsDto> toDetailsDtoList(List<Equipa> entities);

    // --- To Extended DTO ---
    @Named("toExtendedDto")
    EquipaDetailsExtendedDto toExtendedDto(Equipa entity);

    @IterableMapping(qualifiedByName = "toExtendedDto")
    List<EquipaDetailsExtendedDto> toExtendedDtoList(List<Equipa> entities);

    // --- To WithRelationships DTO ---
    @Named("toWithRelationshipsDto")
    EquipaWithRelationshipsDto toWithRelationshipsDto(Equipa entity);

    @IterableMapping(qualifiedByName = "toWithRelationshipsDto")
    List<EquipaWithRelationshipsDto> toWithRelationshipsDtoList(List<Equipa> entities);

    // --- To Entity ---
    Equipa toEntity(EquipaDto dto);

    Equipa toEntity(EquipaDetailsDto dto);

    Equipa toEntity(EquipaDetailsExtendedDto dto);

    Equipa toEntity(EquipaWithRelationshipsDto dto);

    // --- Partial Update ---
    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromExtendedDto(EquipaDetailsExtendedDto dto, @MappingTarget Equipa entity);
}