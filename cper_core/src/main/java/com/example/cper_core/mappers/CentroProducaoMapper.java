package com.example.cper_core.mappers;

import com.example.cper_core.dtos.anomalia.AnomaliaDetailsExtendedDto;
import com.example.cper_core.dtos.centro_producao.*;
import com.example.cper_core.entities.Anomalia;
import com.example.cper_core.entities.CentroProducao;
import com.example.cper_core.enums.EstadoCentro;
import com.example.cper_core.enums.TipoEnergiaRenovavel;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CentroProducaoMapper {

    // -------- To DTO --------

    @Named("toDto")
    CentroProducaoDto toDto(CentroProducao entity);

    @IterableMapping(qualifiedByName = "toDto")
    List<CentroProducaoDto> toDtoList(List<CentroProducao> entities);

    @Named("toDetailsDto")
    @Mapping(source = "tipoEnergia", target = "tipoEnergia", qualifiedByName = "mapTipoToString")
    @Mapping(source = "estado", target = "estado", qualifiedByName = "mapEstadoToString")
    CentroProducaoDetailsDto toDetailsDto(CentroProducao entity);

    @IterableMapping(qualifiedByName = "toDetailsDto")
    List<CentroProducaoDetailsDto> toDetailsDtoList(List<CentroProducao> entities);

    @Named("toDetailsExtendedDto")
    CentroProducaoDetailsExtendedDto toDetailsExtendedDto(CentroProducao entity);

    @IterableMapping(qualifiedByName = "toDetailsExtendedDto")
    List<CentroProducaoDetailsExtendedDto> toDetailsExtendedDtoList(List<CentroProducao> entities);

    @Named("toWithFuncionarioDto")
    CentroProducaoWithFuncionarioDto toWithFuncionarioDto(CentroProducao entity);

    @IterableMapping(qualifiedByName = "toWithFuncionarioDto")
    List<CentroProducaoWithFuncionarioDto> toWithFuncionarioDtoList(List<CentroProducao> entities);

    @Named("toWithAnomaliaDto")
    CentroProducaoWithAnomaliaDto toWithAnomaliaDto(CentroProducao entity);

    @IterableMapping(qualifiedByName = "toWithAnomaliaDto")
    List<CentroProducaoWithAnomaliaDto> toWithAnomaliaDtoList(List<CentroProducao> entities);

    @Named("toWithAvariaDto")
    CentroProducaoWithAvariaDto toWithAvariaDto(CentroProducao entity);

    @IterableMapping(qualifiedByName = "toWithAvariaDto")
    List<CentroProducaoWithAvariaDto> toWithAvariaDtoList(List<CentroProducao> entities);

    @Named("toWithInspecaoDto")
    CentroProducaoWithInspecaoDto toWithInspecaoDto(CentroProducao entity);

    @IterableMapping(qualifiedByName = "toWithInspecaoDto")
    List<CentroProducaoWithInspecaoDto> toWithInspecaoDtoList(List<CentroProducao> entities);

    @Named("toWithPedidoDto")
    CentroProducaoWithPedidoGeracaoDto toWithPedidoDto(CentroProducao entity);

    @IterableMapping(qualifiedByName = "toWithPedidoDto")
    List<CentroProducaoWithPedidoGeracaoDto> toWithPedidoDtoList(List<CentroProducao> entities);

    // -------- To Entity --------

    CentroProducao toEntity(CentroProducaoDto dto);

    @Mapping(source = "tipoEnergia", target = "tipoEnergia", qualifiedByName = "mapStringToTipo")
    @Mapping(source = "estado", target = "estado", qualifiedByName = "mapStringToEstado")
    CentroProducao toEntity(CentroProducaoDetailsDto dto);

    CentroProducao toEntity(CentroProducaoDetailsExtendedDto dto);

    CentroProducao toEntity(CentroProducaoWithFuncionarioDto dto);

    CentroProducao toEntity(CentroProducaoWithAnomaliaDto dto);

    CentroProducao toEntity(CentroProducaoWithAvariaDto dto);

    CentroProducao toEntity(CentroProducaoWithInspecaoDto dto);

    CentroProducao toEntity(CentroProducaoWithPedidoGeracaoDto dto);

    // -------- Métodos auxiliares --------

    @Named("mapTipoToString")
    default String mapTipoToString(Integer tipoEnergiaId) {
        if (tipoEnergiaId == null) return null;
        return TipoEnergiaRenovavel.fromId(tipoEnergiaId).name();
    }

    @Named("mapStringToTipo")
    default Integer mapStringToTipo(String tipoEnergiaName) {
        if (tipoEnergiaName == null) return null;
        return TipoEnergiaRenovavel.fromName(tipoEnergiaName).getId();
    }

    @Named("mapEstadoToString")
    default String mapEstadoToString(Integer estadoId) {
        if (estadoId == null) return null;
        return EstadoCentro.fromId(estadoId).name();
    }

    @Named("mapStringToEstado")
    default Integer mapStringToEstado(String estadoName) {
        if (estadoName == null) return null;
        return EstadoCentro.fromName(estadoName).getId();
    }

    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromExtendedDto(CentroProducaoDetailsExtendedDto dto, @MappingTarget CentroProducao entity);
}