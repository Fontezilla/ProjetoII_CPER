package com.example.cper_core.repositories;

import com.example.cper_core.entities.Anomalia;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AnomaliaRepository extends JpaRepositoryWithExtendedFetch<Anomalia, Integer>,
        JpaSpecificationExecutor<Anomalia> {

    @EntityGraph(attributePaths = {
            "funcionario", "centroProducao", "inspecao", "notas"
    })
    @Query("SELECT a FROM Anomalia a WHERE a.id = :id")
    @Override
    Optional<Anomalia> findByIdExtended(Integer id);
}
