package com.example.cper_core.mappers;

import com.example.cper_core.dtos.anomalia.AnomaliaDetailsExtendedDto;
import com.example.cper_core.dtos.departamento.*;
import com.example.cper_core.entities.Anomalia;
import com.example.cper_core.entities.Departamento;
import com.example.cper_core.enums.Setor;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DepartamentoMapper {

    // -------- To DTO --------

    @Named("toDto")
    DepartamentoDto toDto(Departamento entity);

    @IterableMapping(qualifiedByName = "toDto")
    List<DepartamentoDto> toDtoList(List<Departamento> entities);

    @Named("toDetailsDto")
    @Mapping(source = "setor", target = "setor", qualifiedByName = "mapSetorToString")
    DepartamentoDetailsDto toDetailsDto(Departamento entity);

    @IterableMapping(qualifiedByName = "toDetailsDto")
    List<DepartamentoDetailsDto> toDetailsDtoList(List<Departamento> entities);

    @Named("toDetailsExtendedDto")
    DepartamentoDetailsExtendedDto toDetailsExtendedDto(Departamento entity);

    @IterableMapping(qualifiedByName = "toDetailsExtendedDto")
    List<DepartamentoDetailsExtendedDto> toDetailsExtendedDtoList(List<Departamento> entities);

    @Named("toWithArmazemDto")
    DepartamentoWithArmazemDto toWithArmazemDto(Departamento entity);

    @IterableMapping(qualifiedByName = "toWithArmazemDto")
    List<DepartamentoWithArmazemDto> toWithArmazemDtoList(List<Departamento> entities);

    @Named("toWithFuncionarioDto")
    DepartamentoWithFuncionarioDto toWithFuncionarioDto(Departamento entity);

    @IterableMapping(qualifiedByName = "toWithFuncionarioDto")
    List<DepartamentoWithFuncionarioDto> toWithFuncionarioDtoList(List<Departamento> entities);

    @Named("toWithCentroDto")
    DepartamentoWithCentroDto toWithCentroDto(Departamento entity);

    @IterableMapping(qualifiedByName = "toWithCentroDto")
    List<DepartamentoWithCentroDto> toWithCentroDtoList(List<Departamento> entities);

    @Named("toWithEquipaDto")
    DepartamentoWithEquipaDto toWithEquipaDto(Departamento entity);

    @IterableMapping(qualifiedByName = "toWithEquipaDto")
    List<DepartamentoWithEquipaDto> toWithEquipaDtoList(List<Departamento> entities);

    @Named("toWithResponsavelDto")
    DepartamentoWithResponsavelDto toWithResponsavelDto(Departamento entity);

    @IterableMapping(qualifiedByName = "toWithResponsavelDto")
    List<DepartamentoWithResponsavelDto> toWithResponsavelDtoList(List<Departamento> entities);

    // -------- To Entity --------

    @Mapping(target = "setor", source = "setor", qualifiedByName = "mapStringToSetor")
    Departamento toEntity(DepartamentoDetailsDto dto);

    Departamento toEntity(DepartamentoDto dto);

    Departamento toEntity(DepartamentoDetailsExtendedDto dto);

    Departamento toEntity(DepartamentoWithArmazemDto dto);

    Departamento toEntity(DepartamentoWithFuncionarioDto dto);

    Departamento toEntity(DepartamentoWithCentroDto dto);

    Departamento toEntity(DepartamentoWithEquipaDto dto);

    Departamento toEntity(DepartamentoWithResponsavelDto dto);

    // -------- Auxiliar para Setor --------

    @Named("mapSetorToString")
    default String mapSetorToString(Integer setorId) {
        if (setorId == null) return null;
        return Setor.fromId(setorId).name();
    }

    @Named("mapStringToSetor")
    default Integer mapStringToSetor(String setorName) {
        if (setorName == null) return null;
        return Setor.fromName(setorName).getId();
    }

    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromExtendedDto(DepartamentoDetailsExtendedDto dto, @MappingTarget Departamento entity);
}
