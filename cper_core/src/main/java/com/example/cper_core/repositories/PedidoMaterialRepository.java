package com.example.cper_core.repositories;

import com.example.cper_core.entities.PedidoMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PedidoMaterialRepository extends JpaRepository<PedidoMaterial, Integer>, JpaSpecificationExecutor<PedidoMaterial> {

    @Query("""
        SELECT DISTINCT p FROM PedidoMaterial p
        LEFT JOIN FETCH p.materialPedidomateriais
        LEFT JOIN FETCH p.avarias
        WHERE p.id = :idPedidoMaterial
    """)
    Optional<PedidoMaterial> findByIdWithAllRelations(@Param("idPedidoMaterial") Integer idPedidoMaterial);
}
