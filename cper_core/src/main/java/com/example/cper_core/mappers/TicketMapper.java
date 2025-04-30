package com.example.cper_core.mappers;

import com.example.cper_core.dtos.ticket.*;
import com.example.cper_core.entities.Ticket;
import com.example.cper_core.enums.EstadoSolicitacaoMaterial;
import com.example.cper_core.enums.Prioridade;
import org.mapstruct.*;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TicketMapper {

    // -------- To DTO --------

    @Named("toDto")
    TicketDto toDto(Ticket entity);

    @IterableMapping(qualifiedByName = "toDto")
    List<TicketDto> toDtoList(List<Ticket> entities);

    @Named("toDetailsDto")
    @Mapping(source = "prioridade", target = "prioridade", qualifiedByName = "mapPrioridadeToString")
    @Mapping(source = "estado", target = "estado", qualifiedByName = "mapEstadoToString")
    TicketDetailsDto toDetailsDto(Ticket entity);

    @IterableMapping(qualifiedByName = "toDetailsDto")
    List<TicketDetailsDto> toDetailsDtoList(List<Ticket> entities);

    @Named("toExtendedDto")
    TicketDetailsExtendedDto toExtendedDto(Ticket entity);

    @IterableMapping(qualifiedByName = "toExtendedDto")
    List<TicketDetailsExtendedDto> toExtendedDtoList(List<Ticket> entities);

    @Named("toWithRespostaDto")
    TicketWithRespostaDto toWithRespostaDto(Ticket entity);

    @IterableMapping(qualifiedByName = "toWithRespostaDto")
    List<TicketWithRespostaDto> toWithRespostaDtoList(List<Ticket> entities);

    // -------- To Entity --------

    @Named("toEntity")
    Ticket toEntity(TicketDto dto);

    @Named("toEntity")
    @Mapping(source = "prioridade", target = "prioridade", qualifiedByName = "mapStringToPrioridade")
    @Mapping(source = "estado", target = "estado", qualifiedByName = "mapStringToEstado")
    Ticket toEntity(TicketDetailsDto dto);

    @Named("toEntity")
    Ticket toEntity(TicketDetailsExtendedDto dto);

    @Named("toEntity")
    Ticket toEntity(TicketWithRespostaDto dto);

    // -------- Conversões de listas inversas --------

    @IterableMapping(qualifiedByName = "toEntity")
    List<Ticket> toEntityList(List<TicketDto> dtos);

    @IterableMapping(qualifiedByName = "toEntity")
    List<Ticket> toEntityDetailsList(List<TicketDetailsDto> dtos);

    @IterableMapping(qualifiedByName = "toEntity")
    List<Ticket> toEntityExtendedList(List<TicketDetailsExtendedDto> dtos);

    @IterableMapping(qualifiedByName = "toEntity")
    List<Ticket> toEntityWithRespostaList(List<TicketWithRespostaDto> dtos);

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

    @Named("mapEstadoToString")
    default String mapEstadoToString(Integer estadoId) {
        if (estadoId == null) return null;
        return EstadoSolicitacaoMaterial.fromId(estadoId).name();
    }

    @Named("mapStringToEstado")
    default Integer mapStringToEstado(String estadoName) {
        if (estadoName == null) return null;
        return EstadoSolicitacaoMaterial.fromName(estadoName).getId();
    }

    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromExtendedDto(TicketDetailsExtendedDto dto, @MappingTarget Ticket entity);
}
