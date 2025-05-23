package com.example.cper_core.mappers;

import com.example.cper_core.dtos.notificacao_destinario.*;
import com.example.cper_core.entities.NotificacaoDestinatario;
import com.example.cper_core.entities.Notificacao;
import com.example.cper_core.entities.Funcionario;
import com.example.cper_core.entities.Cliente;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface NotificacaoDestinatarioMapper {

    // --- To DTO ---
    @Named("toDto")
    NotificacaoDestinatarioDto toDto(NotificacaoDestinatario entity);

    @IterableMapping(qualifiedByName = "toDto")
    List<NotificacaoDestinatarioDto> toDtoList(List<NotificacaoDestinatario> entities);

    // --- To Details DTO ---
    @Named("toDetailsDto")
    @Mapping(target = "notificacao", source = "notificacao.id")
    @Mapping(target = "funcionario", source = "funcionario.id")
    @Mapping(target = "cliente", source = "cliente.id")
    NotificacaoDestinatarioDetailsDto toDetailsDto(NotificacaoDestinatario entity);

    @IterableMapping(qualifiedByName = "toDetailsDto")
    List<NotificacaoDestinatarioDetailsDto> toDetailsDtoList(List<NotificacaoDestinatario> entities);

    // --- To Entity ---
    @Named("toEntity")
    @Mapping(target = "notificacao", source = "notificacao", qualifiedByName = "mapNotificacao")
    @Mapping(target = "funcionario", source = "funcionario", qualifiedByName = "mapFuncionario")
    @Mapping(target = "cliente", source = "cliente", qualifiedByName = "mapCliente")
    NotificacaoDestinatario toEntity(NotificacaoDestinatarioDetailsDto dto);

    // --- Update Entity from DTO ---
    @Named("updateEntityFromDetailsDto")
    @Mapping(target = "notificacao", source = "notificacao", qualifiedByName = "mapNotificacao")
    @Mapping(target = "funcionario", source = "funcionario", qualifiedByName = "mapFuncionario")
    @Mapping(target = "cliente", source = "cliente", qualifiedByName = "mapCliente")
    void updateEntityFromDetailsDto(NotificacaoDestinatarioDetailsDto dto, @MappingTarget NotificacaoDestinatario entity);

    // --- Custom Mapping Methods ---
    @Named("mapNotificacao")
    default Notificacao mapNotificacao(Integer id) {
        if (id == null) return null;
        Notificacao notificacao = new Notificacao();
        notificacao.setId(id);
        return notificacao;
    }

    @Named("mapFuncionario")
    default Funcionario mapFuncionario(Integer id) {
        if (id == null) return null;
        Funcionario funcionario = new Funcionario();
        funcionario.setId(id);
        return funcionario;
    }

    @Named("mapCliente")
    default Cliente mapCliente(Integer id) {
        if (id == null) return null;
        Cliente cliente = new Cliente();
        cliente.setId(id);
        return cliente;
    }
}