package com.example.cper_core.mappers;

import com.example.cper_core.enums.*;
import com.example.cper_core.dtos.notificacao.*;
import com.example.cper_core.entities.Notificacao;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface NotificacaoMapper {

    // --- To DTO ---
    @Named("toDto")
    NotificacaoDto toDto(Notificacao entity);

    @IterableMapping(qualifiedByName = "toDto")
    List<NotificacaoDto> toDtoList(List<Notificacao> entities);

    // --- To Details DTO ---
    @Named("toDetailsDto")
    NotificacaoDetailsDto toDetailsDto(Notificacao entity);

    @IterableMapping(qualifiedByName = "toDetailsDto")
    List<NotificacaoDetailsDto> toDetailsDtoList(List<Notificacao> entities);

    // --- To Extended DTO ---
    @Named("toExtendedDto")
    NotificacaoDetailsExtendedDto toExtendedDto(Notificacao entity);

    @IterableMapping(qualifiedByName = "toExtendedDto")
    List<NotificacaoDetailsExtendedDto> toExtendedDtoList(List<Notificacao> entities);

    // --- To WithRelationships DTO ---
    @Named("toWithRelationshipsDto")
    NotificacaoWithRelationshipsDto toWithRelationshipsDto(Notificacao entity);

    @IterableMapping(qualifiedByName = "toWithRelationshipsDto")
    List<NotificacaoWithRelationshipsDto> toWithRelationshipsDtoList(List<Notificacao> entities);

    // --- To Entity ---
    Notificacao toEntity(NotificacaoDto dto);

    Notificacao toEntity(NotificacaoDetailsDto dto);

    Notificacao toEntity(NotificacaoDetailsExtendedDto dto);

    Notificacao toEntity(NotificacaoWithRelationshipsDto dto);

    // --- Partial Update ---
    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromExtendedDto(NotificacaoDetailsExtendedDto dto, @MappingTarget Notificacao entity);
}