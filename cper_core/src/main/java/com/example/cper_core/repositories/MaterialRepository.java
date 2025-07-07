package com.example.cper_core.repositories;

import com.example.cper_core.entities.Material;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MaterialRepository extends JpaRepositoryWithExtendedFetch<Material, Integer>,
        JpaSpecificationExecutor<Material> {

  @EntityGraph(attributePaths = {
          "materiaisSolicitacaoMateriais",
          "materiaisPedidoMateriais",
          "lotes"
  })
  @Query("SELECT m FROM Material m WHERE m.id = :id")
  @Override
  Optional<Material> findByIdExtended(Integer id);
}
