package com.example.cper_core.repositories;

import com.example.cper_core.entities.MaterialSolicitacaoMaterial;
import com.example.cper_core.entities.MaterialSolicitacaoMaterialId;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MaterialSolicitacaoMaterialRepository extends JpaRepositoryWithExtendedFetch<MaterialSolicitacaoMaterial, MaterialSolicitacaoMaterialId>,
        JpaSpecificationExecutor<MaterialSolicitacaoMaterial> {

  @EntityGraph(attributePaths = {
          "material",
          "solicitacaoMaterial"
  })
  @Query("SELECT msm FROM MaterialSolicitacaoMaterial msm WHERE msm.id = :id")
  @Override
  Optional<MaterialSolicitacaoMaterial> findByIdExtended(MaterialSolicitacaoMaterialId id);
}