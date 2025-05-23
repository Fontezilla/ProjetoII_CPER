package com.example.cper_core.specifications;

import com.example.cper_core.dtos.centro_producao.CentroProducaoFiltroDto;
import com.example.cper_core.entities.CentroProducao;
import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class CentroProducaoSpecification {

    public static Specification<CentroProducao> filter(CentroProducaoFiltroDto filtro) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filtro.getId() != null) {
                predicates.add(cb.equal(root.get("id"), filtro.getId()));
            }
            if (filtro.getNome() != null && !filtro.getNome().isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("nome")), "%" + filtro.getNome().toLowerCase() + "%"));
            }
            if (filtro.getTipoEnergia() != null) {
                predicates.add(cb.equal(root.get("tipoEnergia"), filtro.getTipoEnergia().getId()));
            }
            if (filtro.getCapacidadeMax() != null) {
                predicates.add(cb.equal(root.get("capacidadeMax"), filtro.getCapacidadeMax()));
            }
            if (filtro.getCapacidadeAtual() != null) {
                predicates.add(cb.equal(root.get("capacidadeAtual"), filtro.getCapacidadeAtual()));
            }
            if (filtro.getDataInicioInicio() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("dataInicio"), filtro.getDataInicioInicio()));
            }
            if (filtro.getDataInicioFim() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("dataInicio"), filtro.getDataInicioFim()));
            }
            if (filtro.getCustoOperacional() != null) {
                predicates.add(cb.equal(root.get("custoOperacional"), filtro.getCustoOperacional()));
            }
            if (filtro.getEstado() != null) {
                predicates.add(cb.equal(root.get("estado"), filtro.getEstado().getId()));
            }
            if (filtro.getNPorta() != null && !filtro.getNPorta().isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("nPorta")), "%" + filtro.getNPorta().toLowerCase() + "%"));
            }
            if (filtro.getIsDeleted() != null) {
                predicates.add(cb.equal(root.get("isDeleted"), filtro.getIsDeleted()));
            }
            if (filtro.getIdsDepartamento() != null && !filtro.getIdsDepartamento().isEmpty()) {
                predicates.add(root.get("departamento").get("id").in(filtro.getIdsDepartamento()));
            }
            if (filtro.getIdsEndereco() != null && !filtro.getIdsEndereco().isEmpty()) {
                predicates.add(root.get("endereco").get("id").in(filtro.getIdsEndereco()));
            }
            if (filtro.getIdsFuncionario() != null && !filtro.getIdsFuncionario().isEmpty()) {
                predicates.add(root.get("funcionario").get("id").in(filtro.getIdsFuncionario()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}