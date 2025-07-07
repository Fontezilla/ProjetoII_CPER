package com.example.cper_core.repositories;

import com.example.cper_core.entities.CentroProducao;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CentroProducaoRepository extends JpaRepositoryWithExtendedFetch<CentroProducao, Integer>,
        JpaSpecificationExecutor<CentroProducao> {

  @EntityGraph(attributePaths = {
          "departamento",
          "endereco",
          "responsavel",
          "avarias",
          "inspecoes",
          "anomalias",
          "pedidosGeracao",
          "funcionarios"
  })
  @Query("SELECT c FROM CentroProducao c WHERE c.id = :id")
  @Override
  Optional<CentroProducao> findByIdExtended(Integer id);
}