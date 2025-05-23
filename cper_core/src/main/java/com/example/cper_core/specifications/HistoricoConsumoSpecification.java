package com.example.cper_core.specifications;

import com.example.cper_core.dtos.historico_consumo.HistoricoConsumoFiltroDto;
import com.example.cper_core.entities.HistoricoConsumo;
import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class HistoricoConsumoSpecification {

    public static Specification<HistoricoConsumo> filter(HistoricoConsumoFiltroDto filtro) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filtro.getId() != null) {
                predicates.add(cb.equal(root.get("id"), filtro.getId()));
            }
            if (filtro.getDataRegistoInicio() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("dataRegisto"), filtro.getDataRegistoInicio()));
            }
            if (filtro.getDataRegistoFim() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("dataRegisto"), filtro.getDataRegistoFim()));
            }
            if (filtro.getEnergiaTotalMin() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("energiaTotal"), filtro.getEnergiaTotalMin()));
            }
            if (filtro.getEnergiaTotalMax() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("energiaTotal"), filtro.getEnergiaTotalMax()));
            }
            if (filtro.getConsumoPorHoraMin() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("consumoPorHora"), filtro.getConsumoPorHoraMin()));
            }
            if (filtro.getConsumoPorHoraMax() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("consumoPorHora"), filtro.getConsumoPorHoraMax()));
            }
            if (filtro.getIsDeleted() != null) {
                predicates.add(cb.equal(root.get("isDeleted"), filtro.getIsDeleted()));
            }
            if (filtro.getIdsCliente() != null && !filtro.getIdsCliente().isEmpty()) {
                predicates.add(root.get("cliente").get("id").in(filtro.getIdsCliente()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}