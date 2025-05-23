package com.example.cper_core.specifications;

import com.example.cper_core.dtos.armazem.ArmazemFiltroDto;
import com.example.cper_core.entities.Armazem;
import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class ArmazemSpecification {

    public static Specification<Armazem> filter(ArmazemFiltroDto filtro) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filtro.getId() != null) {
                predicates.add(cb.equal(root.get("id"), filtro.getId()));
            }
            if (filtro.getNome() != null && !filtro.getNome().isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("nome")), "%" + filtro.getNome().toLowerCase() + "%"));
            }
            if (filtro.getCapacidadeTotal() != null) {
                predicates.add(cb.equal(root.get("capacidadeTotal"), filtro.getCapacidadeTotal()));
            }
            if (filtro.getCapacidadeOcupada() != null) {
                predicates.add(cb.equal(root.get("capacidadeOcupada"), filtro.getCapacidadeOcupada()));
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
