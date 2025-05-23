package com.example.cper_core.mappers;

import com.example.cper_core.enums.*;
import com.example.cper_core.dtos.solicitacao_energetica.*;
import com.example.cper_core.entities.SolicitacaoEnergetica;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SolicitacaoEnergeticaMapper {

    // --- Métodos Auxiliares de Enum ---
    @Named("mapTipoEnergiaRenovavel")
    default TipoEnergiaRenovavel mapTipoEnergiaRenovavel(Integer id) {
        return id == null ? null : TipoEnergiaRenovavel.fromId(id);
    }

    @Named("mapPrioridade")
    default Prioridade mapPrioridade(Integer id) {
        return id == null ? null : Prioridade.fromId(id);
    }

    @Named("mapEstadoSolicitacaoEnergetica")
    default EstadoSolicitacaoEnergetica mapEstadoSolicitacaoEnergetica(Integer id) {
        return id == null ? null : EstadoSolicitacaoEnergetica.fromId(id);
    }

    // --- To DTO ---
    @Named("toDto")
    SolicitacaoEnergeticaDto toDto(SolicitacaoEnergetica entity);

    @IterableMapping(qualifiedByName = "toDto")
    List<SolicitacaoEnergeticaDto> toDtoList(List<SolicitacaoEnergetica> entities);

    // --- To Details DTO ---
    @Named("toDetailsDto")
    @Mapping(target = "tipoEnergia", source = "tipoEnergia", qualifiedByName = "mapTipoEnergiaRenovavel")
    @Mapping(target = "prioridade", source = "prioridade", qualifiedByName = "mapPrioridade")
    @Mapping(target = "estado", source = "estado", qualifiedByName = "mapEstadoSolicitacaoEnergetica")
    SolicitacaoEnergeticaDetailsDto toDetailsDto(SolicitacaoEnergetica entity);

    @IterableMapping(qualifiedByName = "toDetailsDto")
    List<SolicitacaoEnergeticaDetailsDto> toDetailsDtoList(List<SolicitacaoEnergetica> entities);

    // --- To Extended DTO ---
    @Named("toExtendedDto")
    @Mapping(target = "tipoEnergia", source = "tipoEnergia", qualifiedByName = "mapTipoEnergiaRenovavel")
    @Mapping(target = "prioridade", source = "prioridade", qualifiedByName = "mapPrioridade")
    @Mapping(target = "estado", source = "estado", qualifiedByName = "mapEstadoSolicitacaoEnergetica")
    SolicitacaoEnergeticaDetailsExtendedDto toExtendedDto(SolicitacaoEnergetica entity);

    @IterableMapping(qualifiedByName = "toExtendedDto")
    List<SolicitacaoEnergeticaDetailsExtendedDto> toExtendedDtoList(List<SolicitacaoEnergetica> entities);

    // --- To WithRelationships DTO ---
    @Named("toWithRelationshipsDto")
    SolicitacaoEnergeticaWithRelationshipsDto toWithRelationshipsDto(SolicitacaoEnergetica entity);

    @IterableMapping(qualifiedByName = "toWithRelationshipsDto")
    List<SolicitacaoEnergeticaWithRelationshipsDto> toWithRelationshipsDtoList(List<SolicitacaoEnergetica> entities);

    // --- To Entity ---
    SolicitacaoEnergetica toEntity(SolicitacaoEnergeticaDto dto);

    @Mapping(target = "tipoEnergia", expression = "java(dto.getTipoEnergia() != null ? dto.getTipoEnergia().getId() : null)")
    @Mapping(target = "prioridade", expression = "java(dto.getPrioridade() != null ? dto.getPrioridade().getId() : null)")
    @Mapping(target = "estado", expression = "java(dto.getEstado() != null ? dto.getEstado().getId() : null)")
    SolicitacaoEnergetica toEntity(SolicitacaoEnergeticaDetailsDto dto);

    @Mapping(target = "tipoEnergia", expression = "java(dto.getTipoEnergia() != null ? dto.getTipoEnergia().getId() : null)")
    @Mapping(target = "prioridade", expression = "java(dto.getPrioridade() != null ? dto.getPrioridade().getId() : null)")
    @Mapping(target = "estado", expression = "java(dto.getEstado() != null ? dto.getEstado().getId() : null)")
    SolicitacaoEnergetica toEntity(SolicitacaoEnergeticaDetailsExtendedDto dto);

    SolicitacaoEnergetica toEntity(SolicitacaoEnergeticaWithRelationshipsDto dto);

    // --- Partial Update ---
    @Mapping(target = "tipoEnergia", expression = "java(dto.getTipoEnergia() != null ? dto.getTipoEnergia().getId() : entity.getTipoEnergia())")
    @Mapping(target = "prioridade", expression = "java(dto.getPrioridade() != null ? dto.getPrioridade().getId() : entity.getPrioridade())")
    @Mapping(target = "estado", expression = "java(dto.getEstado() != null ? dto.getEstado().getId() : entity.getEstado())")
    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromExtendedDto(SolicitacaoEnergeticaDetailsExtendedDto dto, @MappingTarget SolicitacaoEnergetica entity);
}