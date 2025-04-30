package com.example.cper_core.repositories;

import com.example.cper_core.entities.Inspecao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InspecaoRepository extends JpaRepository<Inspecao, Integer>, JpaSpecificationExecutor<Inspecao> {

    @Query("""
        SELECT DISTINCT i FROM Inspecao i
        LEFT JOIN FETCH i.anomalia
        LEFT JOIN FETCH i.notas
        LEFT JOIN FETCH i.avarias
        LEFT JOIN FETCH i.centroProducao
        LEFT JOIN FETCH i.equipas
        WHERE i.id = :idInspecao
    """)
    Optional<Inspecao> findByIdWithAllRelations(@Param("idInspecao") Integer idInspecao);
}
