package com.example.cper_core.repositories;

import com.example.cper_core.entities.ArmazemLote;
import com.example.cper_core.entities.PedidoMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PedidoMaterialRepository extends JpaRepository<PedidoMaterial, Integer>, JpaSpecificationExecutor<PedidoMaterial> {
  }