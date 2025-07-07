package com.example.cper_core.repositories;

import com.example.cper_core.entities.Lote;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface LoteRepository extends JpaRepositoryWithExtendedFetch<Lote, Integer>,
        JpaSpecificationExecutor<Lote> {

  @EntityGraph(attributePaths = {
          "material",
          "armazemLotes"
  })
  @Query("SELECT l FROM Lote l WHERE l.id = :id")
  @Override
  Optional<Lote> findByIdExtended(Integer id);
}