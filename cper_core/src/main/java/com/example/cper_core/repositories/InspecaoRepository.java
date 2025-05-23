package com.example.cper_core.repositories;

import com.example.cper_core.entities.ArmazemLote;
import com.example.cper_core.entities.Inspecao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface InspecaoRepository extends JpaRepository<Inspecao, Integer>, JpaSpecificationExecutor<Inspecao> {
  }