package com.example.cper_core.specifications;

import com.example.cper_core.dtos.endereco.EnderecoFiltroDto;
import com.example.cper_core.entities.Endereco;
import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class EnderecoSpecification {

    public static Specification<Endereco> filter(EnderecoFiltroDto filtro) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filtro.getId() != null) {
                predicates.add(cb.equal(root.get("id"), filtro.getId()));
            }
            if (filtro.getRua() != null && !filtro.getRua().isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("rua")), "%" + filtro.getRua().toLowerCase() + "%"));
            }
            if (filtro.getIsDeleted() != null) {
                predicates.add(cb.equal(root.get("isDeleted"), filtro.getIsDeleted()));
            }
            if (filtro.getIdsLocalidade() != null && !filtro.getIdsLocalidade().isEmpty()) {
                predicates.add(root.get("localidade").get("id").in(filtro.getIdsLocalidade()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}