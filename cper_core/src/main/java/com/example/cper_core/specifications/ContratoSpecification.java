package com.example.cper_core.specifications;

import com.example.cper_core.dtos.contrato.ContratoFiltroDto;
import com.example.cper_core.entities.Contrato;
import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class ContratoSpecification {

    public static Specification<Contrato> filter(ContratoFiltroDto filtro) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filtro.getId() != null) {
                predicates.add(cb.equal(root.get("id"), filtro.getId()));
            }
            if (filtro.getTipoContrato() != null) {
                predicates.add(cb.equal(root.get("tipoContrato"), filtro.getTipoContrato().getId()));
            }
            if (filtro.getEstado() != null) {
                predicates.add(cb.equal(root.get("estado"), filtro.getEstado().getId()));
            }
            if (filtro.getDataInicioInicio() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("dataInicio"), filtro.getDataInicioInicio()));
            }
            if (filtro.getDataInicioFim() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("dataInicio"), filtro.getDataInicioFim()));
            }
            if (filtro.getDataFimInicio() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("dataFim"), filtro.getDataFimInicio()));
            }
            if (filtro.getDataFimFim() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("dataFim"), filtro.getDataFimFim()));
            }
            if (filtro.getQtdEnergiaMin() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("qtdEnergia"), filtro.getQtdEnergiaMin()));
            }
            if (filtro.getQtdEnergiaMax() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("qtdEnergia"), filtro.getQtdEnergiaMax()));
            }
            if (filtro.getQtdEnergiaHMin() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("qtdEnergiaH"), filtro.getQtdEnergiaHMin()));
            }
            if (filtro.getQtdEnergiaHMax() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("qtdEnergiaH"), filtro.getQtdEnergiaHMax()));
            }
            if (filtro.getPrazoPagamentoMin() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("prazoPagamento"), filtro.getPrazoPagamentoMin()));
            }
            if (filtro.getPrazoPagamentoMax() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("prazoPagamento"), filtro.getPrazoPagamentoMax()));
            }
            if (filtro.getMultaAtrasoMin() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("multaAtraso"), filtro.getMultaAtrasoMin()));
            }
            if (filtro.getMultaAtrasoMax() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("multaAtraso"), filtro.getMultaAtrasoMax()));
            }
            if (filtro.getIsDeleted() != null) {
                predicates.add(cb.equal(root.get("isDeleted"), filtro.getIsDeleted()));
            }
            if (filtro.getIdsFuncionario() != null && !filtro.getIdsFuncionario().isEmpty()) {
                predicates.add(root.get("funcionario").get("id").in(filtro.getIdsFuncionario()));
            }
            if (filtro.getIdsEndereco() != null && !filtro.getIdsEndereco().isEmpty()) {
                predicates.add(root.get("endereco").get("id").in(filtro.getIdsEndereco()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}