package com.example.cper_core.repositories;

import com.example.cper_core.entities.ArmazemLote;
import com.example.cper_core.entities.MaterialPedidoMaterial;
import com.example.cper_core.entities.MaterialPedidoMaterialId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MaterialPedidoMaterialRepository extends JpaRepository<MaterialPedidoMaterial, MaterialPedidoMaterialId>, JpaSpecificationExecutor<MaterialPedidoMaterial> {
  }