package com.example.cper_core.mappers;

import com.example.cper_core.dtos.anomalia.AnomaliaDetailsExtendedDto;
import com.example.cper_core.dtos.contrato.*;
import com.example.cper_core.entities.Anomalia;
import com.example.cper_core.entities.Contrato;
import com.example.cper_core.enums.*;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ContratoMapper {

    // -------- To DTO --------

    @Named("toDto")
    ContratoDto toDto(Contrato entity);

    @IterableMapping(qualifiedByName = "toDto")
    List<ContratoDto> toDtoList(List<Contrato> entities);

    @Named("toDetailsDto")
    @Mapping(source = "tipoContrato", target = "tipoContrato", qualifiedByName = "mapTipoToString")
    @Mapping(source = "estado", target = "estado", qualifiedByName = "mapEstadoToString")
    ContratoDetailsDto toDetailsDto(Contrato entity);

    @IterableMapping(qualifiedByName = "toDetailsDto")
    List<ContratoDetailsDto> toDetailsDtoList(List<Contrato> entities);

    @Named("toDetailsExtendedDto")
    ContratoDetailsExtendedDto toDetailsExtendedDto(Contrato entity);

    @IterableMapping(qualifiedByName = "toDetailsExtendedDto")
    List<ContratoDetailsExtendedDto> toDetailsExtendedDtoList(List<Contrato> entities);

    @Named("toWithFaturaDto")
    ContratoWithFaturaDto toWithFaturaDto(Contrato entity);

    @IterableMapping(qualifiedByName = "toWithFaturaDto")
    List<ContratoWithFaturaDto> toWithFaturaDtoList(List<Contrato> entities);

    @Named("toWithPedidoDto")
    ContratoWithPedidoDto toWithPedidoDto(Contrato entity);

    @IterableMapping(qualifiedByName = "toWithPedidoDto")
    List<ContratoWithPedidoDto> toWithPedidoDtoList(List<Contrato> entities);

    // -------- To Entity --------

    @Named("toEntityFromDto")
    Contrato toEntity(ContratoDto dto);

    @Named("toEntityFromDetails")
    @Mapping(source = "tipoContrato", target = "tipoContrato", qualifiedByName = "mapStringToTipo")
    @Mapping(source = "estado", target = "estado", qualifiedByName = "mapStringToEstado")
    Contrato toEntity(ContratoDetailsDto dto);

    @Named("toEntityFromExtended")
    Contrato toEntity(ContratoDetailsExtendedDto dto);

    @Named("toEntityFromFatura")
    Contrato toEntity(ContratoWithFaturaDto dto);

    @Named("toEntityFromPedido")
    Contrato toEntity(ContratoWithPedidoDto dto);

    // -------- Conversões de listas inversas --------

    @IterableMapping(qualifiedByName = "toEntityFromDto")
    List<Contrato> toEntityList(List<ContratoDto> dtos);

    @IterableMapping(qualifiedByName = "toEntityFromDetails")
    List<Contrato> toEntityDetailsList(List<ContratoDetailsDto> dtos);

    @IterableMapping(qualifiedByName = "toEntityFromExtended")
    List<Contrato> toEntityExtendedList(List<ContratoDetailsExtendedDto> dtos);

    @IterableMapping(qualifiedByName = "toEntityFromFatura")
    List<Contrato> toEntityWithFaturaList(List<ContratoWithFaturaDto> dtos);

    @IterableMapping(qualifiedByName = "toEntityFromPedido")
    List<Contrato> toEntityWithPedidoList(List<ContratoWithPedidoDto> dtos);

    // -------- Métodos auxiliares --------

    @Named("mapTipoToString")
    default String mapTipoToString(Integer TipoContratoId) {
        if (TipoContratoId == null) return null;
        return TipoContrato.fromId(TipoContratoId).name();
    }

    @Named("mapStringToTipo")
    default Integer mapStringToTipo(String TipoContratoName) {
        if (TipoContratoName == null) return null;
        return TipoContrato.fromName(TipoContratoName).getId();
    }

    @Named("mapEstadoToString")
    default String mapEstadoToString(Integer estadoId) {
        if (estadoId == null) return null;
        return EstadoContrato.fromId(estadoId).name();
    }

    @Named("mapStringToEstado")
    default Integer mapStringToEstado(String estadoName) {
        if (estadoName == null) return null;
        return EstadoContrato.fromName(estadoName).getId();
    }

    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromExtendedDto(ContratoDetailsExtendedDto dto, @MappingTarget Contrato entity);
}
