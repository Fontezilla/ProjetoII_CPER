package com.example.cper_core.mappers;

import com.example.cper_core.enums.*;
import com.example.cper_core.dtos.contrato.*;
import com.example.cper_core.entities.Contrato;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ContratoMapper {

    // --- Métodos Auxiliares de Enum ---
    @Named("mapTipoContrato")
    default TipoContrato mapTipoContrato(Integer id) {
        return id == null ? null : TipoContrato.fromId(id);
    }

    @Named("mapEstadoContrato")
    default EstadoContrato mapEstadoContrato(Integer id) {
        return id == null ? null : EstadoContrato.fromId(id);
    }

    // --- To DTO ---
    @Named("toDto")
    ContratoDto toDto(Contrato entity);

    @IterableMapping(qualifiedByName = "toDto")
    List<ContratoDto> toDtoList(List<Contrato> entities);

    // --- To Details DTO ---
    @Named("toDetailsDto")
    @Mapping(target = "tipoContrato", source = "tipoContrato", qualifiedByName = "mapTipoContrato")
    @Mapping(target = "estado", source = "estado", qualifiedByName = "mapEstadoContrato")
    ContratoDetailsDto toDetailsDto(Contrato entity);

    @IterableMapping(qualifiedByName = "toDetailsDto")
    List<ContratoDetailsDto> toDetailsDtoList(List<Contrato> entities);

    // --- To Extended DTO ---
    @Named("toExtendedDto")
    @Mapping(target = "tipoContrato", source = "tipoContrato", qualifiedByName = "mapTipoContrato")
    @Mapping(target = "estado", source = "estado", qualifiedByName = "mapEstadoContrato")
    ContratoDetailsExtendedDto toExtendedDto(Contrato entity);

    @IterableMapping(qualifiedByName = "toExtendedDto")
    List<ContratoDetailsExtendedDto> toExtendedDtoList(List<Contrato> entities);

    // --- To WithRelationships DTO ---
    @Named("toWithRelationshipsDto")
    ContratoWithRelationshipsDto toWithRelationshipsDto(Contrato entity);

    @IterableMapping(qualifiedByName = "toWithRelationshipsDto")
    List<ContratoWithRelationshipsDto> toWithRelationshipsDtoList(List<Contrato> entities);

    // --- To Entity ---
    Contrato toEntity(ContratoDto dto);

    @Mapping(target = "tipoContrato", expression = "java(dto.getTipoContrato() != null ? dto.getTipoContrato().getId() : null)")
    @Mapping(target = "estado", expression = "java(dto.getEstado() != null ? dto.getEstado().getId() : null)")
    Contrato toEntity(ContratoDetailsDto dto);

    @Mapping(target = "tipoContrato", expression = "java(dto.getTipoContrato() != null ? dto.getTipoContrato().getId() : null)")
    @Mapping(target = "estado", expression = "java(dto.getEstado() != null ? dto.getEstado().getId() : null)")
    Contrato toEntity(ContratoDetailsExtendedDto dto);

    Contrato toEntity(ContratoWithRelationshipsDto dto);

    // --- Partial Update ---
    @Mapping(target = "tipoContrato", expression = "java(dto.getTipoContrato() != null ? dto.getTipoContrato().getId() : entity.getTipoContrato())")
    @Mapping(target = "estado", expression = "java(dto.getEstado() != null ? dto.getEstado().getId() : entity.getEstado())")
    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromExtendedDto(ContratoDetailsExtendedDto dto, @MappingTarget Contrato entity);
}