package com.example.cper_core.mappers;

import com.example.cper_core.enums.*;
import com.example.cper_core.dtos.resposta.*;
import com.example.cper_core.entities.Resposta;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RespostaMapper {

    // --- To DTO ---
    @Named("toDto")
    RespostaDto toDto(Resposta entity);

    @IterableMapping(qualifiedByName = "toDto")
    List<RespostaDto> toDtoList(List<Resposta> entities);

    // --- To Details DTO ---
    @Named("toDetailsDto")
    RespostaDetailsDto toDetailsDto(Resposta entity);

    @IterableMapping(qualifiedByName = "toDetailsDto")
    List<RespostaDetailsDto> toDetailsDtoList(List<Resposta> entities);

    // --- To Extended DTO ---
    @Named("toExtendedDto")
    RespostaDetailsExtendedDto toExtendedDto(Resposta entity);

    @IterableMapping(qualifiedByName = "toExtendedDto")
    List<RespostaDetailsExtendedDto> toExtendedDtoList(List<Resposta> entities);

    // --- To WithRelationships DTO ---
    @Named("toWithRelationshipsDto")
    RespostaWithRelationshipsDto toWithRelationshipsDto(Resposta entity);

    @IterableMapping(qualifiedByName = "toWithRelationshipsDto")
    List<RespostaWithRelationshipsDto> toWithRelationshipsDtoList(List<Resposta> entities);

    // --- To Entity ---
    Resposta toEntity(RespostaDto dto);

    Resposta toEntity(RespostaDetailsDto dto);

    Resposta toEntity(RespostaDetailsExtendedDto dto);

    Resposta toEntity(RespostaWithRelationshipsDto dto);

    // --- Partial Update ---
    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromExtendedDto(RespostaDetailsExtendedDto dto, @MappingTarget Resposta entity);
}