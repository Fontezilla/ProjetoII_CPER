package com.example.cper_core.repositories;

import com.example.cper_core.entities.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StockRepository extends JpaRepository<Stock, Integer>, JpaSpecificationExecutor<Stock> {

    @Query("""
        SELECT DISTINCT s FROM Stock s
        LEFT JOIN FETCH s.lotes
        LEFT JOIN FETCH s.armazems
        WHERE s.id = :idStock
    """)
    Optional<Stock> findByIdWithAllRelations(@Param("idStock") Integer idStock);
}
