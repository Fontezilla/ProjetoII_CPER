package com.example.cper_core.mappers;

import com.example.cper_core.dtos.historico_energia.*;
import com.example.cper_core.entities.HistoricoEnergia;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface HistoricoEnergiaMapper {

    // --- To DTO ---
    @Named("toDto")
    HistoricoEnergiaDto toDto(HistoricoEnergia entity);

    @IterableMapping(qualifiedByName = "toDto")
    List<HistoricoEnergiaDto> toDtoList(List<HistoricoEnergia> entities);

    // --- To Details DTO ---
    @Named("toDetailsDto")
    HistoricoEnergiaDetailsDto toDetailsDto(HistoricoEnergia entity);

    @IterableMapping(qualifiedByName = "toDetailsDto")
    List<HistoricoEnergiaDetailsDto> toDetailsDtoList(List<HistoricoEnergia> entities);

    // --- To Extended DTO ---
    @Named("toExtendedDto")
    HistoricoEnergiaDetailsExtendedDto toExtendedDto(HistoricoEnergia entity);

    @IterableMapping(qualifiedByName = "toExtendedDto")
    List<HistoricoEnergiaDetailsExtendedDto> toExtendedDtoList(List<HistoricoEnergia> entities);

    // --- To Entity ---
    HistoricoEnergia toEntity(HistoricoEnergiaDto dto);

    HistoricoEnergia toEntity(HistoricoEnergiaDetailsDto dto);

    HistoricoEnergia toEntity(HistoricoEnergiaDetailsExtendedDto dto);

    // --- Partial Update ---
    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromExtendedDto(HistoricoEnergiaDetailsExtendedDto dto, @MappingTarget HistoricoEnergia entity);
}