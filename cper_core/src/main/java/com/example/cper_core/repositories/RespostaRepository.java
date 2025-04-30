package com.example.cper_core.repositories;

import com.example.cper_core.entities.Resposta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RespostaRepository extends JpaRepository<Resposta, Integer>, JpaSpecificationExecutor<Resposta> {

    @Query("""
        SELECT DISTINCT r FROM Resposta r
        LEFT JOIN FETCH r.ticket
        LEFT JOIN FETCH r.funcionario
        LEFT JOIN FETCH r.cliente
        WHERE r.id = :idResposta
    """)
    Optional<Resposta> findByIdWithAllRelations(@Param("idResposta") Integer idResposta);
}
