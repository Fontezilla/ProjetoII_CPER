package com.example.cper_core.mappers;

import com.example.cper_core.enums.*;
import com.example.cper_core.dtos.anomalia.*;
import com.example.cper_core.entities.Anomalia;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AnomaliaMapper {

    // -------- Métodos Auxiliares de Enum --------
    @Named("mapEstadoAnomalia")
    default EstadoAnomalia mapEstadoAnomalia(Integer id) {
        return id == null ? null : EstadoAnomalia.fromId(id);
    }

    @Named("mapPrioridade")
    default Prioridade mapPrioridade(Integer id) {
        return id == null ? null : Prioridade.fromId(id);
    }

    @Named("mapTipoAnomalia")
    default TipoAnomalia mapTipoAnomalia(Integer id) {
        return id == null ? null : TipoAnomalia.fromId(id);
    }

    // -------- To Basic DTO --------
    @Named("toDto")
    AnomaliaDto toDto(Anomalia entity);

    @IterableMapping(qualifiedByName = "toDto")
    List<AnomaliaDto> toDtoList(List<Anomalia> entities);

    // -------- To Details DTO --------
    @Named("toDetailsDto")
    @Mapping(target = "tipoAnomalia", source = "tipoAnomalia", qualifiedByName = "mapTipoAnomalia")
    @Mapping(target = "gravidade", source = "gravidade", qualifiedByName = "mapPrioridade")
    @Mapping(target = "estado", source = "estado", qualifiedByName = "mapEstadoAnomalia")
    AnomaliaDetailsDto toDetailsDto(Anomalia entity);

    @IterableMapping(qualifiedByName = "toDetailsDto")
    List<AnomaliaDetailsDto> toDetailsDtoList(List<Anomalia> entities);

    // -------- To Extended DTO --------
    @Named("toExtendedDto")
    @Mapping(target = "tipoAnomalia", source = "tipoAnomalia", qualifiedByName = "mapTipoAnomalia")
    @Mapping(target = "gravidade", source = "gravidade", qualifiedByName = "mapPrioridade")
    @Mapping(target = "estado", source = "estado", qualifiedByName = "mapEstadoAnomalia")
    AnomaliaDetailsExtendedDto toExtendedDto(Anomalia entity);

    @IterableMapping(qualifiedByName = "toExtendedDto")
    List<AnomaliaDetailsExtendedDto> toExtendedDtoList(List<Anomalia> entities);

    // -------- To WithNotas DTO --------
    @Named("toWithNotasDto")
    AnomaliaWithRelationshipsDto toWithNotasDto(Anomalia entity);

    @IterableMapping(qualifiedByName = "toWithNotasDto")
    List<AnomaliaWithRelationshipsDto> toWithNotasDtoList(List<Anomalia> entities);

    // -------- To Entity --------
    Anomalia toEntity(AnomaliaDto dto);

    @Mapping(target = "tipoAnomalia", expression = "java(dto.getTipoAnomalia() != null ? dto.getTipoAnomalia().getId() : null)")
    @Mapping(target = "gravidade", expression = "java(dto.getGravidade() != null ? dto.getGravidade().getId() : null)")
    @Mapping(target = "estado", expression = "java(dto.getEstado() != null ? dto.getEstado().getId() : null)")
    Anomalia toEntity(AnomaliaDetailsDto dto);

    @Mapping(target = "tipoAnomalia", expression = "java(dto.getTipoAnomalia() != null ? dto.getTipoAnomalia().getId() : null)")
    @Mapping(target = "gravidade", expression = "java(dto.getGravidade() != null ? dto.getGravidade().getId() : null)")
    @Mapping(target = "estado", expression = "java(dto.getEstado() != null ? dto.getEstado().getId() : null)")
    Anomalia toEntity(AnomaliaDetailsExtendedDto dto);

    Anomalia toEntity(AnomaliaWithRelationshipsDto dto);

    // -------- Update parcial --------
    @Mapping(target = "tipoAnomalia", expression = "java(dto.getTipoAnomalia() != null ? dto.getTipoAnomalia().getId() : entity.getTipoAnomalia())")
    @Mapping(target = "gravidade", expression = "java(dto.getGravidade() != null ? dto.getGravidade().getId() : entity.getGravidade())")
    @Mapping(target = "estado", expression = "java(dto.getEstado() != null ? dto.getEstado().getId() : entity.getEstado())")
    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromExtendedDto(AnomaliaDetailsExtendedDto dto, @MappingTarget Anomalia entity);
}