package com.example.cper_core.mappers;

import com.example.cper_core.dtos.pedido_geracao.*;
import com.example.cper_core.entities.PedidoGeracao;
import com.example.cper_core.enums.EstadoPedidoGeracao;
import com.example.cper_core.enums.Prioridade;
import com.example.cper_core.enums.TipoEnergiaRenovavel;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PedidoGeracaoMapper {

    // -------- To DTO --------

    @Named("toDto")
    PedidoGeracaoDto toDto(PedidoGeracao entity);

    @IterableMapping(qualifiedByName = "toDto")
    List<PedidoGeracaoDto> toDtoList(List<PedidoGeracao> entities);

    @Named("toDetailsDto")
    @Mapping(source = "tipoEnergia", target = "tipoEnergia", qualifiedByName = "mapTipoToString")
    @Mapping(source = "prioridade", target = "prioridade", qualifiedByName = "mapPrioridadeToString")
    @Mapping(source = "estado", target = "estado", qualifiedByName = "mapEstadoToString")
    PedidoGeracaoDetailsDto toDetailsDto(PedidoGeracao entity);

    @IterableMapping(qualifiedByName = "toDetailsDto")
    List<PedidoGeracaoDetailsDto> toDetailsDtoList(List<PedidoGeracao> entities);

    @Named("toDetailsExtendedDto")
    PedidoGeracaoDetailsExtendedDto toDetailsExtendedDto(PedidoGeracao entity);

    @IterableMapping(qualifiedByName = "toDetailsExtendedDto")
    List<PedidoGeracaoDetailsExtendedDto> toDetailsExtendedDtoList(List<PedidoGeracao> entities);

    // -------- To Entity --------

    PedidoGeracao toEntity(PedidoGeracaoDto dto);

    @Mapping(source = "tipoEnergia", target = "tipoEnergia", qualifiedByName = "mapStringToTipo")
    @Mapping(source = "prioridade", target = "prioridade", qualifiedByName = "mapStringToPrioridade")
    @Mapping(source = "estado", target = "estado", qualifiedByName = "mapStringToEstado")
    PedidoGeracao toEntity(PedidoGeracaoDetailsDto dto);

    PedidoGeracao toEntity(PedidoGeracaoDetailsExtendedDto dto);

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

    @Named("mapPrioridadeToString")
    default String mapPrioridadeToString(Integer prioridadeId) {
        if (prioridadeId == null) return null;
        return Prioridade.fromId(prioridadeId).name();
    }

    @Named("mapStringToPrioridade")
    default Integer mapStringToPrioridade(String prioridadeName) {
        if (prioridadeName == null) return null;
        return Prioridade.fromName(prioridadeName).getId();
    }

    @Named("mapEstadoToString")
    default String mapEstadoToString(Integer estadoId) {
        if (estadoId == null) return null;
        return EstadoPedidoGeracao.fromId(estadoId).name();
    }

    @Named("mapStringToEstado")
    default Integer mapStringToEstado(String estadoName) {
        if (estadoName == null) return null;
        return EstadoPedidoGeracao.fromName(estadoName).getId();
    }

    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromExtendedDto(PedidoGeracaoDetailsExtendedDto dto, @MappingTarget PedidoGeracao entity);
}