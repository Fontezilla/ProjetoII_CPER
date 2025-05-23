package com.example.cper_core.specifications;

import com.example.cper_core.dtos.notificacao_destinario.NotificacaoDestinatarioFiltroDto;
import com.example.cper_core.entities.NotificacaoDestinatario;
import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class NotificacaoDestinatarioSpecification {

    public static Specification<NotificacaoDestinatario> filter(NotificacaoDestinatarioFiltroDto filtro) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filtro.getId() != null) {
                predicates.add(cb.equal(root.get("id"), filtro.getId()));
            }
            if (filtro.getIdNotificacao() != null) {
                predicates.add(cb.equal(root.get("notificacao").get("id"), filtro.getIdNotificacao()));
            }
            if (filtro.getIdFuncionario() != null) {
                predicates.add(cb.equal(root.get("funcionario").get("id"), filtro.getIdFuncionario()));
            }
            if (filtro.getIdCliente() != null) {
                predicates.add(cb.equal(root.get("cliente").get("id"), filtro.getIdCliente()));
            }
            if (filtro.getLida() != null) {
                predicates.add(cb.equal(root.get("lida"), filtro.getLida()));
            }
            if (filtro.getIsDeleted() != null) {
                predicates.add(cb.equal(root.get("isDeleted"), filtro.getIsDeleted()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}