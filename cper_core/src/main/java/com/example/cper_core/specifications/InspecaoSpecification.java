package com.example.cper_core.specifications;

import com.example.cper_core.dtos.inspecao.InspecaoFiltroDto;
import com.example.cper_core.entities.Inspecao;
import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class InspecaoSpecification {

    public static Specification<Inspecao> filter(InspecaoFiltroDto filtro) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filtro.getId() != null) {
                predicates.add(cb.equal(root.get("id"), filtro.getId()));
            }
            if (filtro.getCodigo() != null && !filtro.getCodigo().isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("codigo")), "%" + filtro.getCodigo().toLowerCase() + "%"));
            }
            if (filtro.getDescricao() != null && !filtro.getDescricao().isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("descricao")), "%" + filtro.getDescricao().toLowerCase() + "%"));
            }
            if (filtro.getDataInicio() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("dataInicio"), filtro.getDataInicio()));
            }
            if (filtro.getDataFim() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("dataFim"), filtro.getDataFim()));
            }
            if (filtro.getTipo() != null) {
                predicates.add(cb.equal(root.get("tipo"), filtro.getTipo().getId()));
            }
            if (filtro.getEstado() != null) {
                predicates.add(cb.equal(root.get("estado"), filtro.getEstado().getId()));
            }
            if (filtro.getAreaInspecionada() != null && !filtro.getAreaInspecionada().isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("areaInspecionada")), "%" + filtro.getAreaInspecionada().toLowerCase() + "%"));
            }
            if (filtro.getResultados() != null && !filtro.getResultados().isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("resultados")), "%" + filtro.getResultados().toLowerCase() + "%"));
            }
            if (filtro.getIsDeleted() != null) {
                predicates.add(cb.equal(root.get("isDeleted"), filtro.getIsDeleted()));
            }
            if (filtro.getIdsCentroProducao() != null && !filtro.getIdsCentroProducao().isEmpty()) {
                predicates.add(root.get("centroProducao").get("id").in(filtro.getIdsCentroProducao()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
