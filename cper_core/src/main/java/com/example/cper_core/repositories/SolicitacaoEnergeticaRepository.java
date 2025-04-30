package com.example.cper_core.repositories;

import com.example.cper_core.entities.SolicitacaoEnergetica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SolicitacaoEnergeticaRepository extends JpaRepository<SolicitacaoEnergetica, Integer>, JpaSpecificationExecutor<SolicitacaoEnergetica> {

    @Query("""
        SELECT DISTINCT s FROM SolicitacaoEnergetica s
        LEFT JOIN FETCH s.contrato
        LEFT JOIN FETCH s.cliente
        LEFT JOIN FETCH s.comentarios
        LEFT JOIN FETCH s.funcionarios
        WHERE s.id = :idSolicitacao
    """)
    Optional<SolicitacaoEnergetica> findByIdWithAllRelations(@Param("idSolicitacao") Integer idSolicitacao);
}
