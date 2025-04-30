package com.example.cper_core.repositories;

import com.example.cper_core.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>, JpaSpecificationExecutor<Cliente> {

    @Query("""
        SELECT DISTINCT c FROM Cliente c
        LEFT JOIN FETCH c.perfilConsumos
        LEFT JOIN FETCH c.solicitacaoEnergeticas
        LEFT JOIN FETCH c.tickets
        LEFT JOIN FETCH c.respostas
        LEFT JOIN FETCH c.endereco
        WHERE c.id = :idCliente
    """)
    Optional<Cliente> findByIdWithAllRelations(@Param("idCliente") Integer idCliente);
}