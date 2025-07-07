package com.example.cper_core.repositories;

import com.example.cper_core.entities.Nota;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface NotaRepository extends JpaRepositoryWithExtendedFetch<Nota, Integer>,
        JpaSpecificationExecutor<Nota> {

  @EntityGraph(attributePaths = {
          "inspecao",
          "anomalia",
          "avaria",
          "solicitacaoEnergetica"
  })
  @Query("SELECT n FROM Nota n WHERE n.id = :id")
  @Override
  Optional<Nota> findByIdExtended(Integer id);
}