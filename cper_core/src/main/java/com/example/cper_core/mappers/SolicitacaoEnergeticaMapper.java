package com.example.cper_core.mappers;

import com.example.cper_core.dtos.solicitacao_energetica.*;
import com.example.cper_core.entities.SolicitacaoEnergetica;
import com.example.cper_core.enums.EstadoSolicitacaoEnergetica;
import com.example.cper_core.enums.Prioridade;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SolicitacaoEnergeticaMapper {

    // -------- Mapeamentos individuais --------

    @Named("toDto")
    SolicitacaoEnergeticaDto toDto(SolicitacaoEnergetica entity);

    @Named("toEntity")
    SolicitacaoEnergetica toEntity(SolicitacaoEnergeticaDto dto);

    @Named("toDetailsDto")
    @Mapping(source = "prioridade", target = "prioridade", qualifiedByName = "mapPrioridadeToString")
    @Mapping(source = "estado", target = "estado", qualifiedByName = "mapEstadoToString")
    SolicitacaoEnergeticaDetailsDto toDetailsDto(SolicitacaoEnergetica entity);

    @Named("toEntity")
    SolicitacaoEnergetica toEntity(SolicitacaoEnergeticaDetailsDto dto);

    @Named("toDetailsExtendedDto")
    SolicitacaoEnergeticaDetailsExtendedDto toDetailsExtendedDto(SolicitacaoEnergetica entity);

    @Named("toEntity")
    SolicitacaoEnergetica toEntity(SolicitacaoEnergeticaDetailsExtendedDto dto);

    // -------- Mapeamentos de listas --------

    @IterableMapping(qualifiedByName = "toDto")
    List<SolicitacaoEnergeticaDto> toDtoList(List<SolicitacaoEnergetica> entities);

    @IterableMapping(qualifiedByName = "toDetailsDto")
    List<SolicitacaoEnergeticaDetailsDto> toDetailsDtoList(List<SolicitacaoEnergetica> entities);

    @IterableMapping(qualifiedByName = "toDetailsExtendedDto")
    List<SolicitacaoEnergeticaDetailsExtendedDto> toDetailsExtendedDtoList(List<SolicitacaoEnergetica> entities);

    // -------- Métodos de conversão inversa --------

    @IterableMapping(qualifiedByName = "toEntity")
    List<SolicitacaoEnergetica> toEntityList(List<SolicitacaoEnergeticaDto> dtos);

    @IterableMapping(qualifiedByName = "toEntity")
    List<SolicitacaoEnergetica> toEntityDetailsList(List<SolicitacaoEnergeticaDetailsDto> dtos);

    @IterableMapping(qualifiedByName = "toEntity")
    List<SolicitacaoEnergetica> toEntityExtendedList(List<SolicitacaoEnergeticaDetailsExtendedDto> dtos);

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
        return EstadoSolicitacaoEnergetica.fromId(estadoId).name();
    }

    @Named("mapStringToEstado")
    default Integer mapStringToEstado(String estadoName) {
        if (estadoName == null) return null;
        return EstadoSolicitacaoEnergetica.fromName(estadoName).getId();
    }

    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromExtendedDto(SolicitacaoEnergeticaDetailsExtendedDto dto, @MappingTarget SolicitacaoEnergetica entity);
}
