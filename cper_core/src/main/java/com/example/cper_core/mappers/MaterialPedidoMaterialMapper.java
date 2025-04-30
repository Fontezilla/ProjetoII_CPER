package com.example.cper_core.mappers;

import com.example.cper_core.dtos.anomalia.AnomaliaDetailsExtendedDto;
import com.example.cper_core.dtos.material_pedido_material.*;
import com.example.cper_core.entities.Anomalia;
import com.example.cper_core.entities.MaterialPedidoMaterial;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MaterialPedidoMaterialMapper {

    // -------- To DTO --------

    @Named("toDto")
    MaterialPedidoMaterialDto toDto(MaterialPedidoMaterial entity);

    @IterableMapping(qualifiedByName = "toDto")
    List<MaterialPedidoMaterialDto> toDtoList(List<MaterialPedidoMaterial> entities);

    // -------- To Entity --------

    @Named("toEntity")
    MaterialPedidoMaterial toEntity(MaterialPedidoMaterialDto dto);

    @IterableMapping(qualifiedByName = "toEntity")
    List<MaterialPedidoMaterial> toEntityList(List<MaterialPedidoMaterialDto> dtos);

    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromExtendedDto(MaterialPedidoMaterialDto dto, @MappingTarget MaterialPedidoMaterial entity);
}
