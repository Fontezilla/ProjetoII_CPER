package com.example.cper_core.repositories;

import com.example.cper_core.entities.Notificacao;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface NotificacaoRepository extends JpaRepositoryWithExtendedFetch<Notificacao, Integer>,
        JpaSpecificationExecutor<Notificacao> {

  @EntityGraph(attributePaths = {
          "notificacaoUsuarios"
  })
  @Query("SELECT n FROM Notificacao n WHERE n.id = :id")
  @Override
  Optional<Notificacao> findByIdExtended(Integer id);
}