package com.example.cper_core.repositories;

import com.example.cper_core.entities.SolicitacaoMaterial;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface SolicitacaoMaterialRepository extends JpaRepositoryWithExtendedFetch<SolicitacaoMaterial, Integer>,
        JpaSpecificationExecutor<SolicitacaoMaterial> {

  @EntityGraph(attributePaths = {
          "materialSolicitacoes"
  })
  @Query("SELECT s FROM SolicitacaoMaterial s WHERE s.id = :id")
  @Override
  Optional<SolicitacaoMaterial> findByIdExtended(Integer id);
}