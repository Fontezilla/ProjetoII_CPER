package com.example.cper_core.mappers;

import com.example.cper_core.dtos.anomalia.AnomaliaDetailsExtendedDto;
import com.example.cper_core.dtos.inspecao.*;
import com.example.cper_core.entities.Anomalia;
import com.example.cper_core.entities.Inspecao;
import com.example.cper_core.enums.EstadoInspecao;
import com.example.cper_core.enums.TipoInspecao;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface InspecaoMapper {

    // -------- To DTO --------

    @Named("toDto")
    InspecaoDto toDto(Inspecao entity);

    @IterableMapping(qualifiedByName = "toDto")
    List<InspecaoDto> toDtoList(List<Inspecao> entities);

    @Named("toDetailsDto")
    @Mapping(source = "tipo", target = "tipo", qualifiedByName = "mapTipoToString")
    @Mapping(source = "estado", target = "estado", qualifiedByName = "mapEstadoToString")
    InspecaoDetailsDto toDetailsDto(Inspecao entity);

    @IterableMapping(qualifiedByName = "toDetailsDto")
    List<InspecaoDetailsDto> toDetailsDtoList(List<Inspecao> entities);

    @Named("toWithDetailsExtendedDto")
    InspecaoDetailsExtendedDto toWithDetailsExtendedDto(Inspecao entity);

    @IterableMapping(qualifiedByName = "toWithDetailsExtendedDto")
    List<InspecaoDetailsExtendedDto> toWithDetailsExtendedDtoList(List<Inspecao> entities);

    @Named("toWithAvariaDto")
    InspecaoWithAvariaDto toWithAvariaDto(Inspecao entity);

    @IterableMapping(qualifiedByName = "toWithAvariaDto")
    List<InspecaoWithAvariaDto> toWithAvariaDtoList(List<Inspecao> entities);

    @Named("toWithEquipaDto")
    InspecaoWithEquipaDto toWithEquipaDto(Inspecao entity);

    @IterableMapping(qualifiedByName = "toWithEquipaDto")
    List<InspecaoWithEquipaDto> toWithEquipaDtoList(List<Inspecao> entities);

    @Named("toWithNotasDto")
    InspecaoWithNotasDto toWithNotasDto(Inspecao entity);

    @IterableMapping(qualifiedByName = "toWithNotasDto")
    List<InspecaoWithNotasDto> toWithNotasDtoList(List<Inspecao> entities);

    // -------- To Entity --------

    Inspecao toEntity(InspecaoDto dto);

    @Mapping(source = "tipo", target = "tipo", qualifiedByName = "mapStringToTipo")
    @Mapping(source = "estado", target = "estado", qualifiedByName = "mapStringToEstado")
    Inspecao toEntity(InspecaoDetailsDto dto);

    Inspecao toEntity(InspecaoDetailsExtendedDto dto);

    Inspecao toEntity(InspecaoWithAvariaDto dto);

    Inspecao toEntity(InspecaoWithEquipaDto dto);

    Inspecao toEntity(InspecaoWithNotasDto dto);

    // -------- Métodos auxiliares --------

    @Named("mapTipoToString")
    default String mapTipoToString(Integer tipoId) {
        if (tipoId == null) return null;
        return TipoInspecao.fromId(tipoId).name();
    }

    @Named("mapStringToTipo")
    default Integer mapStringToTipo(String tipoName) {
        if (tipoName == null) return null;
        return TipoInspecao.fromName(tipoName).getId();
    }

    @Named("mapEstadoToString")
    default String mapEstadoToString(Integer estadoId) {
        if (estadoId == null) return null;
        return EstadoInspecao.fromId(estadoId).name();
    }

    @Named("mapStringToEstado")
    default Integer mapStringToEstado(String estadoName) {
        if (estadoName == null) return null;
        return EstadoInspecao.fromName(estadoName).getId();
    }

    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromExtendedDto(InspecaoDetailsExtendedDto dto, @MappingTarget Inspecao entity);
}