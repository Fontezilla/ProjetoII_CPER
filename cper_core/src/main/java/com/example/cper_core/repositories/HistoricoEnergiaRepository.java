package com.example.cper_core.repositories;

import com.example.cper_core.entities.HistoricoEnergia;
import com.example.cper_core.entities.PedidoGeracao;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface HistoricoEnergiaRepository extends JpaRepositoryWithExtendedFetch<HistoricoEnergia, Integer>,
        JpaSpecificationExecutor<HistoricoEnergia> {

  @EntityGraph(attributePaths = {
          "pedidoGeracao"
  })
  @Query("SELECT h FROM HistoricoEnergia h WHERE h.id = :id")
  @Override
  Optional<HistoricoEnergia> findByIdExtended(Integer id);

  Optional<HistoricoEnergia> findTopByPedidoGeracaoOrderByDataDesc(PedidoGeracao pedidoGeracao);
}