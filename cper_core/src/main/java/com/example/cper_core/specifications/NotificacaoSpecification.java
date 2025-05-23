package com.example.cper_core.specifications;

import com.example.cper_core.dtos.notificacao.NotificacaoFiltroDto;
import com.example.cper_core.entities.Notificacao;
import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class NotificacaoSpecification {

    public static Specification<Notificacao> filter(NotificacaoFiltroDto filtro) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filtro.getId() != null) {
                predicates.add(cb.equal(root.get("id"), filtro.getId()));
            }
            if (filtro.getTitulo() != null && !filtro.getTitulo().isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("titulo")), "%" + filtro.getTitulo().toLowerCase() + "%"));
            }
            if (filtro.getMensagem() != null && !filtro.getMensagem().isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("mensagem")), "%" + filtro.getMensagem().toLowerCase() + "%"));
            }
            if (filtro.getDataCriacaoInicio() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("dataCriacao"), filtro.getDataCriacaoInicio()));
            }
            if (filtro.getDataCriacaoFim() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("dataCriacao"), filtro.getDataCriacaoFim()));
            }
            if (filtro.getTipoDestinatario() != null && !filtro.getTipoDestinatario().isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("tipoDestinatario")), "%" + filtro.getTipoDestinatario().toLowerCase() + "%"));
            }
            if (filtro.getIsDeleted() != null) {
                predicates.add(cb.equal(root.get("isDeleted"), filtro.getIsDeleted()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}