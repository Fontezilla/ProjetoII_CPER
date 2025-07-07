package com.example.cper_core.repositories;

import com.example.cper_core.entities.Contrato;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ContratoRepository extends JpaRepositoryWithExtendedFetch<Contrato, Integer>,
        JpaSpecificationExecutor<Contrato> {

  @EntityGraph(attributePaths = {
          "funcionario",
          "endereco",
          "solicitacaoEnergetica",
          "faturas",
          "pedidosGeracao"
  })
  @Query("SELECT c FROM Contrato c WHERE c.id = :id")
  @Override
  Optional<Contrato> findByIdExtended(Integer id);
}
