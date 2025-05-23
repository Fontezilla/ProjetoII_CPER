package com.example.cper_core.mappers;

import com.example.cper_core.enums.*;
import com.example.cper_core.dtos.historico_consumo.HistoricoConsumoDetailsDto;
import com.example.cper_core.dtos.historico_consumo.HistoricoConsumoDetailsExtendedDto;
import com.example.cper_core.dtos.historico_consumo.HistoricoConsumoDto;
import com.example.cper_core.dtos.historico_consumo.HistoricoConsumoWithRelationshipsDto;
import com.example.cper_core.entities.HistoricoConsumo;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface HistoricoConsumoMapper {

    // --- To DTO ---
    @Named("toDto")
    HistoricoConsumoDto toDto(HistoricoConsumo entity);

    @IterableMapping(qualifiedByName = "toDto")
    List<HistoricoConsumoDto> toDtoList(List<HistoricoConsumo> entities);

    // --- To Details DTO ---
    @Named("toDetailsDto")
    HistoricoConsumoDetailsDto toDetailsDto(HistoricoConsumo entity);

    @IterableMapping(qualifiedByName = "toDetailsDto")
    List<HistoricoConsumoDetailsDto> toDetailsDtoList(List<HistoricoConsumo> entities);

    // --- To Extended DTO ---
    @Named("toExtendedDto")
    HistoricoConsumoDetailsExtendedDto toExtendedDto(HistoricoConsumo entity);

    @IterableMapping(qualifiedByName = "toExtendedDto")
    List<HistoricoConsumoDetailsExtendedDto> toExtendedDtoList(List<HistoricoConsumo> entities);

    // --- To WithRelationships DTO ---
    @Named("toWithRelationshipsDto")
    HistoricoConsumoWithRelationshipsDto toWithRelationshipsDto(HistoricoConsumo entity);

    @IterableMapping(qualifiedByName = "toWithRelationshipsDto")
    List<HistoricoConsumoWithRelationshipsDto> toWithRelationshipsDtoList(List<HistoricoConsumo> entities);

    // --- To Entity ---
    HistoricoConsumo toEntity(HistoricoConsumoDto dto);

    HistoricoConsumo toEntity(HistoricoConsumoDetailsDto dto);

    HistoricoConsumo toEntity(HistoricoConsumoDetailsExtendedDto dto);

    HistoricoConsumo toEntity(HistoricoConsumoWithRelationshipsDto dto);

    // --- Partial Update ---
    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromExtendedDto(HistoricoConsumoDetailsExtendedDto dto, @MappingTarget HistoricoConsumo entity);
}