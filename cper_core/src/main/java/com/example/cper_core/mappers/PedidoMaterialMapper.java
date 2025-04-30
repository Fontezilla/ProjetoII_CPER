package com.example.cper_core.mappers;

import com.example.cper_core.dtos.pedido_material.*;
import com.example.cper_core.entities.PedidoMaterial;
import com.example.cper_core.enums.EstadoPedidoMaterial;
import com.example.cper_core.enums.Prioridade;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PedidoMaterialMapper {

    // -------- To DTO --------

    @Named("toDto")
    PedidoMaterialDto toDto(PedidoMaterial entity);

    @IterableMapping(qualifiedByName = "toDto")
    List<PedidoMaterialDto> toDtoList(List<PedidoMaterial> entities);

    @Named("toDetailsDto")
    @Mapping(source = "prioridade", target = "prioridade", qualifiedByName = "mapPrioridadeToString")
    @Mapping(source = "estado", target = "estado", qualifiedByName = "mapEstadoToString")
    PedidoMaterialDetailsDto toDetailsDto(PedidoMaterial entity);

    @IterableMapping(qualifiedByName = "toDetailsDto")
    List<PedidoMaterialDetailsDto> toDetailsDtoList(List<PedidoMaterial> entities);

    @Named("toWithAvariaDto")
    PedidoMaterialWithAvariaDto toWithAvariaDto(PedidoMaterial entity);

    @IterableMapping(qualifiedByName = "toWithAvariaDto")
    List<PedidoMaterialWithAvariaDto> toWithAvariaDtoList(List<PedidoMaterial> entities);

    @Named("toWithPedidoDto")
    PedidoMaterialWithPedidoMaterialDto toWithPedidoDto(PedidoMaterial entity);

    @IterableMapping(qualifiedByName = "toWithPedidoDto")
    List<PedidoMaterialWithPedidoMaterialDto> toWithPedidoDtoList(List<PedidoMaterial> entities);

    // -------- To Entity --------

    @Named("toEntityFromDto")
    PedidoMaterial toEntity(PedidoMaterialDto dto);

    @Named("toEntityFromDetails")
    @Mapping(source = "prioridade", target = "prioridade", qualifiedByName = "mapStringToPrioridade")
    @Mapping(source = "estado", target = "estado", qualifiedByName = "mapStringToEstado")
    PedidoMaterial toEntity(PedidoMaterialDetailsDto dto);

    @Named("toEntityFromAvaria")
    PedidoMaterial toEntity(PedidoMaterialWithAvariaDto dto);

    @Named("toEntityFromPedido")
    PedidoMaterial toEntity(PedidoMaterialWithPedidoMaterialDto dto);

    // -------- Conversões de listas inversas --------

    @IterableMapping(qualifiedByName = "toEntityFromDto")
    List<PedidoMaterial> toEntityList(List<PedidoMaterialDto> dtos);

    @IterableMapping(qualifiedByName = "toEntityFromDetails")
    List<PedidoMaterial> toEntityDetailsList(List<PedidoMaterialDetailsDto> dtos);

    @IterableMapping(qualifiedByName = "toEntityFromAvaria")
    List<PedidoMaterial> toEntityWithAvariaList(List<PedidoMaterialWithAvariaDto> dtos);

    @IterableMapping(qualifiedByName = "toEntityFromPedido")
    List<PedidoMaterial> toEntityWithPedidoList(List<PedidoMaterialWithPedidoMaterialDto> dtos);

    // -------- Métodos auxiliares --------

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
        return EstadoPedidoMaterial.fromId(estadoId).name();
    }

    @Named("mapStringToEstado")
    default Integer mapStringToEstado(String estadoName) {
        if (estadoName == null) return null;
        return EstadoPedidoMaterial.fromName(estadoName).getId();
    }

    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromExtendedDto(PedidoMaterialDetailsDto dto, @MappingTarget PedidoMaterial entity);
}
