package com.example.cper_core.repositories;

import com.example.cper_core.entities.Anomalia;
import com.example.cper_core.entities.ArmazemLote;
import com.example.cper_core.entities.ArmazemLoteId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ArmazemLoteRepository extends JpaRepository<ArmazemLote, ArmazemLoteId>, JpaSpecificationExecutor<ArmazemLote> {
  }