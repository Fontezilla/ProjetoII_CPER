package com.example.cper_core.repositories;

import com.example.cper_core.entities.NotificacaoDestinatario;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface NotificacaoDestinatarioRepository extends JpaRepositoryWithExtendedFetch<NotificacaoDestinatario, Integer>,
        JpaSpecificationExecutor<NotificacaoDestinatario> {

    @EntityGraph(attributePaths = {
            "notificacao", "funcionario", "cliente"
    })
    @Query("SELECT nd FROM NotificacaoDestinatario nd WHERE nd.id = :id")
    @Override
    Optional<NotificacaoDestinatario> findByIdExtended(Integer id);

    NotificacaoDestinatario findByNotificacaoIdAndFuncionarioId(Integer idNotificacao, Integer idFuncionario);

    NotificacaoDestinatario findByNotificacaoIdAndClienteId(Integer idNotificacao, Integer idCliente);
}