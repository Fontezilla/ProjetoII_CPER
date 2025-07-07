package com.example.cper_core.repositories;

import com.example.cper_core.entities.Cliente;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ClienteRepository extends JpaRepositoryWithExtendedFetch<Cliente, Integer>,
        JpaSpecificationExecutor<Cliente> {

  @EntityGraph(attributePaths = {
          "endereco",
          "historicoConsumos",
          "solicitacaoEnergeticas",
          "tickets",
          "respostas",
          "notificacoes"
  })
  @Query("SELECT c FROM Cliente c WHERE c.id = :id")
  @Override
  Optional<Cliente> findByIdExtended(Integer id);

  Optional<Cliente> findByEmail(String email);
}
