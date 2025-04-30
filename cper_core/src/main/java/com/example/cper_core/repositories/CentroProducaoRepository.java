package com.example.cper_core.repositories;

import com.example.cper_core.entities.CentroProducao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CentroProducaoRepository extends JpaRepository<CentroProducao, Integer>, JpaSpecificationExecutor<CentroProducao> {

    @Query("""
        SELECT DISTINCT c FROM CentroProducao c
        LEFT JOIN FETCH c.endereco
        LEFT JOIN FETCH c.departamento
        LEFT JOIN FETCH c.funcionarios
        LEFT JOIN FETCH c.inspecoes
        LEFT JOIN FETCH c.anomalias
        LEFT JOIN FETCH c.avarias
        LEFT JOIN FETCH c.pedidosGeracao
        WHERE c.id = :idCentro
    """)
    Optional<CentroProducao> findByIdWithAllRelations(@Param("idCentro") Integer idCentro);
}