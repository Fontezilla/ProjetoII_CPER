package com.example.cper_core.specifications;

import com.example.cper_core.dtos.material_solicitacao_material.MaterialSolicitacaoMaterialFiltroDto;
import com.example.cper_core.entities.MaterialSolicitacaoMaterial;
import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class MaterialSolicitacaoMaterialSpecification {

    public static Specification<MaterialSolicitacaoMaterial> filter(MaterialSolicitacaoMaterialFiltroDto filtro) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filtro.getIdMaterial() != null) {
                predicates.add(cb.equal(root.get("material").get("id"), filtro.getIdMaterial()));
            }
            if (filtro.getIdSolicitacao() != null) {
                predicates.add(cb.equal(root.get("solicitacaoMaterial").get("id"), filtro.getIdSolicitacao()));
            }
            if (filtro.getQtdMin() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("qtd"), filtro.getQtdMin()));
            }
            if (filtro.getQtdMax() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("qtd"), filtro.getQtdMax()));
            }
            if (filtro.getIsDeleted() != null) {
                predicates.add(cb.equal(root.get("isDeleted"), filtro.getIsDeleted()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}