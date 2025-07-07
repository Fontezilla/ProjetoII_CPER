package com.example.cper_core.repositories;

import com.example.cper_core.entities.Departamento;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface DepartamentoRepository extends JpaRepositoryWithExtendedFetch<Departamento, Integer>,
        JpaSpecificationExecutor<Departamento> {

  @EntityGraph(attributePaths = {
          "centrosProducao",
          "equipas",
          "funcionarios",
          "armazens",
          "responsaveis"
  })
  @Query("SELECT d FROM Departamento d WHERE d.id = :id")
  @Override
  Optional<Departamento> findByIdExtended(Integer id);
}