package com.example.cper_core.repositories;

import com.example.cper_core.entities.PedidoGeracao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PedidoGeracaoRepository extends JpaRepository<PedidoGeracao, Integer>, JpaSpecificationExecutor<PedidoGeracao> {

    @Query("""
        SELECT DISTINCT p FROM PedidoGeracao p
        LEFT JOIN FETCH p.contrato
        LEFT JOIN FETCH p.centroProducao
        LEFT JOIN FETCH p.funcionario
        WHERE p.id = :idPedidoGeracao
    """)
    Optional<PedidoGeracao> findByIdWithAllRelations(@Param("idPedidoGeracao") Integer idPedidoGeracao);
}
