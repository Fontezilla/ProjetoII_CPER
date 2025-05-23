package com.example.cper_core.mappers;

import com.example.cper_core.enums.*;
import com.example.cper_core.dtos.fatura.*;
import com.example.cper_core.entities.Fatura;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FaturaMapper {

    // --- To DTO ---
    @Named("toDto")
    FaturaDto toDto(Fatura entity);

    @IterableMapping(qualifiedByName = "toDto")
    List<FaturaDto> toDtoList(List<Fatura> entities);

    // --- To Details DTO ---
    @Named("toDetailsDto")
    FaturaDetailsDto toDetailsDto(Fatura entity);

    @IterableMapping(qualifiedByName = "toDetailsDto")
    List<FaturaDetailsDto> toDetailsDtoList(List<Fatura> entities);

    // --- To Extended DTO ---
    @Named("toExtendedDto")
    FaturaDetailsExtendedDto toExtendedDto(Fatura entity);

    @IterableMapping(qualifiedByName = "toExtendedDto")
    List<FaturaDetailsExtendedDto> toExtendedDtoList(List<Fatura> entities);

    // --- To WithRelationships DTO ---
    @Named("toWithRelationshipsDto")
    FaturaWithRelationshipsDto toWithRelationshipsDto(Fatura entity);

    @IterableMapping(qualifiedByName = "toWithRelationshipsDto")
    List<FaturaWithRelationshipsDto> toWithRelationshipsDtoList(List<Fatura> entities);

    // --- To Entity ---
    Fatura toEntity(FaturaDto dto);

    Fatura toEntity(FaturaDetailsDto dto);

    Fatura toEntity(FaturaDetailsExtendedDto dto);

    Fatura toEntity(FaturaWithRelationshipsDto dto);

    // --- Partial Update ---
    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromExtendedDto(FaturaDetailsExtendedDto dto, @MappingTarget Fatura entity);
}