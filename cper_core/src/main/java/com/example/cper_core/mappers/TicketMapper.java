package com.example.cper_core.mappers;

import com.example.cper_core.enums.*;
import com.example.cper_core.dtos.ticket.*;
import com.example.cper_core.entities.Ticket;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TicketMapper {

    // --- Métodos Auxiliares de Enum ---
    @Named("mapEstadoTicket")
    default EstadoTicket mapEstadoTicket(Integer id) {
        return id == null ? null : EstadoTicket.fromId(id);
    }

    @Named("mapTipoTicket")
    default TipoTicket mapTipoTicket(Integer id) {
        return id == null ? null : TipoTicket.fromId(id);
    }

    @Named("mapPrioridade")
    default Prioridade mapPrioridade(Integer id) {
        return id == null ? null : Prioridade.fromId(id);
    }

    // --- To DTO ---
    @Named("toDto")
    TicketDto toDto(Ticket entity);

    @IterableMapping(qualifiedByName = "toDto")
    List<TicketDto> toDtoList(List<Ticket> entities);

    // --- To Details DTO ---
    @Named("toDetailsDto")
    @Mapping(target = "estado", source = "estado", qualifiedByName = "mapEstadoTicket")
    @Mapping(target = "tipoTicket", source = "tipoTicket", qualifiedByName = "mapTipoTicket")
    @Mapping(target = "prioridade", source = "prioridade", qualifiedByName = "mapPrioridade")
    TicketDetailsDto toDetailsDto(Ticket entity);

    @IterableMapping(qualifiedByName = "toDetailsDto")
    List<TicketDetailsDto> toDetailsDtoList(List<Ticket> entities);

    // --- To Extended DTO ---
    @Named("toExtendedDto")
    @Mapping(target = "estado", source = "estado", qualifiedByName = "mapEstadoTicket")
    @Mapping(target = "tipoTicket", source = "tipoTicket", qualifiedByName = "mapTipoTicket")
    @Mapping(target = "prioridade", source = "prioridade", qualifiedByName = "mapPrioridade")
    TicketDetailsExtendedDto toExtendedDto(Ticket entity);

    @IterableMapping(qualifiedByName = "toExtendedDto")
    List<TicketDetailsExtendedDto> toExtendedDtoList(List<Ticket> entities);

    // --- To WithRelationships DTO ---
    @Named("toWithRelationshipsDto")
    TicketWithRelationshipsDto toWithRelationshipsDto(Ticket entity);

    @IterableMapping(qualifiedByName = "toWithRelationshipsDto")
    List<TicketWithRelationshipsDto> toWithRelationshipsDtoList(List<Ticket> entities);

    // --- To Entity ---
    Ticket toEntity(TicketDto dto);

    @Mapping(target = "estado", expression = "java(dto.getEstado() != null ? dto.getEstado().getId() : null)")
    @Mapping(target = "tipoTicket", expression = "java(dto.getTipoTicket() != null ? dto.getTipoTicket().getId() : null)")
    @Mapping(target = "prioridade", expression = "java(dto.getPrioridade() != null ? dto.getPrioridade().getId() : null)")
    Ticket toEntity(TicketDetailsDto dto);

    @Mapping(target = "estado", expression = "java(dto.getEstado() != null ? dto.getEstado().getId() : null)")
    @Mapping(target = "tipoTicket", expression = "java(dto.getTipoTicket() != null ? dto.getTipoTicket().getId() : null)")
    @Mapping(target = "prioridade", expression = "java(dto.getPrioridade() != null ? dto.getPrioridade().getId() : null)")
    Ticket toEntity(TicketDetailsExtendedDto dto);

    Ticket toEntity(TicketWithRelationshipsDto dto);

    // --- Partial Update ---
    @Mapping(target = "estado", expression = "java(dto.getEstado() != null ? dto.getEstado().getId() : entity.getEstado())")
    @Mapping(target = "tipoTicket", expression = "java(dto.getTipoTicket() != null ? dto.getTipoTicket().getId() : entity.getTipoTicket())")
    @Mapping(target = "prioridade", expression = "java(dto.getPrioridade() != null ? dto.getPrioridade().getId() : entity.getPrioridade())")
    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromExtendedDto(TicketDetailsExtendedDto dto, @MappingTarget Ticket entity);
}