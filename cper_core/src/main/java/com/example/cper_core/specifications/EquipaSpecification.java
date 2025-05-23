package com.example.cper_core.specifications;

import com.example.cper_core.dtos.equipa.EquipaFiltroDto;
import com.example.cper_core.entities.Equipa;
import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class EquipaSpecification {

    public static Specification<Equipa> filter(EquipaFiltroDto filtro) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filtro.getId() != null) {
                predicates.add(cb.equal(root.get("id"), filtro.getId()));
            }
            if (filtro.getNome() != null && !filtro.getNome().isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("nome")), "%" + filtro.getNome().toLowerCase() + "%"));
            }
            if (filtro.getAreaAtuacao() != null && !filtro.getAreaAtuacao().isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("areaAtuacao")), "%" + filtro.getAreaAtuacao().toLowerCase() + "%"));
            }
            if (filtro.getDataCriacaoInicio() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("dataCriacao"), filtro.getDataCriacaoInicio()));
            }
            if (filtro.getDataCriacaoFim() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("dataCriacao"), filtro.getDataCriacaoFim()));
            }
            if (filtro.getIsDeleted() != null) {
                predicates.add(cb.equal(root.get("isDeleted"), filtro.getIsDeleted()));
            }
            if (filtro.getIdsDepartamento() != null && !filtro.getIdsDepartamento().isEmpty()) {
                predicates.add(root.get("departamento").get("id").in(filtro.getIdsDepartamento()));
            }
            if (filtro.getIdsFuncionario() != null && !filtro.getIdsFuncionario().isEmpty()) {
                predicates.add(root.get("funcionario").get("id").in(filtro.getIdsFuncionario()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
