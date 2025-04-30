package com.example.cper_core.mappers;

import com.example.cper_core.dtos.anomalia.AnomaliaDetailsExtendedDto;
import com.example.cper_core.dtos.lote.*;
import com.example.cper_core.entities.Anomalia;
import com.example.cper_core.entities.Lote;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LoteMapper {

    // -------- To DTO --------

    @Named("toDto")
    LoteDto toDto(Lote entity);

    @IterableMapping(qualifiedByName = "toDto")
    List<LoteDto> toDtoList(List<Lote> entities);

    @Named("toDetailsDto")
    LoteDetailsDto toDetailsDto(Lote entity);

    @IterableMapping(qualifiedByName = "toDetailsDto")
    List<LoteDetailsDto> toDetailsDtoList(List<Lote> entities);

    // -------- To Entity --------

    @Named("toEntityFromDto")
    Lote toEntity(LoteDto dto);

    @Named("toEntityFromDetails")
    Lote toEntity(LoteDetailsDto dto);

    // -------- Conversões de listas inversas --------

    @IterableMapping(qualifiedByName = "toEntityFromDto")
    List<Lote> toEntityList(List<LoteDto> dtos);

    @IterableMapping(qualifiedByName = "toEntityFromDetails")
    List<Lote> toEntityDetailsList(List<LoteDetailsDto> dtos);

    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromExtendedDto(LoteDetailsDto dto, @MappingTarget Lote entity);
}
