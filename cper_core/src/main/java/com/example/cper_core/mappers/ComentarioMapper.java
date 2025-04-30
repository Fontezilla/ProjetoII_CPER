package com.example.cper_core.mappers;

import com.example.cper_core.dtos.anomalia.AnomaliaDetailsExtendedDto;
import com.example.cper_core.dtos.comentario.*;
import com.example.cper_core.entities.Anomalia;
import com.example.cper_core.entities.Comentario;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ComentarioMapper {

    // -------- To DTO --------

    @Named("toDto")
    ComentarioDto toDto(Comentario entity);

    @IterableMapping(qualifiedByName = "toDto")
    List<ComentarioDto> toDtoList(List<Comentario> entities);

    @Named("toDetailsDto")
    ComentarioDetailsDto toDetailsDto(Comentario entity);

    @IterableMapping(qualifiedByName = "toDetailsDto")
    List<ComentarioDetailsDto> toDetailsDtoList(List<Comentario> entities);

    // -------- To Entity --------

    Comentario toEntity(ComentarioDto dto);

    Comentario toEntity(ComentarioDetailsDto dto);

    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromExtendedDto(ComentarioDetailsDto dto, @MappingTarget Comentario entity);
}