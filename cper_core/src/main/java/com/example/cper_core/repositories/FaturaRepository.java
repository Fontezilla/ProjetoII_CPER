package com.example.cper_core.repositories;

import com.example.cper_core.entities.Fatura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FaturaRepository extends JpaRepository<Fatura, Integer>, JpaSpecificationExecutor<Fatura> {

    @Query("""
        SELECT f FROM Fatura f
        LEFT JOIN FETCH f.contrato
        LEFT JOIN FETCH f.funcionario
        WHERE f.id = :idFatura
    """)
    Optional<Fatura> findByIdWithAllRelations(@Param("idFatura") Integer idFatura);
}
