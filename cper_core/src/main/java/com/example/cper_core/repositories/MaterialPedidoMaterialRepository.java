package com.example.cper_core.repositories;

import com.example.cper_core.entities.MaterialPedidoMaterial;
import com.example.cper_core.entities.MaterialPedidoMaterialId;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MaterialPedidoMaterialRepository extends JpaRepositoryWithExtendedFetch<MaterialPedidoMaterial, MaterialPedidoMaterialId>,
        JpaSpecificationExecutor<MaterialPedidoMaterial> {

  @EntityGraph(attributePaths = {
          "material",
          "pedidoMaterial"
  })
  @Query("SELECT mpm FROM MaterialPedidoMaterial mpm WHERE mpm.id = :id")
  @Override
  Optional<MaterialPedidoMaterial> findByIdExtended(MaterialPedidoMaterialId id);
}
