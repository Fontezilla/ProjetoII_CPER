package com.example.cper_core.mappers;

import com.example.cper_core.enums.*;
import com.example.cper_core.dtos.material.*;
import com.example.cper_core.entities.Material;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MaterialMapper {

    // --- Métodos Auxiliares de Enum ---
    @Named("mapUnidadePeso")
    default UnidadePeso mapUnidadePeso(Integer id) {
        return id == null ? null : UnidadePeso.fromId(id);
    }

    @Named("mapUnidadeVolume")
    default UnidadeVolume mapUnidadeVolume(Integer id) {
        return id == null ? null : UnidadeVolume.fromId(id);
    }

    @Named("toDto")
    MaterialDto toDto(Material entity);

    @IterableMapping(qualifiedByName = "toDto")
    List<MaterialDto> toDtoList(List<Material> entities);

    @Named("toDetailsDto")
    MaterialDetailsDto toDetailsDto(Material entity);

    @IterableMapping(qualifiedByName = "toDetailsDto")
    List<MaterialDetailsDto> toDetailsDtoList(List<Material> entities);

    @Named("toExtendedDto")
    @Mapping(target = "uniMedidaPeso", source = "uniMedidaPeso", qualifiedByName = "mapUnidadePeso")
    @Mapping(target = "uniMedidaVolume", source = "uniMedidaVolume", qualifiedByName = "mapUnidadeVolume")
    MaterialDetailsExtendedDto toExtendedDto(Material entity);

    @IterableMapping(qualifiedByName = "toExtendedDto")
    List<MaterialDetailsExtendedDto> toExtendedDtoList(List<Material> entities);

    @Named("toWithRelationshipsDto")
    MaterialWithRelationshipsDto toWithRelationshipsDto(Material entity);

    @IterableMapping(qualifiedByName = "toWithRelationshipsDto")
    List<MaterialWithRelationshipsDto> toWithRelationshipsDtoList(List<Material> entities);

    Material toEntity(MaterialDto dto);

    Material toEntity(MaterialDetailsDto dto);

    @Mapping(target = "uniMedidaPeso", expression = "java(dto.getUniMedidaPeso() != null ? dto.getUniMedidaPeso().getId() : null)")
    @Mapping(target = "uniMedidaVolume", expression = "java(dto.getUniMedidaVolume() != null ? dto.getUniMedidaVolume().getId() : null)")
    Material toEntity(MaterialDetailsExtendedDto dto);

    Material toEntity(MaterialWithRelationshipsDto dto);

    @Mapping(target = "uniMedidaPeso", expression = "java(dto.getUniMedidaPeso() != null ? dto.getUniMedidaPeso().getId() : entity.getUniMedidaPeso())")
    @Mapping(target = "uniMedidaVolume", expression = "java(dto.getUniMedidaVolume() != null ? dto.getUniMedidaVolume().getId() : entity.getUniMedidaVolume())")
    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromExtendedDto(MaterialDetailsExtendedDto dto, @MappingTarget Material entity);
}