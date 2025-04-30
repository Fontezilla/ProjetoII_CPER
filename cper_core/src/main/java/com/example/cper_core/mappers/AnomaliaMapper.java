package com.example.cper_core.mappers;

import com.example.cper_core.dtos.anomalia.*;
import com.example.cper_core.entities.Anomalia;
import com.example.cper_core.enums.EstadoAnomalia;
import com.example.cper_core.enums.Prioridade;
import com.example.cper_core.enums.TipoAnomalia;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AnomaliaMapper {

    // -------- To DTO --------

    @Named("toDto")
    AnomaliaDto toDto(Anomalia entity);

    @IterableMapping(qualifiedByName = "toDto")
    List<AnomaliaDto> toDtoList(List<Anomalia> entities);

    @Named("toDetailsDto")
    @Mapping(source = "tipoAnomalia", target = "tipoAnomalia", qualifiedByName = "mapTipoToString")
    @Mapping(source = "gravidade", target = "gravidade", qualifiedByName = "mapGravidadeToString")
    @Mapping(source = "estado", target = "estado", qualifiedByName = "mapEstadoToString")
    AnomaliaDetailsDto toDetailsDto(Anomalia entity);

    @IterableMapping(qualifiedByName = "toDetailsDto")
    List<AnomaliaDetailsDto> toDetailsDtoList(List<Anomalia> entities);

    @Named("toExtendedDto")
    AnomaliaDetailsExtendedDto toExtendedDto(Anomalia entity);

    @IterableMapping(qualifiedByName = "toExtendedDto")
    List<AnomaliaDetailsExtendedDto> toExtendedDtoList(List<Anomalia> entities);

    @Named("toWithNotasDto")
    AnomaliaWithNotasDto toWithNotasDto(Anomalia entity);

    @IterableMapping(qualifiedByName = "toWithNotasDto")
    List<AnomaliaWithNotasDto> toWithNotasDtoList(List<Anomalia> entities);

    // -------- To Entity --------

    Anomalia toEntity(AnomaliaDto dto);

    @Mapping(source = "tipoAnomalia", target = "tipoAnomalia", qualifiedByName = "mapStringToTipo")
    @Mapping(source = "gravidade", target = "gravidade", qualifiedByName = "mapStringToGravidade")
    @Mapping(source = "estado", target = "estado", qualifiedByName = "mapStringToEstado")
    Anomalia toEntity(AnomaliaDetailsDto dto);

    Anomalia toEntity(AnomaliaDetailsExtendedDto dto);

    Anomalia toEntity(AnomaliaWithNotasDto dto);

    // -------- Métodos auxiliares --------

    @Named("mapTipoToString")
    default String mapTipoToString(Integer tipoId) {
        if (tipoId == null) return null;
        return TipoAnomalia.fromId(tipoId).name();
    }

    @Named("mapStringToTipo")
    default Integer mapStringToTipo(String tipoName) {
        if (tipoName == null) return null;
        return TipoAnomalia.fromName(tipoName).getId();
    }

    @Named("mapEstadoToString")
    default String mapEstadoToString(Integer estadoId) {
        if (estadoId == null) return null;
        return EstadoAnomalia.fromId(estadoId).name();
    }

    @Named("mapStringToEstado")
    default Integer mapStringToEstado(String estadoName) {
        if (estadoName == null) return null;
        return EstadoAnomalia.fromName(estadoName).getId();
    }

    @Named("mapGravidadeToString")
    default String mapGravidadeToString(Integer gravidadeId) {
        if (gravidadeId == null) return null;
        return Prioridade.fromId(gravidadeId).name();
    }

    @Named("mapStringToGravidade")
    default Integer mapStringToGravidade(String gravidadeName) {
        if (gravidadeName == null) return null;
        return Prioridade.fromName(gravidadeName).getId();
    }

    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromExtendedDto(AnomaliaDetailsExtendedDto dto, @MappingTarget Anomalia entity);

}