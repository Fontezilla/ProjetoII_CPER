package com.example.cper_core.repositories;

import com.example.cper_core.entities.Equipa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EquipaRepository extends JpaRepository<Equipa, Integer>, JpaSpecificationExecutor<Equipa> {

    @Query("""
        SELECT DISTINCT e FROM Equipa e
        LEFT JOIN FETCH e.departamento
        LEFT JOIN FETCH e.funcionario
        LEFT JOIN FETCH e.funcionarios
        LEFT JOIN FETCH e.avarias
        LEFT JOIN FETCH e.inspecoes
        WHERE e.id = :idEquipa
    """)
    Optional<Equipa> findByIdWithAllRelations(@Param("idEquipa") Integer idEquipa);
}