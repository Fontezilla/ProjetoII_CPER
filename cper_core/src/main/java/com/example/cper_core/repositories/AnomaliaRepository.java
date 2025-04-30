package com.example.cper_core.repositories;

import com.example.cper_core.entities.Anomalia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AnomaliaRepository extends JpaRepository<Anomalia, Integer>, JpaSpecificationExecutor<Anomalia> {

    @Query("""
        SELECT a FROM Anomalia a
        LEFT JOIN FETCH a.notas
        LEFT JOIN FETCH a.inspecao
        LEFT JOIN FETCH a.centroProducao
        LEFT JOIN FETCH a.funcionario
        WHERE a.id = :idAnomalia
    """)
    Optional<Anomalia> findByIdWithAllRelations(@Param("idAnomalia") Integer idAnomalia);
}
