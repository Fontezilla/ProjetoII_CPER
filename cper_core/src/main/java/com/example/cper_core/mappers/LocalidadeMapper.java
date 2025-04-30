package com.example.cper_core.mappers;

import com.example.cper_core.dtos.anomalia.AnomaliaDetailsExtendedDto;
import com.example.cper_core.dtos.localidade.*;
import com.example.cper_core.entities.Anomalia;
import com.example.cper_core.entities.Localidade;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LocalidadeMapper {

    // -------- To DTO --------

    @Named("toDto")
    LocalidadeDto toDto(Localidade entity);

    @IterableMapping(qualifiedByName = "toDto")
    List<LocalidadeDto> toDtoList(List<Localidade> entities);

    @Named("toDetailsDto")
    LocalidadeDetailsDto toDetailsDto(Localidade entity);

    @IterableMapping(qualifiedByName = "toDetailsDto")
    List<LocalidadeDetailsDto> toDetailsDtoList(List<Localidade> entities);

    @Named("toWithEnderecosDto")
    LocalidadeWithEnderecosDto toWithEnderecosDto(Localidade entity);

    @IterableMapping(qualifiedByName = "toWithEnderecosDto")
    List<LocalidadeWithEnderecosDto> toWithEnderecosDtoList(List<Localidade> entities);

    // -------- To Entity --------

    @Named("toEntityFromDto")
    Localidade toEntity(LocalidadeDto dto);

    @Named("toEntityFromDetails")
    Localidade toEntity(LocalidadeDetailsDto dto);

    @Named("toEntityFromWithEnderecos")
    Localidade toEntity(LocalidadeWithEnderecosDto dto);

    // -------- Conversões de listas inversas --------

    @IterableMapping(qualifiedByName = "toEntityFromDto")
    List<Localidade> toEntityList(List<LocalidadeDto> dtos);

    @IterableMapping(qualifiedByName = "toEntityFromDetails")
    List<Localidade> toEntityDetailsList(List<LocalidadeDetailsDto> dtos);

    @IterableMapping(qualifiedByName = "toEntityFromWithEnderecos")
    List<Localidade> toEntityWithEnderecosList(List<LocalidadeWithEnderecosDto> dtos);

    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromExtendedDto(LocalidadeDetailsDto dto, @MappingTarget Localidade entity);
}