package com.example.cper_core.mappers;

import com.example.cper_core.enums.*;
import com.example.cper_core.dtos.funcionario.*;
import com.example.cper_core.entities.Funcionario;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FuncionarioMapper {

    // --- Método Auxiliar de Enum ---
    @Named("mapEstadoFuncionario")
    default EstadoFuncionario mapEstadoFuncionario(Integer id) {
        return id == null ? null : EstadoFuncionario.fromId(id);
    }

    // --- To DTO ---
    @Named("toDto")
    FuncionarioDto toDto(Funcionario entity);

    @IterableMapping(qualifiedByName = "toDto")
    List<FuncionarioDto> toDtoList(List<Funcionario> entities);

    // --- To Details DTO ---
    @Named("toDetailsDto")
    @Mapping(target = "estado", source = "estado", qualifiedByName = "mapEstadoFuncionario")
    FuncionarioDetailsDto toDetailsDto(Funcionario entity);

    @IterableMapping(qualifiedByName = "toDetailsDto")
    List<FuncionarioDetailsDto> toDetailsDtoList(List<Funcionario> entities);

    // --- To Extended DTO ---
    @Named("toExtendedDto")
    @Mapping(target = "estado", source = "estado", qualifiedByName = "mapEstadoFuncionario")
    FuncionarioDetailsExtendedDto toExtendedDto(Funcionario entity);

    @IterableMapping(qualifiedByName = "toExtendedDto")
    List<FuncionarioDetailsExtendedDto> toExtendedDtoList(List<Funcionario> entities);

    // --- To WithRelationships DTO ---
    @Named("toWithRelationshipsDto")
    FuncionarioWithRelationshipsDto toWithRelationshipsDto(Funcionario entity);

    @IterableMapping(qualifiedByName = "toWithRelationshipsDto")
    List<FuncionarioWithRelationshipsDto> toWithRelationshipsDtoList(List<Funcionario> entities);

    // --- To Entity ---
    Funcionario toEntity(FuncionarioDto dto);

    @Mapping(target = "estado", expression = "java(dto.getEstado() != null ? dto.getEstado().getId() : null)")
    Funcionario toEntity(FuncionarioDetailsDto dto);

    @Mapping(target = "estado", expression = "java(dto.getEstado() != null ? dto.getEstado().getId() : null)")
    Funcionario toEntity(FuncionarioDetailsExtendedDto dto);

    Funcionario toEntity(FuncionarioWithRelationshipsDto dto);

    // --- Partial Update ---
    @Mapping(target = "estado", expression = "java(dto.getEstado() != null ? dto.getEstado().getId() : entity.getEstado())")
    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromExtendedDto(FuncionarioDetailsExtendedDto dto, @MappingTarget Funcionario entity);
}