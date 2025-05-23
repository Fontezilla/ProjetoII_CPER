package com.example.cper_core.specifications;

import com.example.cper_core.dtos.nota.NotaFiltroDto;
import com.example.cper_core.entities.Nota;
import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class NotaSpecification {

    public static Specification<Nota> filter(NotaFiltroDto filtro) {
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
            if (filtro.getDataCriacaoInicio() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("dataCriacao"), filtro.getDataCriacaoInicio()));
            }
            if (filtro.getDataCriacaoFim() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("dataCriacao"), filtro.getDataCriacaoFim()));
            }
            if (filtro.getPrioridade() != null) {
                predicates.add(cb.equal(root.get("prioridade"), filtro.getPrioridade().getId()));
            }
            if (filtro.getIsDeleted() != null) {
                predicates.add(cb.equal(root.get("isDeleted"), filtro.getIsDeleted()));
            }
            if (filtro.getIdsInspecao() != null && !filtro.getIdsInspecao().isEmpty()) {
                predicates.add(root.get("inspecao").get("id").in(filtro.getIdsInspecao()));
            }
            if (filtro.getIdsAnomalia() != null && !filtro.getIdsAnomalia().isEmpty()) {
                predicates.add(root.get("anomalia").get("id").in(filtro.getIdsAnomalia()));
            }
            if (filtro.getIdsAvaria() != null && !filtro.getIdsAvaria().isEmpty()) {
                predicates.add(root.get("avaria").get("id").in(filtro.getIdsAvaria()));
            }
            if (filtro.getIdsSolicitacaoEnergetica() != null && !filtro.getIdsSolicitacaoEnergetica().isEmpty()) {
                predicates.add(root.get("solicitacaoEnergetica").get("id").in(filtro.getIdsSolicitacaoEnergetica()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}