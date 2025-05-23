package com.example.cper_core.repositories;

import com.example.cper_core.entities.ArmazemLote;
import com.example.cper_core.entities.PedidoGeracao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PedidoGeracaoRepository extends JpaRepository<PedidoGeracao, Integer>, JpaSpecificationExecutor<PedidoGeracao> {
  }