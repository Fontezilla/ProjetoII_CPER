package com.example.cper_core.repositories;

import com.example.cper_core.entities.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Integer>, JpaSpecificationExecutor<Comentario> {

    @Query("""
        SELECT c FROM Comentario c
        LEFT JOIN FETCH c.solicitacaoEnergetica
        LEFT JOIN FETCH c.funcionario
        WHERE c.id = :idComentario
    """)
    Optional<Comentario> findByIdWithAllRelations(@Param("idComentario") Integer idComentario);
}
