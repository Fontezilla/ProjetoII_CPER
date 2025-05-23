package com.example.cper_core.specifications;

import com.example.cper_core.dtos.lote.LoteFiltroDto;
import com.example.cper_core.entities.Lote;
import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class LoteSpecification {

    public static Specification<Lote> filter(LoteFiltroDto filtro) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filtro.getId() != null) {
                predicates.add(cb.equal(root.get("id"), filtro.getId()));
            }
            if (filtro.getCodigoLote() != null && !filtro.getCodigoLote().isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("codigoLote")), "%" + filtro.getCodigoLote().toLowerCase() + "%"));
            }
            if (filtro.getDataProducaoInicio() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("dataProducao"), filtro.getDataProducaoInicio()));
            }
            if (filtro.getDataProducaoFim() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("dataProducao"), filtro.getDataProducaoFim()));
            }
            if (filtro.getDataValidadeInicio() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("dataValidade"), filtro.getDataValidadeInicio()));
            }
            if (filtro.getDataValidadeFim() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("dataValidade"), filtro.getDataValidadeFim()));
            }
            if (filtro.getQuantidadeTotalMin() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("quantidadeTotal"), filtro.getQuantidadeTotalMin()));
            }
            if (filtro.getQuantidadeTotalMax() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("quantidadeTotal"), filtro.getQuantidadeTotalMax()));
            }
            if (filtro.getQuantidadeDisponivelMin() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("quantidadeDisponivel"), filtro.getQuantidadeDisponivelMin()));
            }
            if (filtro.getQuantidadeDisponivelMax() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("quantidadeDisponivel"), filtro.getQuantidadeDisponivelMax()));
            }
            if (filtro.getIsDeleted() != null) {
                predicates.add(cb.equal(root.get("isDeleted"), filtro.getIsDeleted()));
            }
            if (filtro.getIdsMaterial() != null && !filtro.getIdsMaterial().isEmpty()) {
                predicates.add(root.get("material").get("id").in(filtro.getIdsMaterial()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}