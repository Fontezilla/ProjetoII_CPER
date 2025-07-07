package com.example.cper_core.repositories;

import com.example.cper_core.entities.ArmazemLote;
import com.example.cper_core.entities.ArmazemLoteId;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ArmazemLoteRepository extends JpaRepositoryWithExtendedFetch<ArmazemLote, ArmazemLoteId>,
        JpaSpecificationExecutor<ArmazemLote> {

  @EntityGraph(attributePaths = {
          "armazem",
          "lote"
  })
  @Query("SELECT al FROM ArmazemLote al WHERE al.id = :id")
  @Override
  Optional<ArmazemLote> findByIdExtended(ArmazemLoteId id);
}