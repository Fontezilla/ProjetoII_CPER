package com.example.cper_core.mappers;

import com.example.cper_core.enums.*;
import com.example.cper_core.dtos.cliente.*;
import com.example.cper_core.entities.Cliente;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ClienteMapper {

    // --- Métodos Auxiliares de Enum ---
    @Named("mapTipoCliente")
    default TipoCliente mapTipoCliente(Integer id) {
        return id == null ? null : TipoCliente.fromId(id);
    }

    @Named("mapEstadoCliente")
    default EstadoCliente mapEstadoCliente(Integer id) {
        return id == null ? null : EstadoCliente.fromId(id);
    }

    // --- To DTO ---
    @Named("toDto")
    ClienteDto toDto(Cliente entity);

    @IterableMapping(qualifiedByName = "toDto")
    List<ClienteDto> toDtoList(List<Cliente> entities);

    // --- To Details DTO ---
    @Named("toDetailsDto")
    @Mapping(target = "tipoCliente", source = "tipoCliente", qualifiedByName = "mapTipoCliente")
    @Mapping(target = "estado", source = "estado", qualifiedByName = "mapEstadoCliente")
    ClienteDetailsDto toDetailsDto(Cliente entity);

    @IterableMapping(qualifiedByName = "toDetailsDto")
    List<ClienteDetailsDto> toDetailsDtoList(List<Cliente> entities);

    // --- To Extended DTO ---
    @Named("toExtendedDto")
    @Mapping(target = "tipoCliente", source = "tipoCliente", qualifiedByName = "mapTipoCliente")
    @Mapping(target = "estado", source = "estado", qualifiedByName = "mapEstadoCliente")
    ClienteDetailsExtendedDto toExtendedDto(Cliente entity);

    @IterableMapping(qualifiedByName = "toExtendedDto")
    List<ClienteDetailsExtendedDto> toExtendedDtoList(List<Cliente> entities);

    // --- To WithRelationships DTO ---
    @Named("toWithRelationshipsDto")
    ClienteWithRelationshipsDto toWithRelationshipsDto(Cliente entity);

    @IterableMapping(qualifiedByName = "toWithRelationshipsDto")
    List<ClienteWithRelationshipsDto> toWithRelationshipsDtoList(List<Cliente> entities);

    // --- To Entity ---
    Cliente toEntity(ClienteDto dto);

    @Mapping(target = "tipoCliente", expression = "java(dto.getTipoCliente() != null ? dto.getTipoCliente().getId() : null)")
    @Mapping(target = "estado", expression = "java(dto.getEstado() != null ? dto.getEstado().getId() : null)")
    Cliente toEntity(ClienteDetailsDto dto);

    @Mapping(target = "tipoCliente", expression = "java(dto.getTipoCliente() != null ? dto.getTipoCliente().getId() : null)")
    @Mapping(target = "estado", expression = "java(dto.getEstado() != null ? dto.getEstado().getId() : null)")
    Cliente toEntity(ClienteDetailsExtendedDto dto);

    Cliente toEntity(ClienteWithRelationshipsDto dto);

    // --- Partial Update ---
    @Mapping(target = "tipoCliente", expression = "java(dto.getTipoCliente() != null ? dto.getTipoCliente().getId() : entity.getTipoCliente())")
    @Mapping(target = "estado", expression = "java(dto.getEstado() != null ? dto.getEstado().getId() : entity.getEstado())")
    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromExtendedDto(ClienteDetailsExtendedDto dto, @MappingTarget Cliente entity);
}