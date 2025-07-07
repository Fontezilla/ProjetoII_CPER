package com.example.cper_core.repositories;

import com.example.cper_core.entities.Avaria;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AvariaRepository extends JpaRepositoryWithExtendedFetch<Avaria, Integer>,
        JpaSpecificationExecutor<Avaria> {

  @EntityGraph(attributePaths = {
          "centroProducao",
          "inspecao",
          "notas",
          "equipas",
          "pedidoMateriais"
  })
  @Query("SELECT a FROM Avaria a WHERE a.id = :id")
  @Override
  Optional<Avaria> findByIdExtended(Integer id);
}