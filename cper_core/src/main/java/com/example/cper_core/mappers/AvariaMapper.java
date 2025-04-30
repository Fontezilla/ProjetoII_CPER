package com.example.cper_core.mappers;

import com.example.cper_core.dtos.anomalia.AnomaliaDetailsExtendedDto;
import com.example.cper_core.dtos.avaria.*;
import com.example.cper_core.entities.Anomalia;
import com.example.cper_core.entities.Avaria;
import com.example.cper_core.enums.Prioridade;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AvariaMapper {

    // -------- To DTO --------

    @Named("toDto")
    AvariaDto toDto(Avaria entity);

    @IterableMapping(qualifiedByName = "toDto")
    List<AvariaDto> toDtoList(List<Avaria> entities);

    @Named("toDetailsDto")
    @Mapping(source = "gravidade", target = "gravidade", qualifiedByName = "mapGravidadeToString")
    AvariaDetailsDto toDetailsDto(Avaria entity);

    @IterableMapping(qualifiedByName = "toDetailsDto")
    List<AvariaDetailsDto> toDetailsDtoList(List<Avaria> entities);

    @Named("toDetailsExtendedDto")
    AvariaDetailsExtendedDto toDetailsExtendedDto(Avaria entity);

    @IterableMapping(qualifiedByName = "toDetailsExtendedDto")
    List<AvariaDetailsExtendedDto> toDetailsExtendedDtoList(List<Avaria> entities);

    @Named("toWithEquipaDto")
    AvariaWithEquipaDto toWithEquipaDto(Avaria entity);

    @IterableMapping(qualifiedByName = "toWithEquipaDto")
    List<AvariaWithEquipaDto> toWithEquipaDtoList(List<Avaria> entities);

    @Named("toWithNotasDto")
    AvariaWithNotasDto toWithNotasDto(Avaria entity);

    @IterableMapping(qualifiedByName = "toWithNotasDto")
    List<AvariaWithNotasDto> toWithNotasDtoList(List<Avaria> entities);

    @Named("toWithPedidoDto")
    AvariaWithPedidoDto toWithPedidoDto(Avaria entity);

    @IterableMapping(qualifiedByName = "toWithPedidoDto")
    List<AvariaWithPedidoDto> toWithPedidoDtoList(List<Avaria> entities);

    // -------- To Entity --------

    Avaria toEntity(AvariaDto dto);

    @Mapping(source = "gravidade", target = "gravidade", qualifiedByName = "mapStringToGravidade")
    Avaria toEntity(AvariaDetailsDto dto);

    Avaria toEntity(AvariaDetailsExtendedDto dto);

    Avaria toEntity(AvariaWithEquipaDto dto);

    Avaria toEntity(AvariaWithNotasDto dto);

    Avaria toEntity(AvariaWithPedidoDto dto);

    // -------- Métodos auxiliares --------

    @Named("mapGravidadeToString")
    default String mapGravidadeToString(Integer gravidadeId) {
        if (gravidadeId == null) return null;
        return Prioridade.fromId(gravidadeId).name();
    }

    @Named("mapStringToGravidade")
    default Integer mapStringToGravidade(String gravidadeName) {
        if (gravidadeName == null) return null;
        return Prioridade.fromName(gravidadeName).getId();
    }

    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromExtendedDto(AvariaDetailsExtendedDto dto, @MappingTarget Avaria entity);
}
