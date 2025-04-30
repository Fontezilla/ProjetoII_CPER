package com.example.cper_core.repositories;

import com.example.cper_core.entities.MaterialPedidoMaterial;
import com.example.cper_core.entities.MaterialPedidoMaterialId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MaterialPedidoMaterialRepository extends JpaRepository<MaterialPedidoMaterial, MaterialPedidoMaterialId>, JpaSpecificationExecutor<MaterialPedidoMaterial> {

    @Query("""
        SELECT mpm FROM MaterialPedidoMaterial mpm
        LEFT JOIN FETCH mpm.pedidoMaterial
        LEFT JOIN FETCH mpm.material
        WHERE mpm.id = :id
    """)
    Optional<MaterialPedidoMaterial> findByIdWithAllRelations(@Param("id") MaterialPedidoMaterialId id);
}
