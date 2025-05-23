package com.example.cper_core.repositories;

import com.example.cper_core.entities.ArmazemLote;
import com.example.cper_core.entities.SolicitacaoMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SolicitacaoMaterialRepository extends JpaRepository<SolicitacaoMaterial, Integer>, JpaSpecificationExecutor<SolicitacaoMaterial> {
  }