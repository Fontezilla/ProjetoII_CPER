package com.example.cper_core.specifications;

import com.example.cper_core.dtos.avaria.AvariaFiltroDto;
import com.example.cper_core.entities.Avaria;
import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class AvariaSpecification {

    public static Specification<Avaria> filter(AvariaFiltroDto filtro) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filtro.getId() != null) {
                predicates.add(cb.equal(root.get("id"), filtro.getId()));
            }
            if (filtro.getTitulo() != null && !filtro.getTitulo().isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("titulo")), "%" + filtro.getTitulo().toLowerCase() + "%"));
            }
            if (filtro.getDescricao() != null && !filtro.getDescricao().isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("descricao")), "%" + filtro.getDescricao().toLowerCase() + "%"));
            }
            if (filtro.getDataInicioInicio() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("dataInicio"), filtro.getDataInicioInicio()));
            }
            if (filtro.getDataInicioFim() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("dataInicio"), filtro.getDataInicioFim()));
            }
            if (filtro.getGravidade() != null) {
                predicates.add(cb.equal(root.get("gravidade"), filtro.getGravidade().getId()));
            }
            if (filtro.getEstado() != null) {
                predicates.add(cb.equal(root.get("estado"), filtro.getEstado().getId()));
            }
            if (filtro.getImpactoProducaoMin() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("impactoProducao"), filtro.getImpactoProducaoMin()));
            }
            if (filtro.getImpactoProducaoMax() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("impactoProducao"), filtro.getImpactoProducaoMax()));
            }
            if (filtro.getImpactoPercentualMin() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("impactoPercentual"), filtro.getImpactoPercentualMin()));
            }
            if (filtro.getImpactoPercentualMax() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("impactoPercentual"), filtro.getImpactoPercentualMax()));
            }
            if (filtro.getIsDeleted() != null) {
                predicates.add(cb.equal(root.get("isDeleted"), filtro.getIsDeleted()));
            }
            if (filtro.getIdsCentroProducao() != null && !filtro.getIdsCentroProducao().isEmpty()) {
                predicates.add(root.get("centroProducao").get("id").in(filtro.getIdsCentroProducao()));
            }
            if (filtro.getIdsInspecao() != null && !filtro.getIdsInspecao().isEmpty()) {
                predicates.add(root.get("inspecao").get("id").in(filtro.getIdsInspecao()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}