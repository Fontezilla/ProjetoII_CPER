package com.example.cper_core.specifications;

import com.example.cper_core.dtos.funcionario.FuncionarioFiltroDto;
import com.example.cper_core.entities.Funcionario;
import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioSpecification {

    public static Specification<Funcionario> filter(FuncionarioFiltroDto filtro) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filtro.getId() != null) {
                predicates.add(cb.equal(root.get("id"), filtro.getId()));
            }
            if (filtro.getNome() != null && !filtro.getNome().isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("nome")), "%" + filtro.getNome().toLowerCase() + "%"));
            }
            if (filtro.getNif() != null && !filtro.getNif().isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("nif")), "%" + filtro.getNif().toLowerCase() + "%"));
            }
            if (filtro.getEmail() != null && !filtro.getEmail().isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("email")), "%" + filtro.getEmail().toLowerCase() + "%"));
            }
            if (filtro.getTelefone() != null && !filtro.getTelefone().isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("telefone")), "%" + filtro.getTelefone().toLowerCase() + "%"));
            }
            if (filtro.getDataContratacaoInicio() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("dataContratacao"), filtro.getDataContratacaoInicio()));
            }
            if (filtro.getDataContratacaoFim() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("dataContratacao"), filtro.getDataContratacaoFim()));
            }
            if (filtro.getSalarioMin() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("salario"), filtro.getSalarioMin()));
            }
            if (filtro.getSalarioMax() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("salario"), filtro.getSalarioMax()));
            }
            if (filtro.getEstado() != null) {
                predicates.add(cb.equal(root.get("estado"), filtro.getEstado().getId()));
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

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}