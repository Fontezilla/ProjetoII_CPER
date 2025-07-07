package com.example.cper_core.repositories;

import com.example.cper_core.entities.Fatura;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface FaturaRepository extends JpaRepositoryWithExtendedFetch<Fatura, Integer>,
        JpaSpecificationExecutor<Fatura> {

  @EntityGraph(attributePaths = {
          "contrato",
          "funcionario"
  })
  @Query("SELECT f FROM Fatura f WHERE f.id = :id")
  @Override
  Optional<Fatura> findByIdExtended(Integer id);
}