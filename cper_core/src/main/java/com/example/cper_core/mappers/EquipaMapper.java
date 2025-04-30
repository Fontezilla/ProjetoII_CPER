package com.example.cper_core.mappers;

import com.example.cper_core.dtos.anomalia.AnomaliaDetailsExtendedDto;
import com.example.cper_core.dtos.equipa.*;
import com.example.cper_core.entities.Anomalia;
import com.example.cper_core.entities.Equipa;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EquipaMapper {

    // -------- To DTO --------

    @Named("toDto")
    EquipaDto toDto(Equipa entity);

    @IterableMapping(qualifiedByName = "toDto")
    List<EquipaDto> toDtoList(List<Equipa> entities);

    @Named("toDetailsDto")
    EquipaDetailsDto toDetailsDto(Equipa entity);

    @IterableMapping(qualifiedByName = "toDetailsDto")
    List<EquipaDetailsDto> toDetailsDtoList(List<Equipa> entities);

    @Named("toWithAvariaDto")
    EquipaWithAvariaDto toWithAvariaDto(Equipa entity);

    @IterableMapping(qualifiedByName = "toWithAvariaDto")
    List<EquipaWithAvariaDto> toWithAvariaDtoList(List<Equipa> entities);

    @Named("toWithFuncionarioDto")
    EquipaWithFuncionarioDto toWithFuncionarioDto(Equipa entity);

    @IterableMapping(qualifiedByName = "toWithFuncionarioDto")
    List<EquipaWithFuncionarioDto> toWithFuncionarioDtoList(List<Equipa> entities);

    @Named("toWithInspecaoDto")
    EquipaWithInspecaoDto toWithInspecaoDto(Equipa entity);

    @IterableMapping(qualifiedByName = "toWithInspecaoDto")
    List<EquipaWithInspecaoDto> toWithInspecaoDtoList(List<Equipa> entities);

    // -------- To Entity --------

    @Named("toEntityFromDto")
    Equipa toEntity(EquipaDto dto);

    @Named("toEntityFromDetails")
    Equipa toEntity(EquipaDetailsDto dto);

    @Named("toEntityFromAvaria")
    Equipa toEntity(EquipaWithAvariaDto dto);

    @Named("toEntityFromFuncionario")
    Equipa toEntity(EquipaWithFuncionarioDto dto);

    @Named("toEntityFromInspecao")
    Equipa toEntity(EquipaWithInspecaoDto dto);

    // -------- Conversões de listas inversas --------

    @IterableMapping(qualifiedByName = "toEntityFromDto")
    List<Equipa> toEntityList(List<EquipaDto> dtos);

    @IterableMapping(qualifiedByName = "toEntityFromDetails")
    List<Equipa> toEntityDetailsList(List<EquipaDetailsDto> dtos);

    @IterableMapping(qualifiedByName = "toEntityFromAvaria")
    List<Equipa> toEntityWithAvariaList(List<EquipaWithAvariaDto> dtos);

    @IterableMapping(qualifiedByName = "toEntityFromFuncionario")
    List<Equipa> toEntityWithFuncionarioList(List<EquipaWithFuncionarioDto> dtos);

    @IterableMapping(qualifiedByName = "toEntityFromInspecao")
    List<Equipa> toEntityWithInspecaoList(List<EquipaWithInspecaoDto> dtos);

    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromExtendedDto(EquipaDetailsDto dto, @MappingTarget Equipa entity);
}