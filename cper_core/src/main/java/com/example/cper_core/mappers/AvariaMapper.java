package com.example.cper_core.mappers;

import com.example.cper_core.enums.*;
import com.example.cper_core.dtos.avaria.*;
import com.example.cper_core.entities.Avaria;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AvariaMapper {

    // -------- Métodos Auxiliares de Enum --------
    @Named("mapEstadoAvaria")
    default EstadoAvaria mapEstadoAvaria(Integer id) {
        return id == null ? null : EstadoAvaria.fromId(id);
    }

    @Named("mapPrioridade")
    default Prioridade mapPrioridade(Integer id) {
        return id == null ? null : Prioridade.fromId(id);
    }

    // -------- To DTO --------
    @Named("toDto")
    AvariaDto toDto(Avaria entity);

    @IterableMapping(qualifiedByName = "toDto")
    List<AvariaDto> toDtoList(List<Avaria> entities);

    // -------- To Details DTO --------
    @Named("toDetailsDto")
    @Mapping(target = "gravidade", source = "gravidade", qualifiedByName = "mapPrioridade")
    @Mapping(target = "estado", source = "estado", qualifiedByName = "mapEstadoAvaria")
    AvariaDetailsDto toDetailsDto(Avaria entity);

    @IterableMapping(qualifiedByName = "toDetailsDto")
    List<AvariaDetailsDto> toDetailsDtoList(List<Avaria> entities);

    // -------- To Extended DTO --------
    @Named("toExtendedDto")
    @Mapping(target = "gravidade", source = "gravidade", qualifiedByName = "mapPrioridade")
    @Mapping(target = "estado", source = "estado", qualifiedByName = "mapEstadoAvaria")
    AvariaDetailsExtendedDto toExtendedDto(Avaria entity);

    @IterableMapping(qualifiedByName = "toExtendedDto")
    List<AvariaDetailsExtendedDto> toExtendedDtoList(List<Avaria> entities);

    // -------- To WithRelationships DTO --------
    @Named("toWithRelationshipsDto")
    AvariaWithRelationshipsDto toWithRelationshipsDto(Avaria entity);

    @IterableMapping(qualifiedByName = "toWithRelationshipsDto")
    List<AvariaWithRelationshipsDto> toWithRelationshipsDtoList(List<Avaria> entities);

    // -------- To Entity --------
    Avaria toEntity(AvariaDto dto);

    @Mapping(target = "gravidade", expression = "java(dto.getGravidade() != null ? dto.getGravidade().getId() : null)")
    @Mapping(target = "estado", expression = "java(dto.getEstado() != null ? dto.getEstado().getId() : null)")
    Avaria toEntity(AvariaDetailsDto dto);

    @Mapping(target = "gravidade", expression = "java(dto.getGravidade() != null ? dto.getGravidade().getId() : null)")
    @Mapping(target = "estado", expression = "java(dto.getEstado() != null ? dto.getEstado().getId() : null)")
    Avaria toEntity(AvariaDetailsExtendedDto dto);

    Avaria toEntity(AvariaWithRelationshipsDto dto);

    // -------- Partial Update --------
    @Mapping(target = "gravidade", expression = "java(dto.getGravidade() != null ? dto.getGravidade().getId() : entity.getGravidade())")
    @Mapping(target = "estado", expression = "java(dto.getEstado() != null ? dto.getEstado().getId() : entity.getEstado())")
    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromExtendedDto(AvariaDetailsExtendedDto dto, @MappingTarget Avaria entity);
}