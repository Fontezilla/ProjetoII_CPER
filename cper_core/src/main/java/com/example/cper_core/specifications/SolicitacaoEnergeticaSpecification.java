package com.example.cper_core.specifications;

import com.example.cper_core.dtos.solicitacao_energetica.SolicitacaoEnergeticaFiltroDto;
import com.example.cper_core.entities.SolicitacaoEnergetica;
import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class SolicitacaoEnergeticaSpecification {

    public static Specification<SolicitacaoEnergetica> filter(SolicitacaoEnergeticaFiltroDto filtro) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filtro.getId() != null) {
                predicates.add(cb.equal(root.get("id"), filtro.getId()));
            }
            if (filtro.getDataSolicitacaoInicio() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("dataSolicitacao"), filtro.getDataSolicitacaoInicio()));
            }
            if (filtro.getDataSolicitacaoFim() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("dataSolicitacao"), filtro.getDataSolicitacaoFim()));
            }
            if (filtro.getTipoEnergia() != null) {
                predicates.add(cb.equal(root.get("tipoEnergia"), filtro.getTipoEnergia().getId()));
            }
            if (filtro.getQtdSolicitadaMin() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("qtdSolicitada"), filtro.getQtdSolicitadaMin()));
            }
            if (filtro.getQtdSolicitadaMax() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("qtdSolicitada"), filtro.getQtdSolicitadaMax()));
            }
            if (filtro.getPrioridade() != null) {
                predicates.add(cb.equal(root.get("prioridade"), filtro.getPrioridade().getId()));
            }
            if (filtro.getEstado() != null) {
                predicates.add(cb.equal(root.get("estado"), filtro.getEstado().getId()));
            }
            if (filtro.getIsDeleted() != null) {
                predicates.add(cb.equal(root.get("isDeleted"), filtro.getIsDeleted()));
            }
            if (filtro.getIdsCliente() != null && !filtro.getIdsCliente().isEmpty()) {
                predicates.add(root.get("cliente").get("id").in(filtro.getIdsCliente()));
            }
            if (filtro.getIdsContrato() != null && !filtro.getIdsContrato().isEmpty()) {
                predicates.add(root.get("contrato").get("id").in(filtro.getIdsContrato()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
