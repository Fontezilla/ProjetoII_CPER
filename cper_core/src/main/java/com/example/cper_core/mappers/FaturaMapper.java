package com.example.cper_core.mappers;

import com.example.cper_core.dtos.anomalia.AnomaliaDetailsExtendedDto;
import com.example.cper_core.dtos.fatura.*;
import com.example.cper_core.entities.Anomalia;
import com.example.cper_core.entities.Fatura;
import com.example.cper_core.enums.EstadoFatura;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FaturaMapper {

    // -------- To DTO --------

    @Named("toDto")
    FaturaDto toDto(Fatura entity);

    @IterableMapping(qualifiedByName = "toDto")
    List<FaturaDto> toDtoList(List<Fatura> entities);

    @Named("toDetailsDto")
    @Mapping(source = "estado", target = "estado", qualifiedByName = "mapEstadoToString")
    FaturaDetailsDto toDetailsDto(Fatura entity);

    @IterableMapping(qualifiedByName = "toDetailsDto")
    List<FaturaDetailsDto> toDetailsDtoList(List<Fatura> entities);

    @Named("toDetailsExtendedDto")
    FaturaDetailsExtendedDto toDetailsExtendedDto(Fatura entity);

    @IterableMapping(qualifiedByName = "toDetailsExtendedDto")
    List<FaturaDetailsExtendedDto> toDetailsExtendedDtoList(List<Fatura> entities);

    // -------- To Entity --------

    Fatura toEntity(FaturaDto dto);

    @Mapping(source = "estado", target = "estado", qualifiedByName = "mapStringToEstado")
    Fatura toEntity(FaturaDetailsDto dto);

    Fatura toEntity(FaturaDetailsExtendedDto dto);

    // -------- Métodos auxiliares --------

    @Named("mapEstadoToString")
    default String mapEstadoToString(Integer estadoId) {
        if (estadoId == null) return null;
        return EstadoFatura.fromId(estadoId).name();
    }

    @Named("mapStringToEstado")
    default Integer mapStringToEstado(String estadoName) {
        if (estadoName == null) return null;
        return EstadoFatura.fromName(estadoName).getId();
    }

    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromExtendedDto(FaturaDetailsExtendedDto dto, @MappingTarget Fatura entity);
}
