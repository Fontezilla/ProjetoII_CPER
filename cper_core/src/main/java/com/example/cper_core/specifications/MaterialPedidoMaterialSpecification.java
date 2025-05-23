package com.example.cper_core.specifications;

import com.example.cper_core.dtos.material_pedido_material.MaterialPedidoMaterialFiltroDto;
import com.example.cper_core.entities.MaterialPedidoMaterial;
import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class MaterialPedidoMaterialSpecification {

    public static Specification<MaterialPedidoMaterial> filter(MaterialPedidoMaterialFiltroDto filtro) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filtro.getIdPedido() != null) {
                predicates.add(cb.equal(root.get("pedidoMaterial").get("id"), filtro.getIdPedido()));
            }
            if (filtro.getIdMaterial() != null) {
                predicates.add(cb.equal(root.get("material").get("id"), filtro.getIdMaterial()));
            }
            if (filtro.getQtdMin() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("qtd"), filtro.getQtdMin()));
            }
            if (filtro.getQtdMax() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("qtd"), filtro.getQtdMax()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}