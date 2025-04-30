package com.example.cper_core.repositories;

import com.example.cper_core.entities.Nota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NotaRepository extends JpaRepository<Nota, Integer>, JpaSpecificationExecutor<Nota> {

    @Query("""
        SELECT DISTINCT n FROM Nota n
        LEFT JOIN FETCH n.inspecao
        LEFT JOIN FETCH n.anomalia
        LEFT JOIN FETCH n.avaria
        WHERE n.id = :idNota
    """)
    Optional<Nota> findByIdWithAllRelations(@Param("idNota") Integer idNota);
}
