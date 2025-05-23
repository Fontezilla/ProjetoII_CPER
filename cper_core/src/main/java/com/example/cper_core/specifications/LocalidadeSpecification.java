package com.example.cper_core.specifications;

import com.example.cper_core.dtos.localidade.LocalidadeFiltroDto;
import com.example.cper_core.entities.Localidade;
import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class LocalidadeSpecification {

    public static Specification<Localidade> filter(LocalidadeFiltroDto filtro) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filtro.getId() != null) {
                predicates.add(cb.equal(root.get("id"), filtro.getId()));
            }
            if (filtro.getNome() != null && !filtro.getNome().isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("nome")), "%" + filtro.getNome().toLowerCase() + "%"));
            }
            if (filtro.getCodigoPostal() != null && !filtro.getCodigoPostal().isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("codigoPostal")), "%" + filtro.getCodigoPostal().toLowerCase() + "%"));
            }
            if (filtro.getDistrito() != null && !filtro.getDistrito().isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("distrito")), "%" + filtro.getDistrito().toLowerCase() + "%"));
            }
            if (filtro.getPais() != null && !filtro.getPais().isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("pais")), "%" + filtro.getPais().toLowerCase() + "%"));
            }
            if (filtro.getIsDeleted() != null) {
                predicates.add(cb.equal(root.get("isDeleted"), filtro.getIsDeleted()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}