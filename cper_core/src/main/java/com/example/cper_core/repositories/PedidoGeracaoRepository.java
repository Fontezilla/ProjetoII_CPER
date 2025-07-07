package com.example.cper_core.repositories;

import com.example.cper_core.entities.PedidoGeracao;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PedidoGeracaoRepository extends JpaRepositoryWithExtendedFetch<PedidoGeracao, Integer>,
        JpaSpecificationExecutor<PedidoGeracao> {

  @EntityGraph(attributePaths = {
          "contrato", "centroProducao", "funcionario"
  })
  @Query("SELECT p FROM PedidoGeracao p WHERE p.id = :id")
  @Override
  Optional<PedidoGeracao> findByIdExtended(Integer id);
}