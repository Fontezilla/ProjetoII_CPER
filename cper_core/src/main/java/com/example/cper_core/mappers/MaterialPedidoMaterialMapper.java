package com.example.cper_core.mappers;

import com.example.cper_core.enums.*;
import com.example.cper_core.dtos.material_pedido_material.MaterialPedidoMaterialDetailsDto;
import com.example.cper_core.dtos.material_pedido_material.MaterialPedidoMaterialDto;
import com.example.cper_core.entities.MaterialPedidoMaterial;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MaterialPedidoMaterialMapper {

    // --- To DTO ---
    @Named("toDto")
    MaterialPedidoMaterialDto toDto(MaterialPedidoMaterial entity);

    @IterableMapping(qualifiedByName = "toDto")
    List<MaterialPedidoMaterialDto> toDtoList(List<MaterialPedidoMaterial> entities);

    // --- To Details DTO ---
    @Named("toDetailsDto")
    @Mapping(source = "qtd", target = "qtd")
    MaterialPedidoMaterialDetailsDto toDetailsDto(MaterialPedidoMaterial entity);

    @IterableMapping(qualifiedByName = "toDetailsDto")
    List<MaterialPedidoMaterialDetailsDto> toDetailsDtoList(List<MaterialPedidoMaterial> entities);

    // --- To Entity ---
    @Mapping(target = "pedidoMaterial", ignore = true)
    @Mapping(target = "material", ignore = true)
    MaterialPedidoMaterial toEntity(MaterialPedidoMaterialDto dto);
}