package com.example.cper_core.specifications;

import com.example.cper_core.dtos.resposta.RespostaFiltroDto;
import com.example.cper_core.entities.Resposta;
import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class RespostaSpecification {

    public static Specification<Resposta> filter(RespostaFiltroDto filtro) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filtro.getId() != null) {
                predicates.add(cb.equal(root.get("id"), filtro.getId()));
            }
            if (filtro.getCodigo() != null && !filtro.getCodigo().isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("codigo")), "%" + filtro.getCodigo().toLowerCase() + "%"));
            }
            if (filtro.getResposta() != null && !filtro.getResposta().isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("resposta")), "%" + filtro.getResposta().toLowerCase() + "%"));
            }
            if (filtro.getDataRespostaInicio() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("dataResposta"), filtro.getDataRespostaInicio()));
            }
            if (filtro.getDataRespostaFim() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("dataResposta"), filtro.getDataRespostaFim()));
            }
            if (filtro.getIsCliente() != null) {
                predicates.add(cb.equal(root.get("isCliente"), filtro.getIsCliente()));
            }
            if (filtro.getIsDeleted() != null) {
                predicates.add(cb.equal(root.get("isDeleted"), filtro.getIsDeleted()));
            }
            if (filtro.getIdsTicket() != null && !filtro.getIdsTicket().isEmpty()) {
                predicates.add(root.get("ticket").get("id").in(filtro.getIdsTicket()));
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