package com.example.cper_core.repositories;

import com.example.cper_core.entities.ArmazemLote;
import com.example.cper_core.entities.MaterialSolicitacaoMaterial;
import com.example.cper_core.entities.MaterialSolicitacaoMaterialId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MaterialSolicitacaoMaterialRepository extends JpaRepository<MaterialSolicitacaoMaterial, MaterialSolicitacaoMaterialId>, JpaSpecificationExecutor<MaterialSolicitacaoMaterial> {
  }