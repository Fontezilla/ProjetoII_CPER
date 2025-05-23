package com.example.cper_core.repositories;

import com.example.cper_core.entities.ArmazemLote;
import com.example.cper_core.entities.NotificacaoDestinatario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface NotificacaoDestinatarioRepository extends JpaRepository<NotificacaoDestinatario, Integer>, JpaSpecificationExecutor<NotificacaoDestinatario> {
    // Método para encontrar a associação entre Notificação e Funcionário
    NotificacaoDestinatario findByNotificacaoIdAndFuncionarioId(Integer idNotificacao, Integer idFuncionario);

    // Método para encontrar a associação entre Notificação e Cliente
    NotificacaoDestinatario findByNotificacaoIdAndClienteId(Integer idNotificacao, Integer idCliente);
  }