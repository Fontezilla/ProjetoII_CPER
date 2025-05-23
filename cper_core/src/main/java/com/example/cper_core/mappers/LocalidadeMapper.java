package com.example.cper_core.mappers;

import com.example.cper_core.enums.*;
import com.example.cper_core.dtos.localidade.*;
import com.example.cper_core.entities.Localidade;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LocalidadeMapper {

    // --- To DTO ---
    @Named("toDto")
    LocalidadeDto toDto(Localidade entity);

    @IterableMapping(qualifiedByName = "toDto")
    List<LocalidadeDto> toDtoList(List<Localidade> entities);

    // --- To Details DTO ---
    @Named("toDetailsDto")
    LocalidadeDetailsDto toDetailsDto(Localidade entity);

    @IterableMapping(qualifiedByName = "toDetailsDto")
    List<LocalidadeDetailsDto> toDetailsDtoList(List<Localidade> entities);

    // --- To Extended DTO ---
    @Named("toExtendedDto")
    LocalidadeDetailsExtendedDto toExtendedDto(Localidade entity);

    @IterableMapping(qualifiedByName = "toExtendedDto")
    List<LocalidadeDetailsExtendedDto> toExtendedDtoList(List<Localidade> entities);

    // --- To WithRelationships DTO ---
    @Named("toWithRelationshipsDto")
    LocalidadeWithRelationshipsDto toWithRelationshipsDto(Localidade entity);

    @IterableMapping(qualifiedByName = "toWithRelationshipsDto")
    List<LocalidadeWithRelationshipsDto> toWithRelationshipsDtoList(List<Localidade> entities);

    // --- To Entity ---
    Localidade toEntity(LocalidadeDto dto);

    Localidade toEntity(LocalidadeDetailsDto dto);

    Localidade toEntity(LocalidadeDetailsExtendedDto dto);

    Localidade toEntity(LocalidadeWithRelationshipsDto dto);

    // --- Partial Update ---
    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromExtendedDto(LocalidadeDetailsExtendedDto dto, @MappingTarget Localidade entity);
}