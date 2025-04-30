package com.example.cper_core.mappers;

import com.example.cper_core.dtos.resposta.*;
import com.example.cper_core.entities.Resposta;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RespostaMapper {

    // -------- To DTO --------

    @Named("toDto")
    RespostaDto toDto(Resposta entity);

    @IterableMapping(qualifiedByName = "toDto")
    List<RespostaDto> toDtoList(List<Resposta> entities);

    @Named("toDetailsDto")
    RespostaDetailsDto toDetailsDto(Resposta entity);

    @IterableMapping(qualifiedByName = "toDetailsDto")
    List<RespostaDetailsDto> toDetailsDtoList(List<Resposta> entities);

    // -------- To Entity --------

    @Named("toEntityFromDto")
    Resposta toEntity(RespostaDto dto);

    @Named("toEntityFromDetails")
    Resposta toEntity(RespostaDetailsDto dto);

    // -------- Conversões de listas inversas --------

    @IterableMapping(qualifiedByName = "toEntityFromDto")
    List<Resposta> toEntityList(List<RespostaDto> dtos);

    @IterableMapping(qualifiedByName = "toEntityFromDetails")
    List<Resposta> toEntityDetailsList(List<RespostaDetailsDto> dtos);

    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromExtendedDto(RespostaDetailsDto dto, @MappingTarget Resposta entity);
}
