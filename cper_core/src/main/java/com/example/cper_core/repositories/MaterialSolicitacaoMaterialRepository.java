package com.example.cper_core.repositories;

import com.example.cper_core.entities.MaterialSolicitacaoMaterial;
import com.example.cper_core.entities.MaterialSolicitacaoMaterialId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MaterialSolicitacaoMaterialRepository extends JpaRepository<MaterialSolicitacaoMaterial, MaterialSolicitacaoMaterialId>, JpaSpecificationExecutor<MaterialSolicitacaoMaterial> {

    @Query("""
        SELECT msm FROM MaterialSolicitacaoMaterial msm
        LEFT JOIN FETCH msm.material
        LEFT JOIN FETCH msm.solicitacaoMaterial
        WHERE msm.id = :id
    """)
    Optional<MaterialSolicitacaoMaterial> findByIdWithAllRelations(@Param("id") MaterialSolicitacaoMaterialId id);
}
