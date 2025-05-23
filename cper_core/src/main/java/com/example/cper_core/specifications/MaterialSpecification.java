package com.example.cper_core.specifications;

import com.example.cper_core.dtos.material.MaterialFiltroDto;
import com.example.cper_core.entities.Material;
import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class MaterialSpecification {

    public static Specification<Material> filter(MaterialFiltroDto filtro) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filtro.getId() != null) {
                predicates.add(cb.equal(root.get("id"), filtro.getId()));
            }
            if (filtro.getNome() != null && !filtro.getNome().isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("nome")), "%" + filtro.getNome().toLowerCase() + "%"));
            }
            if (filtro.getCategoria() != null && !filtro.getCategoria().isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("categoria")), "%" + filtro.getCategoria().toLowerCase() + "%"));
            }
            if (filtro.getUniMedidaPeso() != null) {
                predicates.add(cb.equal(root.get("uniMedidaPeso"), filtro.getUniMedidaPeso().getId()));
            }
            if (filtro.getUniMedidaVolume() != null) {
                predicates.add(cb.equal(root.get("uniMedidaVolume"), filtro.getUniMedidaVolume().getId()));
            }
            if (filtro.getCustoUniMin() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("custoUni"), filtro.getCustoUniMin()));
            }
            if (filtro.getCustoUniMax() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("custoUni"), filtro.getCustoUniMax()));
            }
            if (filtro.getPesoMin() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("peso"), filtro.getPesoMin()));
            }
            if (filtro.getPesoMax() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("peso"), filtro.getPesoMax()));
            }
            if (filtro.getVolumeMin() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("volume"), filtro.getVolumeMin()));
            }
            if (filtro.getVolumeMax() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("volume"), filtro.getVolumeMax()));
            }
            if (filtro.getIsDeleted() != null) {
                predicates.add(cb.equal(root.get("isDeleted"), filtro.getIsDeleted()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}