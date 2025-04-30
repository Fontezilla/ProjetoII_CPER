package com.example.cper_core.mappers;

import com.example.cper_core.dtos.perfil_consumo.*;
import com.example.cper_core.entities.PerfilConsumo;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PerfilConsumoMapper {

    // -------- To DTO --------

    @Named("toDto")
    PerfilConsumoDto toDto(PerfilConsumo entity);

    @IterableMapping(qualifiedByName = "toDto")
    List<PerfilConsumoDto> toDtoList(List<PerfilConsumo> entities);

    @Named("toDetailsDto")
    PerfilConsumoDetailsDto toDetailsDto(PerfilConsumo entity);

    @IterableMapping(qualifiedByName = "toDetailsDto")
    List<PerfilConsumoDetailsDto> toDetailsDtoList(List<PerfilConsumo> entities);

    // -------- To Entity --------

    @Named("toEntityFromDto")
    PerfilConsumo toEntity(PerfilConsumoDto dto);

    @Named("toEntityFromDetails")
    PerfilConsumo toEntity(PerfilConsumoDetailsDto dto);

    // -------- Conversões de listas inversas --------

    @IterableMapping(qualifiedByName = "toEntityFromDto")
    List<PerfilConsumo> toEntityList(List<PerfilConsumoDto> dtos);

    @IterableMapping(qualifiedByName = "toEntityFromDetails")
    List<PerfilConsumo> toEntityDetailsList(List<PerfilConsumoDetailsDto> dtos);

    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromExtendedDto(PerfilConsumoDetailsDto dto, @MappingTarget PerfilConsumo entity);
}

