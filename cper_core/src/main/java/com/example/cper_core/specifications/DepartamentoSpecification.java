package com.example.cper_core.specifications;

import com.example.cper_core.dtos.departamento.DepartamentoFiltroDto;
import com.example.cper_core.entities.Departamento;
import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class DepartamentoSpecification {

    public static Specification<Departamento> filter(DepartamentoFiltroDto filtro) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filtro.getId() != null) {
                predicates.add(cb.equal(root.get("id"), filtro.getId()));
            }
            if (filtro.getCodigo() != null && !filtro.getCodigo().isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("codigo")), "%" + filtro.getCodigo().toLowerCase() + "%"));
            }
            if (filtro.getNome() != null && !filtro.getNome().isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("nome")), "%" + filtro.getNome().toLowerCase() + "%"));
            }
            if (filtro.getSetor() != null) {
                predicates.add(cb.equal(root.get("setor"), filtro.getSetor().getId()));
            }
            if (filtro.getDataCriacaoInicio() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("dataCriacao"), filtro.getDataCriacaoInicio()));
            }
            if (filtro.getDataCriacaoFim() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("dataCriacao"), filtro.getDataCriacaoFim()));
            }
            if (filtro.getNumFuncionariosMin() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("numFuncionarios"), filtro.getNumFuncionariosMin()));
            }
            if (filtro.getNumFuncionariosMax() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("numFuncionarios"), filtro.getNumFuncionariosMax()));
            }
            if (filtro.getIsDeleted() != null) {
                predicates.add(cb.equal(root.get("isDeleted"), filtro.getIsDeleted()));
            }
            if (filtro.getOrcamentoMin() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("orcamento"), filtro.getOrcamentoMin()));
            }
            if (filtro.getOrcamentoMax() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("orcamento"), filtro.getOrcamentoMax()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
