package com.example.cper_core.mappers;

import com.example.cper_core.enums.*;
import com.example.cper_core.dtos.armazem_lote.*;
import com.example.cper_core.entities.ArmazemLote;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ArmazemLoteMapper {

    // --- To DTO ---
    @Named("toDto")
    ArmazemLoteDto toDto(ArmazemLote entity);

    @IterableMapping(qualifiedByName = "toDto")
    List<ArmazemLoteDto> toDtoList(List<ArmazemLote> entities);

    // --- To Details DTO ---
    @Named("toDetailsDto")
    ArmazemLoteDetailsDto toDetailsDto(ArmazemLote entity);

    @IterableMapping(qualifiedByName = "toDetailsDto")
    List<ArmazemLoteDetailsDto> toDetailsDtoList(List<ArmazemLote> entities);

    // --- To Extended DTO ---
    @Named("toExtendedDto")
    ArmazemLoteDetailsExtendedDto toExtendedDto(ArmazemLote entity);

    @IterableMapping(qualifiedByName = "toExtendedDto")
    List<ArmazemLoteDetailsExtendedDto> toExtendedDtoList(List<ArmazemLote> entities);

    // --- To WithRelationships DTO ---
    @Named("toWithRelationshipsDto")
    ArmazemLoteWithRelationshipsDto toWithRelationshipsDto(ArmazemLote entity);

    @IterableMapping(qualifiedByName = "toWithRelationshipsDto")
    List<ArmazemLoteWithRelationshipsDto> toWithRelationshipsDtoList(List<ArmazemLote> entities);

    // --- To Entity ---
    ArmazemLote toEntity(ArmazemLoteDto dto);

    ArmazemLote toEntity(ArmazemLoteDetailsDto dto);

    ArmazemLote toEntity(ArmazemLoteDetailsExtendedDto dto);

    ArmazemLote toEntity(ArmazemLoteWithRelationshipsDto dto);

    // --- Partial Update ---
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromExtendedDto(ArmazemLoteDetailsExtendedDto dto, @MappingTarget ArmazemLote entity);
}
