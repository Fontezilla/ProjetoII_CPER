package com.example.cper_core.mappers;

import com.example.cper_core.enums.*;
import com.example.cper_core.dtos.departamento.*;
import com.example.cper_core.entities.Departamento;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DepartamentoMapper {

    // --- Método Auxiliar de Enum ---
    @Named("mapSetor")
    default Setor mapSetor(Integer id) {
        return id == null ? null : Setor.fromId(id);
    }

    // --- To DTO ---
    @Named("toDto")
    DepartamentoDto toDto(Departamento entity);

    @IterableMapping(qualifiedByName = "toDto")
    List<DepartamentoDto> toDtoList(List<Departamento> entities);

    // --- To Details DTO ---
    @Named("toDetailsDto")
    @Mapping(target = "setor", source = "setor", qualifiedByName = "mapSetor")
    DepartamentoDetailsDto toDetailsDto(Departamento entity);

    @IterableMapping(qualifiedByName = "toDetailsDto")
    List<DepartamentoDetailsDto> toDetailsDtoList(List<Departamento> entities);

    // --- To Extended DTO ---
    @Named("toExtendedDto")
    @Mapping(target = "setor", source = "setor", qualifiedByName = "mapSetor")
    DepartamentoDetailsExtendedDto toExtendedDto(Departamento entity);

    @IterableMapping(qualifiedByName = "toExtendedDto")
    List<DepartamentoDetailsExtendedDto> toExtendedDtoList(List<Departamento> entities);

    // --- To WithRelationships DTO ---
    @Named("toWithRelationshipsDto")
    DepartamentoWithRelationshipsDto toWithRelationshipsDto(Departamento entity);

    @IterableMapping(qualifiedByName = "toWithRelationshipsDto")
    List<DepartamentoWithRelationshipsDto> toWithRelationshipsDtoList(List<Departamento> entities);

    // --- To Entity ---
    Departamento toEntity(DepartamentoDto dto);

    @Mapping(target = "setor", expression = "java(dto.getSetor() != null ? dto.getSetor().getId() : null)")
    Departamento toEntity(DepartamentoDetailsDto dto);

    @Mapping(target = "setor", expression = "java(dto.getSetor() != null ? dto.getSetor().getId() : null)")
    Departamento toEntity(DepartamentoDetailsExtendedDto dto);

    Departamento toEntity(DepartamentoWithRelationshipsDto dto);

    // --- Partial Update ---
    @Mapping(target = "setor", expression = "java(dto.getSetor() != null ? dto.getSetor().getId() : entity.getSetor())")
    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromExtendedDto(DepartamentoDetailsExtendedDto dto, @MappingTarget Departamento entity);
}