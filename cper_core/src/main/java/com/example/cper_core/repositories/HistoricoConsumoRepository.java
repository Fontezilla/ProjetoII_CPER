package com.example.cper_core.repositories;

import com.example.cper_core.entities.ArmazemLote;
import com.example.cper_core.entities.HistoricoConsumo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface HistoricoConsumoRepository extends JpaRepository<HistoricoConsumo, Integer>, JpaSpecificationExecutor<HistoricoConsumo> {
  }