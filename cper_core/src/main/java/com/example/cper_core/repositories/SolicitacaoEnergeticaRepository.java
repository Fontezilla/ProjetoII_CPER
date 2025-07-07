package com.example.cper_core.repositories;

import com.example.cper_core.entities.SolicitacaoEnergetica;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface SolicitacaoEnergeticaRepository extends JpaRepositoryWithExtendedFetch<SolicitacaoEnergetica, Integer>,
        JpaSpecificationExecutor<SolicitacaoEnergetica> {

  @EntityGraph(attributePaths = {
          "cliente", "contrato", "notas", "funcionarios"
  })
  @Query("SELECT s FROM SolicitacaoEnergetica s WHERE s.id = :id")
  @Override
  Optional<SolicitacaoEnergetica> findByIdExtended(Integer id);
}