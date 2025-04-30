package com.example.cper_core.repositories;

import com.example.cper_core.entities.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Integer>, JpaSpecificationExecutor<Material> {

    @Query("""
        SELECT DISTINCT m FROM Material m
        LEFT JOIN FETCH m.lotes
        LEFT JOIN FETCH m.materialSolicitacaomateriais
        LEFT JOIN FETCH m.materialPedidomateriais
        WHERE m.id = :idMaterial
    """)
    Optional<Material> findByIdWithAllRelations(@Param("idMaterial") Integer idMaterial);
}
