package com.example.cper_core.repositories;

import com.example.cper_core.entities.Equipa;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface EquipaRepository extends JpaRepositoryWithExtendedFetch<Equipa, Integer>,
        JpaSpecificationExecutor<Equipa> {

    @EntityGraph(attributePaths = {
            "departamento",
            "funcionario",
            "funcionarios",
            "avarias",
            "inspecoes"
    })
    @Query("SELECT e FROM Equipa e WHERE e.id = :id")
    @Override
    Optional<Equipa> findByIdExtended(Integer id);
}
