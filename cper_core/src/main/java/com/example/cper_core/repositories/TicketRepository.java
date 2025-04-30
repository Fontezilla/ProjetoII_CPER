package com.example.cper_core.repositories;

import com.example.cper_core.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer>, JpaSpecificationExecutor<Ticket> {

    @Query("""
        SELECT DISTINCT t FROM Ticket t
        LEFT JOIN FETCH t.respostas
        LEFT JOIN FETCH t.funcionario
        LEFT JOIN FETCH t.cliente
        WHERE t.id = :idTicket
    """)
    Optional<Ticket> findByIdWithAllRelations(@Param("idTicket") Integer idTicket);
}
