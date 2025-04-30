package com.example.cper_core.repositories;

import com.example.cper_core.entities.SolicitacaoMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SolicitacaoMaterialRepository extends JpaRepository<SolicitacaoMaterial, Integer>, JpaSpecificationExecutor<SolicitacaoMaterial> {

    @Query("""
        SELECT DISTINCT s FROM SolicitacaoMaterial s
        LEFT JOIN FETCH s.materialSolicitacaomateriais
        WHERE s.id = :idSolicitacao
    """)
    Optional<SolicitacaoMaterial> findByIdWithAllRelations(@Param("idSolicitacao") Integer idSolicitacao);
}
