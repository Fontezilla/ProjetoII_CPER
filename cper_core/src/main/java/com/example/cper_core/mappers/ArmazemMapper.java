package com.example.cper_core.mappers;

import com.example.cper_core.dtos.anomalia.AnomaliaDetailsExtendedDto;
import com.example.cper_core.dtos.armazem.*;
import com.example.cper_core.entities.Anomalia;
import com.example.cper_core.entities.Armazem;
import com.example.cper_core.enums.EstadoArmazem;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ArmazemMapper {

    // -------- To DTO --------

    @Named("toDto")
    ArmazemDto toDto(Armazem entity);

    List<ArmazemDto> toDtoList(List<Armazem> entities);

    @Named("toDetailsDto")
    @Mapping(source = "estado", target = "estado", qualifiedByName = "mapEstadoToString")
    ArmazemDetailsDto toDetailsDto(Armazem entity);

    List<ArmazemDetailsDto> toDetailsDtoList(List<Armazem> entities);

    @Named("toDetailsExtendedDto")
    ArmazemDetailsExtendedDto toDetailsExtendedDto(Armazem entity);

    List<ArmazemDetailsExtendedDto> toDetailsExtendedDtoList(List<Armazem> entities);

    @Named("toWithFuncionarioDto")
    ArmazemWithFuncionarioDto toWithFuncionarioDto(Armazem entity);

    List<ArmazemWithFuncionarioDto> toWithFuncionarioDtoList(List<Armazem> entities);

    @Named("toWithStockDto")
    ArmazemWithStockDto toWithStockDto(Armazem entity);

    List<ArmazemWithStockDto> toWithStockDtoList(List<Armazem> entities);

    // -------- To Entity --------

    Armazem toEntity(ArmazemDto dto);

    @Mapping(source = "estado", target = "estado", qualifiedByName = "mapStringToEstado")
    Armazem toEntity(ArmazemDetailsDto dto);

    Armazem toEntity(ArmazemDetailsExtendedDto dto);

    Armazem toEntity(ArmazemWithFuncionarioDto dto);

    Armazem toEntity(ArmazemWithStockDto dto);

    // -------- Auxiliar para EstadoArmazem --------

    @Named("mapEstadoToString")
    default String mapEstadoToString(Integer estadoId) {
        if (estadoId == null) return null;
        return EstadoArmazem.fromId(estadoId).name();
    }

    @Named("mapStringToEstado")
    default Integer mapStringToEstado(String estadoName) {
        if (estadoName == null) return null;
        return EstadoArmazem.fromName(estadoName).getId();
    }

    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromExtendedDto(ArmazemDetailsExtendedDto dto, @MappingTarget Armazem entity);
}
