package com.example.cper_core.repositories;

import com.example.cper_core.entities.Ticket;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TicketRepository extends JpaRepositoryWithExtendedFetch<Ticket, Integer>,
        JpaSpecificationExecutor<Ticket> {

    @EntityGraph(attributePaths = {
            "funcionario",
            "cliente",
            "respostas"
    })
    @Query("SELECT t FROM Ticket t WHERE t.id = :id")
    @Override
    Optional<Ticket> findByIdExtended(Integer id);

    @Query("""
        SELECT COUNT(t) > 0 FROM Ticket t
        WHERE t.id = :ticketId AND t.cliente.id = :idCliente
    """)
    boolean isTicketOwnedByClient(@Param("ticketId") Integer ticketId, @Param("idCliente") Integer idCliente);

}