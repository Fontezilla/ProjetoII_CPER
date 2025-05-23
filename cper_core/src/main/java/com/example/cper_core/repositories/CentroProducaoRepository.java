package com.example.cper_core.repositories;

import com.example.cper_core.entities.ArmazemLote;
import com.example.cper_core.entities.CentroProducao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CentroProducaoRepository extends JpaRepository<CentroProducao, Integer>, JpaSpecificationExecutor<CentroProducao> {
  }