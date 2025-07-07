package com.example.cper_core.repositories;

import com.example.cper_core.entities.PedidoMaterial;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PedidoMaterialRepository extends JpaRepositoryWithExtendedFetch<PedidoMaterial, Integer>,
        JpaSpecificationExecutor<PedidoMaterial> {

  @EntityGraph(attributePaths = {
          "materialPedidoMateriais", "avarias"
  })
  @Query("SELECT p FROM PedidoMaterial p WHERE p.id = :id")
  @Override
  Optional<PedidoMaterial> findByIdExtended(Integer id);
}