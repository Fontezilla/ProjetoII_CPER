package com.example.cper_core.repositories;

import com.example.cper_core.entities.ArmazemLote;
import com.example.cper_core.entities.Resposta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RespostaRepository extends JpaRepository<Resposta, Integer>, JpaSpecificationExecutor<Resposta> {
  }