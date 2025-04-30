package com.example.cper_core.repositories;

import com.example.cper_core.entities.Armazem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArmazemRepository extends JpaRepository<Armazem, Integer>, JpaSpecificationExecutor<Armazem> {

    @Query("""
        SELECT DISTINCT a FROM Armazem a
        LEFT JOIN FETCH a.stocks
        LEFT JOIN FETCH a.funcionarios
        LEFT JOIN FETCH a.departamento
        LEFT JOIN FETCH a.endereco
        LEFT JOIN FETCH a.responsavel
        WHERE a.id = :idArmazem
    """)
    Optional<Armazem> findByIdWithAllRelations(@Param("idArmazem") Integer idArmazem);
}
