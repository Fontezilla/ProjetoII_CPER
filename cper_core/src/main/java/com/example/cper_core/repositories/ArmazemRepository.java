package com.example.cper_core.repositories;

import com.example.cper_core.entities.Armazem;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ArmazemRepository extends JpaRepositoryWithExtendedFetch<Armazem, Integer>,
        JpaSpecificationExecutor<Armazem> {

    @EntityGraph(attributePaths = {
            "departamento",
            "endereco",
            "responsavel",
            "lotes",
            "funcionarios"
    })
    @Query("SELECT a FROM Armazem a WHERE a.id = :id")
    @Override
    Optional<Armazem> findByIdExtended(Integer id);
}