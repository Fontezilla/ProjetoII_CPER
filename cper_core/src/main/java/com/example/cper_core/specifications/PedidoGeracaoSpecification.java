package com.example.cper_core.specifications;

import com.example.cper_core.dtos.pedido_geracao.PedidoGeracaoFiltroDto;
import com.example.cper_core.entities.PedidoGeracao;
import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class PedidoGeracaoSpecification {

    public static Specification<PedidoGeracao> filter(PedidoGeracaoFiltroDto filtro) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filtro.getId() != null) {
                predicates.add(cb.equal(root.get("id"), filtro.getId()));
            }
            if (filtro.getCodigo() != null && !filtro.getCodigo().isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("codigo")), "%" + filtro.getCodigo().toLowerCase() + "%"));
            }
            if (filtro.getDataCriacaoInicio() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("dataCriacao"), filtro.getDataCriacaoInicio()));
            }
            if (filtro.getDataCriacaoFim() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("dataCriacao"), filtro.getDataCriacaoFim()));
            }
            if (filtro.getDataPrevisaoInicio() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("dataPrevisao"), filtro.getDataPrevisaoInicio()));
            }
            if (filtro.getDataPrevisaoFim() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("dataPrevisao"), filtro.getDataPrevisaoFim()));
            }
            if (filtro.getQtdEnergiaMin() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("qtdEnergia"), filtro.getQtdEnergiaMin()));
            }
            if (filtro.getQtdEnergiaMax() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("qtdEnergia"), filtro.getQtdEnergiaMax()));
            }
            if (filtro.getTipoEnergia() != null) {
                predicates.add(cb.equal(root.get("tipoEnergia"), filtro.getTipoEnergia().getId()));
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
            if (filtro.getIdsContrato() != null && !filtro.getIdsContrato().isEmpty()) {
                predicates.add(root.get("contrato").get("id").in(filtro.getIdsContrato()));
            }
            if (filtro.getIdsCentroProducao() != null && !filtro.getIdsCentroProducao().isEmpty()) {
                predicates.add(root.get("centroProducao").get("id").in(filtro.getIdsCentroProducao()));
            }
            if (filtro.getIdsFuncionario() != null && !filtro.getIdsFuncionario().isEmpty()) {
                predicates.add(root.get("funcionario").get("id").in(filtro.getIdsFuncionario()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}