package com.example.cper_core.repositories;

import com.example.cper_core.entities.Contrato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContratoRepository extends JpaRepository<Contrato, Integer>, JpaSpecificationExecutor<Contrato> {

    @Query("""
        SELECT DISTINCT c FROM Contrato c
        LEFT JOIN FETCH c.solicitacaoEnergetica
        LEFT JOIN FETCH c.funcionario
        LEFT JOIN FETCH c.endereco
        LEFT JOIN FETCH c.pedidosGeracao
        LEFT JOIN FETCH c.faturas
        WHERE c.id = :idContrato
    """)
    Optional<Contrato> findByIdWithAllRelations(@Param("idContrato") Integer idContrato);
}
