package com.example.cper_core.mappers;

import com.example.cper_core.enums.*;
import com.example.cper_core.dtos.endereco.*;
import com.example.cper_core.entities.Endereco;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EnderecoMapper {

    // --- To DTO ---
    @Named("toDto")
    EnderecoDto toDto(Endereco entity);

    @IterableMapping(qualifiedByName = "toDto")
    List<EnderecoDto> toDtoList(List<Endereco> entities);

    // --- To Details DTO ---
    @Named("toDetailsDto")
    EnderecoDetailsDto toDetailsDto(Endereco entity);

    @IterableMapping(qualifiedByName = "toDetailsDto")
    List<EnderecoDetailsDto> toDetailsDtoList(List<Endereco> entities);

    // --- To Extended DTO ---
    @Named("toExtendedDto")
    EnderecoDetailsExtendedDto toExtendedDto(Endereco entity);

    @IterableMapping(qualifiedByName = "toExtendedDto")
    List<EnderecoDetailsExtendedDto> toExtendedDtoList(List<Endereco> entities);

    // --- To WithRelationships DTO ---
    @Named("toWithRelationshipsDto")
    EnderecoWithRelationshipsDto toWithRelationshipsDto(Endereco entity);

    @IterableMapping(qualifiedByName = "toWithRelationshipsDto")
    List<EnderecoWithRelationshipsDto> toWithRelationshipsDtoList(List<Endereco> entities);

    // --- To Entity ---
    Endereco toEntity(EnderecoDto dto);

    Endereco toEntity(EnderecoDetailsDto dto);

    Endereco toEntity(EnderecoDetailsExtendedDto dto);

    Endereco toEntity(EnderecoWithRelationshipsDto dto);

    // --- Partial Update ---
    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromExtendedDto(EnderecoDetailsExtendedDto dto, @MappingTarget Endereco entity);
}