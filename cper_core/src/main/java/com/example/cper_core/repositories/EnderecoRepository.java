package com.example.cper_core.repositories;

import com.example.cper_core.entities.Endereco;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface EnderecoRepository extends JpaRepositoryWithExtendedFetch<Endereco, Integer>,
        JpaSpecificationExecutor<Endereco> {

  @EntityGraph(attributePaths = {
          "localidade",
          "clientes",
          "funcionarios",
          "armazens",
          "centrosProducao",
          "contratos"
  })
  @Query("SELECT e FROM Endereco e WHERE e.id = :id")
  @Override
  Optional<Endereco> findByIdExtended(Integer id);

  @EntityGraph(attributePaths = {
          "localidade"
  })
  @Query("SELECT e FROM Endereco e WHERE LOWER(TRIM(e.rua)) = LOWER(TRIM(:rua)) AND e.localidade.id = :localidadeId")
  Optional<Endereco> findByRuaIgnoreCaseAndLocalidadeId(String rua, Integer localidadeId);
}
