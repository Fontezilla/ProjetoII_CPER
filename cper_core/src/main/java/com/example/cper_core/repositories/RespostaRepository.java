package com.example.cper_core.repositories;

import com.example.cper_core.entities.Resposta;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RespostaRepository extends JpaRepositoryWithExtendedFetch<Resposta, Integer>,
        JpaSpecificationExecutor<Resposta> {

  @EntityGraph(attributePaths = {
          "ticket", "funcionario", "cliente"
  })
  @Query("SELECT r FROM Resposta r WHERE r.id = :id")
  @Override
  Optional<Resposta> findByIdExtended(Integer id);
}