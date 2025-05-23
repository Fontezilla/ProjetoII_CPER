package com.example.cper_core.specifications;

import com.example.cper_core.dtos.ticket.TicketFiltroDto;
import com.example.cper_core.entities.Ticket;
import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class TicketSpecification {

    public static Specification<Ticket> filter(TicketFiltroDto filtro) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filtro.getId() != null) {
                predicates.add(cb.equal(root.get("id"), filtro.getId()));
            }
            if (filtro.getDataIniInicio() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("dataIni"), filtro.getDataIniInicio()));
            }
            if (filtro.getDataIniFim() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("dataIni"), filtro.getDataIniFim()));
            }
            if (filtro.getDataFimInicio() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("dataFim"), filtro.getDataFimInicio()));
            }
            if (filtro.getDataFimFim() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("dataFim"), filtro.getDataFimFim()));
            }
            if (filtro.getDescricao() != null && !filtro.getDescricao().isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("descricao")), "%" + filtro.getDescricao().toLowerCase() + "%"));
            }
            if (filtro.getComentario() != null && !filtro.getComentario().isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("comentario")), "%" + filtro.getComentario().toLowerCase() + "%"));
            }
            if (filtro.getTipoTicket() != null) {
                predicates.add(cb.equal(root.get("tipoTicket"), filtro.getTipoTicket()));
            }
            if (filtro.getPrioridade() != null) {
                predicates.add(cb.equal(root.get("prioridade"), filtro.getPrioridade()));
            }
            if (filtro.getEstado() != null) {
                predicates.add(cb.equal(root.get("estado"), filtro.getEstado()));
            }
            if (filtro.getIsClosed() != null) {
                predicates.add(cb.equal(root.get("isClosed"), filtro.getIsClosed()));
            }
            if (filtro.getIsDeleted() != null) {
                predicates.add(cb.equal(root.get("isDeleted"), filtro.getIsDeleted()));
            }
            if (filtro.getIdsFuncionario() != null && !filtro.getIdsFuncionario().isEmpty()) {
                predicates.add(root.get("funcionario").get("id").in(filtro.getIdsFuncionario()));
            }
            if (filtro.getIdsCliente() != null && !filtro.getIdsCliente().isEmpty()) {
                predicates.add(root.get("cliente").get("id").in(filtro.getIdsCliente()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}