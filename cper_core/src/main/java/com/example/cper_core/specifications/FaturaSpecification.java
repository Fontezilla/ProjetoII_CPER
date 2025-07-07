package com.example.cper_core.specifications;

import com.example.cper_core.dtos.fatura.FaturaFiltroDto;
import com.example.cper_core.entities.Fatura;
import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class FaturaSpecification {

    public static Specification<Fatura> filter(FaturaFiltroDto filtro) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filtro.getId() != null) {
                predicates.add(cb.equal(root.get("id"), filtro.getId()));
            }
            if (filtro.getCodigo() != null && !filtro.getCodigo().isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("codigo")), "%" + filtro.getCodigo().toLowerCase() + "%"));
            }

            if (filtro.getDataEmissaoInicio() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("dataEmissao"), filtro.getDataEmissaoInicio()));
            }
            if (filtro.getDataEmissaoFim() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("dataEmissao"), filtro.getDataEmissaoFim()));
            }
            if (filtro.getDataVencimentoInicio() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("dataVencimento"), filtro.getDataVencimentoInicio()));
            }
            if (filtro.getDataVencimentoFim() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("dataVencimento"), filtro.getDataVencimentoFim()));
            }
            if (filtro.getVElectricidadeMin() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("vElectricidade"), filtro.getVElectricidadeMin()));
            }
            if (filtro.getVElectricidadeMax() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("vElectricidade"), filtro.getVElectricidadeMax()));
            }
            if (filtro.getVMultaMin() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("vMulta"), filtro.getVMultaMin()));
            }
            if (filtro.getVMultaMax() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("vMulta"), filtro.getVMultaMax()));
            }
            if (filtro.getQtdEnergiaMin() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("qtdEnergia"), filtro.getQtdEnergiaMin()));
            }
            if (filtro.getQtdEnergiaMax() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("qtdEnergia"), filtro.getQtdEnergiaMax()));
            }
            if (filtro.getEstado() != null) {
                predicates.add(cb.equal(root.get("estado"), filtro.getEstado()));
            }
            if (filtro.getTaxa() != null) {
                predicates.add(cb.equal(root.get("taxa"), filtro.getTaxa()));
            }
            if (filtro.getIsDeleted() != null) {
                predicates.add(cb.equal(root.get("isDeleted"), filtro.getIsDeleted()));
            }
            if (filtro.getIdsContrato() != null && !filtro.getIdsContrato().isEmpty()) {
                predicates.add(root.get("contrato").get("id").in(filtro.getIdsContrato()));
            }
            if (filtro.getIdsFuncionario() != null && !filtro.getIdsFuncionario().isEmpty()) {
                predicates.add(root.get("funcionario").get("id").in(filtro.getIdsFuncionario()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
