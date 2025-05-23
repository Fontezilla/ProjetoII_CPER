package com.example.cper_core.mappers;

import com.example.cper_core.enums.*;
import com.example.cper_core.dtos.pedido_geracao.*;
import com.example.cper_core.entities.PedidoGeracao;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PedidoGeracaoMapper {

    // --- Métodos Auxiliares de Enum ---
    @Named("mapTipoEnergiaRenovavel")
    default TipoEnergiaRenovavel mapTipoEnergiaRenovavel(Integer id) {
        return id == null ? null : TipoEnergiaRenovavel.fromId(id);
    }

    @Named("mapPrioridade")
    default Prioridade mapPrioridade(Integer id) {
        return id == null ? null : Prioridade.fromId(id);
    }

    @Named("mapEstadoPedidoGeracao")
    default EstadoPedidoGeracao mapEstadoPedidoGeracao(Integer id) {
        return id == null ? null : EstadoPedidoGeracao.fromId(id);
    }

    // --- To DTO ---
    @Named("toDto")
    PedidoGeracaoDto toDto(PedidoGeracao entity);

    @IterableMapping(qualifiedByName = "toDto")
    List<PedidoGeracaoDto> toDtoList(List<PedidoGeracao> entities);

    // --- To Details DTO ---
    @Named("toDetailsDto")
    @Mapping(target = "tipoEnergia", source = "tipoEnergia", qualifiedByName = "mapTipoEnergiaRenovavel")
    @Mapping(target = "prioridade", source = "prioridade", qualifiedByName = "mapPrioridade")
    @Mapping(target = "estado", source = "estado", qualifiedByName = "mapEstadoPedidoGeracao")
    PedidoGeracaoDetailsDto toDetailsDto(PedidoGeracao entity);

    @IterableMapping(qualifiedByName = "toDetailsDto")
    List<PedidoGeracaoDetailsDto> toDetailsDtoList(List<PedidoGeracao> entities);

    // --- To Extended DTO ---
    @Named("toExtendedDto")
    @Mapping(target = "tipoEnergia", source = "tipoEnergia", qualifiedByName = "mapTipoEnergiaRenovavel")
    @Mapping(target = "prioridade", source = "prioridade", qualifiedByName = "mapPrioridade")
    @Mapping(target = "estado", source = "estado", qualifiedByName = "mapEstadoPedidoGeracao")
    PedidoGeracaoDetailsExtendedDto toExtendedDto(PedidoGeracao entity);

    @IterableMapping(qualifiedByName = "toExtendedDto")
    List<PedidoGeracaoDetailsExtendedDto> toExtendedDtoList(List<PedidoGeracao> entities);

    // --- To WithRelationships DTO ---
    @Named("toWithRelationshipsDto")
    PedidoGeracaoWithRelationshipsDto toWithRelationshipsDto(PedidoGeracao entity);

    @IterableMapping(qualifiedByName = "toWithRelationshipsDto")
    List<PedidoGeracaoWithRelationshipsDto> toWithRelationshipsDtoList(List<PedidoGeracao> entities);

    // --- To Entity ---
    PedidoGeracao toEntity(PedidoGeracaoDto dto);

    @Mapping(target = "tipoEnergia", expression = "java(dto.getTipoEnergia() != null ? dto.getTipoEnergia().getId() : null)")
    @Mapping(target = "prioridade", expression = "java(dto.getPrioridade() != null ? dto.getPrioridade().getId() : null)")
    @Mapping(target = "estado", expression = "java(dto.getEstado() != null ? dto.getEstado().getId() : null)")
    PedidoGeracao toEntity(PedidoGeracaoDetailsDto dto);

    @Mapping(target = "tipoEnergia", expression = "java(dto.getTipoEnergia() != null ? dto.getTipoEnergia().getId() : null)")
    @Mapping(target = "prioridade", expression = "java(dto.getPrioridade() != null ? dto.getPrioridade().getId() : null)")
    @Mapping(target = "estado", expression = "java(dto.getEstado() != null ? dto.getEstado().getId() : null)")
    PedidoGeracao toEntity(PedidoGeracaoDetailsExtendedDto dto);

    PedidoGeracao toEntity(PedidoGeracaoWithRelationshipsDto dto);

    // --- Partial Update ---
    @Mapping(target = "tipoEnergia", expression = "java(dto.getTipoEnergia() != null ? dto.getTipoEnergia().getId() : entity.getTipoEnergia())")
    @Mapping(target = "prioridade", expression = "java(dto.getPrioridade() != null ? dto.getPrioridade().getId() : entity.getPrioridade())")
    @Mapping(target = "estado", expression = "java(dto.getEstado() != null ? dto.getEstado().getId() : entity.getEstado())")
    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromExtendedDto(PedidoGeracaoDetailsExtendedDto dto, @MappingTarget PedidoGeracao entity);
}