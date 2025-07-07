package com.example.cper_core.repositories;

import com.example.cper_core.entities.HistoricoConsumo;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface HistoricoConsumoRepository extends JpaRepositoryWithExtendedFetch<HistoricoConsumo, Integer>,
        JpaSpecificationExecutor<HistoricoConsumo> {

  @EntityGraph(attributePaths = {
          "cliente"
  })
  @Query("SELECT h FROM HistoricoConsumo h WHERE h.id = :id")
  @Override
  Optional<HistoricoConsumo> findByIdExtended(Integer id);
}
