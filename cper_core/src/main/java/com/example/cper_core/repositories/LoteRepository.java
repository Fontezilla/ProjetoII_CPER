package com.example.cper_core.repositories;

import com.example.cper_core.entities.Lote;
import com.example.cper_core.entities.LoteId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoteRepository extends JpaRepository<Lote, LoteId>, JpaSpecificationExecutor<Lote> {

    @Query("""
        SELECT l FROM Lote l
        LEFT JOIN FETCH l.stock
        LEFT JOIN FETCH l.material
        WHERE l.id = :id
    """)
    Optional<Lote> findByIdWithAllRelations(@Param("id") LoteId id);
}
