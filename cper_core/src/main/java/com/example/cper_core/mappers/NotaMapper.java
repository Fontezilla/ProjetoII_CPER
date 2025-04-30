package com.example.cper_core.mappers;

import com.example.cper_core.dtos.nota.*;
import com.example.cper_core.entities.Nota;
import com.example.cper_core.enums.Prioridade;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface NotaMapper {

    // -------- To DTO --------

    @Named("toDto")
    NotaDto toDto(Nota entity);

    @IterableMapping(qualifiedByName = "toDto")
    List<NotaDto> toDtoList(List<Nota> entities);

    @Named("toDetailsDto")
    @Mapping(source = "prioridade", target = "prioridade", qualifiedByName = "mapPrioridadeToString")
    NotaDetailsDto toDetailsDto(Nota entity);

    @IterableMapping(qualifiedByName = "toDetailsDto")
    List<NotaDetailsDto> toDetailsDtoList(List<Nota> entities);

    @Named("toDetailsExtendedDto")
    NotaDetailsExtendedDto toDetailsExtendedDto(Nota entity);

    @IterableMapping(qualifiedByName = "toDetailsExtendedDto")
    List<NotaDetailsExtendedDto> toDetailsExtendedDtoList(List<Nota> entities);

    // -------- To Entity --------

    @Named("toEntityFromDto")
    Nota toEntity(NotaDto dto);

    @Named("toEntityFromDetails")
    @Mapping(source = "prioridade", target = "prioridade", qualifiedByName = "mapStringToPrioridade")
    Nota toEntity(NotaDetailsDto dto);

    @Named("toEntityFromExtended")
    Nota toEntity(NotaDetailsExtendedDto dto);

    // -------- Conversões de listas inversas --------

    @IterableMapping(qualifiedByName = "toEntityFromDto")
    List<Nota> toEntityList(List<NotaDto> dtos);

    @IterableMapping(qualifiedByName = "toEntityFromDetails")
    List<Nota> toEntityDetailsList(List<NotaDetailsDto> dtos);

    @IterableMapping(qualifiedByName = "toEntityFromExtended")
    List<Nota> toEntityExtendedList(List<NotaDetailsExtendedDto> dtos);

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

    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromExtendedDto(NotaDetailsExtendedDto dto, @MappingTarget Nota entity);
}
