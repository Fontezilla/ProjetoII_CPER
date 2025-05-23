package com.example.cper_core.specifications;

import com.example.cper_core.dtos.armazem_lote.ArmazemLoteFiltroDto;
import com.example.cper_core.entities.ArmazemLote;
import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class ArmazemLoteSpecification {

    public static Specification<ArmazemLote> filter(ArmazemLoteFiltroDto filtro) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filtro.getQuantidadeArmazenadaMin() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("quantidadeArmazenada"), filtro.getQuantidadeArmazenadaMin()));
            }
            if (filtro.getQuantidadeArmazenadaMax() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("quantidadeArmazenada"), filtro.getQuantidadeArmazenadaMax()));
            }
            if (filtro.getIsDeleted() != null) {
                predicates.add(cb.equal(root.get("isDeleted"), filtro.getIsDeleted()));
            }
            if (filtro.getIdsArmazem() != null && !filtro.getIdsArmazem().isEmpty()) {
                predicates.add(root.get("armazem").get("id").in(filtro.getIdsArmazem()));
            }
            if (filtro.getIdsLote() != null && !filtro.getIdsLote().isEmpty()) {
                predicates.add(root.get("lote").get("id").in(filtro.getIdsLote()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}