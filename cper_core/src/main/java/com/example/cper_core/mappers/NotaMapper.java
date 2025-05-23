package com.example.cper_core.mappers;

import com.example.cper_core.enums.*;
import com.example.cper_core.dtos.nota.*;
import com.example.cper_core.entities.Nota;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface NotaMapper {

    // --- Método Auxiliar de Enum ---
    @Named("mapPrioridade")
    default Prioridade mapPrioridade(Integer id) {
        return id == null ? null : Prioridade.fromId(id);
    }

    // --- To DTO ---
    @Named("toDto")
    NotaDto toDto(Nota entity);

    @IterableMapping(qualifiedByName = "toDto")
    List<NotaDto> toDtoList(List<Nota> entities);

    // --- To Details DTO ---
    @Named("toDetailsDto")
    @Mapping(target = "prioridade", source = "prioridade", qualifiedByName = "mapPrioridade")
    NotaDetailsDto toDetailsDto(Nota entity);

    @IterableMapping(qualifiedByName = "toDetailsDto")
    List<NotaDetailsDto> toDetailsDtoList(List<Nota> entities);

    // --- To Extended DTO ---
    @Named("toExtendedDto")
    @Mapping(target = "prioridade", source = "prioridade", qualifiedByName = "mapPrioridade")
    NotaDetailsExtendedDto toExtendedDto(Nota entity);

    @IterableMapping(qualifiedByName = "toExtendedDto")
    List<NotaDetailsExtendedDto> toExtendedDtoList(List<Nota> entities);

    // --- To WithRelationships DTO ---
    @Named("toWithRelationshipsDto")
    NotaWithRelationshipsDto toWithRelationshipsDto(Nota entity);

    @IterableMapping(qualifiedByName = "toWithRelationshipsDto")
    List<NotaWithRelationshipsDto> toWithRelationshipsDtoList(List<Nota> entities);

    // --- To Entity ---
    Nota toEntity(NotaDto dto);

    @Mapping(target = "prioridade", expression = "java(dto.getPrioridade() != null ? dto.getPrioridade().getId() : null)")
    Nota toEntity(NotaDetailsDto dto);

    @Mapping(target = "prioridade", expression = "java(dto.getPrioridade() != null ? dto.getPrioridade().getId() : null)")
    Nota toEntity(NotaDetailsExtendedDto dto);

    Nota toEntity(NotaWithRelationshipsDto dto);

    // --- Partial Update ---
    @Mapping(target = "prioridade", expression = "java(dto.getPrioridade() != null ? dto.getPrioridade().getId() : entity.getPrioridade())")
    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromExtendedDto(NotaDetailsExtendedDto dto, @MappingTarget Nota entity);
}