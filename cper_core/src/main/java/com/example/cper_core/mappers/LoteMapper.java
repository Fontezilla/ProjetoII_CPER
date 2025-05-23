package com.example.cper_core.mappers;

import com.example.cper_core.enums.*;
import com.example.cper_core.entities.Lote;
import com.example.cper_core.dtos.lote.*;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LoteMapper {

    // --- To DTO ---
    @Named("toDto")
    LoteDto toDto(Lote entity);

    @IterableMapping(qualifiedByName = "toDto")
    List<LoteDto> toDtoList(List<Lote> entities);

    // --- To Details DTO ---
    @Named("toDetailsDto")
    LoteDetailsDto toDetailsDto(Lote entity);

    @IterableMapping(qualifiedByName = "toDetailsDto")
    List<LoteDetailsDto> toDetailsDtoList(List<Lote> entities);

    // --- To Extended DTO ---
    @Named("toExtendedDto")
    LoteDetailsExtendedDto toExtendedDto(Lote entity);

    @IterableMapping(qualifiedByName = "toExtendedDto")
    List<LoteDetailsExtendedDto> toExtendedDtoList(List<Lote> entities);

    // --- To WithRelationships DTO ---
    @Named("toWithRelationshipsDto")
    LoteWithRelationshipsDto toWithRelationshipsDto(Lote entity);

    @IterableMapping(qualifiedByName = "toWithRelationshipsDto")
    List<LoteWithRelationshipsDto> toWithRelationshipsDtoList(List<Lote> entities);

    // --- To Entity ---
    Lote toEntity(LoteDto dto);

    Lote toEntity(LoteDetailsDto dto);

    Lote toEntity(LoteDetailsExtendedDto dto);

    Lote toEntity(LoteWithRelationshipsDto dto);

    // --- Partial Update ---
    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromExtendedDto(LoteDetailsExtendedDto dto, @MappingTarget Lote entity);
}
