package com.example.cper_core.repositories;

import com.example.cper_core.entities.Funcionario;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface FuncionarioRepository extends JpaRepositoryWithExtendedFetch<Funcionario, Integer>,
        JpaSpecificationExecutor<Funcionario> {

  @EntityGraph(attributePaths = {
          "departamento",
          "endereco",
          "armazens",
          "centrosProducao",
          "departamentos",
          "equipas"
  })
  @Query("SELECT f FROM Funcionario f WHERE f.id = :id")
  @Override
  Optional<Funcionario> findByIdExtended(Integer id);

  Optional<Funcionario> findByEmail(String email);
}
