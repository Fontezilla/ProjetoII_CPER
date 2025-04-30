package com.example.cper_core.repositories;

import com.example.cper_core.entities.Avaria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AvariaRepository extends JpaRepository<Avaria, Integer>, JpaSpecificationExecutor<Avaria> {

    @Query("""
        SELECT DISTINCT a FROM Avaria a
        LEFT JOIN FETCH a.notas
        LEFT JOIN FETCH a.inspecao
        LEFT JOIN FETCH a.centroProducao
        LEFT JOIN FETCH a.equipas
        LEFT JOIN FETCH a.pedidoMateriais
        WHERE a.id = :idAvaria
    """)
    Optional<Avaria> findByIdWithAllRelations(@Param("idAvaria") Integer idAvaria);
}
