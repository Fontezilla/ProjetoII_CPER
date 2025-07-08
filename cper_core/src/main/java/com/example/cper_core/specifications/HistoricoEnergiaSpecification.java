package com.example.cper_core.specifications;

import com.example.cper_core.dtos.historico_energia.HistoricoEnergiaFiltroDto;
import com.example.cper_core.entities.HistoricoEnergia;
import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class HistoricoEnergiaSpecification {

    public static Specification<HistoricoEnergia> filter(HistoricoEnergiaFiltroDto filtro) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filtro.getId() != null) {
                predicates.add(cb.equal(root.get("id"), filtro.getId()));
            }

            if (filtro.getDataIncio() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("data"), filtro.getDataIncio()));
            }

            if (filtro.getDataFIm() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("data"), filtro.getDataFIm()));
            }

            if (filtro.getEnergiaGeradaMin() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("energiaGerada"), filtro.getEnergiaGeradaMin()));
            }

            if (filtro.getEnergiaGeradaMax() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("energiaGerada"), filtro.getEnergiaGeradaMax()));
            }

            if (filtro.getEnergiaHoraMin() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("energiaHora"), filtro.getEnergiaHoraMin()));
            }

            if (filtro.getEnergiaHoraMax() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("energiaHora"), filtro.getEnergiaHoraMax()));
            }

            if (filtro.getIdsPedidosGeracao() != null && !filtro.getIdsPedidosGeracao().isEmpty()) {
                predicates.add(root.get("pedidoGeracao").get("id").in(filtro.getIdsPedidosGeracao()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}