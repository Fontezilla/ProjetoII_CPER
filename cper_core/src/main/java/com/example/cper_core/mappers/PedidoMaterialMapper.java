package com.example.cper_core.mappers;

import com.example.cper_core.enums.*;
import com.example.cper_core.dtos.pedido_material.*;
import com.example.cper_core.entities.PedidoMaterial;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PedidoMaterialMapper {

    // --- Métodos Auxiliares de Enum ---
    @Named("mapPrioridade")
    default Prioridade mapPrioridade(Integer id) {
        return id == null ? null : Prioridade.fromId(id);
    }

    @Named("mapEstadoPedidoMaterial")
    default EstadoPedidoMaterial mapEstadoPedidoMaterial(Integer id) {
        return id == null ? null : EstadoPedidoMaterial.fromId(id);
    }

    // --- To DTO ---
    @Named("toDto")
    PedidoMaterialDto toDto(PedidoMaterial entity);

    @IterableMapping(qualifiedByName = "toDto")
    List<PedidoMaterialDto> toDtoList(List<PedidoMaterial> entities);

    // --- To Details DTO ---
    @Named("toDetailsDto")
    @Mapping(target = "prioridade", source = "prioridade", qualifiedByName = "mapPrioridade")
    @Mapping(target = "estado", source = "estado", qualifiedByName = "mapEstadoPedidoMaterial")
    PedidoMaterialDetailsDto toDetailsDto(PedidoMaterial entity);

    @IterableMapping(qualifiedByName = "toDetailsDto")
    List<PedidoMaterialDetailsDto> toDetailsDtoList(List<PedidoMaterial> entities);

    // --- To Extended DTO ---
    @Named("toExtendedDto")
    @Mapping(target = "prioridade", source = "prioridade", qualifiedByName = "mapPrioridade")
    @Mapping(target = "estado", source = "estado", qualifiedByName = "mapEstadoPedidoMaterial")
    PedidoMaterialDetailsExtendedDto toExtendedDto(PedidoMaterial entity);

    @IterableMapping(qualifiedByName = "toExtendedDto")
    List<PedidoMaterialDetailsExtendedDto> toExtendedDtoList(List<PedidoMaterial> entities);

    // --- To WithRelationships DTO ---
    @Named("toWithRelationshipsDto")
    PedidoMaterialWithRelationshipsDto toWithRelationshipsDto(PedidoMaterial entity);

    @IterableMapping(qualifiedByName = "toWithRelationshipsDto")
    List<PedidoMaterialWithRelationshipsDto> toWithRelationshipsDtoList(List<PedidoMaterial> entities);

    // --- To Entity ---
    PedidoMaterial toEntity(PedidoMaterialDto dto);

    @Mapping(target = "prioridade", expression = "java(dto.getPrioridade() != null ? dto.getPrioridade().getId() : null)")
    @Mapping(target = "estado", expression = "java(dto.getEstado() != null ? dto.getEstado().getId() : null)")
    PedidoMaterial toEntity(PedidoMaterialDetailsDto dto);

    @Mapping(target = "prioridade", expression = "java(dto.getPrioridade() != null ? dto.getPrioridade().getId() : null)")
    @Mapping(target = "estado", expression = "java(dto.getEstado() != null ? dto.getEstado().getId() : null)")
    PedidoMaterial toEntity(PedidoMaterialDetailsExtendedDto dto);

    PedidoMaterial toEntity(PedidoMaterialWithRelationshipsDto dto);

    // --- Partial Update ---
    @Mapping(target = "prioridade", expression = "java(dto.getPrioridade() != null ? dto.getPrioridade().getId() : entity.getPrioridade())")
    @Mapping(target = "estado", expression = "java(dto.getEstado() != null ? dto.getEstado().getId() : entity.getEstado())")
    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromExtendedDto(PedidoMaterialDetailsExtendedDto dto, @MappingTarget PedidoMaterial entity);
}
