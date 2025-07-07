package com.example.cper_core.repositories;

import com.example.cper_core.entities.Inspecao;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface InspecaoRepository extends JpaRepositoryWithExtendedFetch<Inspecao, Integer>,
        JpaSpecificationExecutor<Inspecao> {

  @EntityGraph(attributePaths = {
          "anomalia",
          "centroProducao",
          "equipas",
          "notas",
          "avarias"
  })
  @Query("SELECT i FROM Inspecao i WHERE i.id = :id")
  @Override
  Optional<Inspecao> findByIdExtended(Integer id);
}