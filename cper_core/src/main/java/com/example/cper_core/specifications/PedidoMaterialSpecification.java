package com.example.cper_core.specifications;

import com.example.cper_core.dtos.pedido_material.PedidoMaterialFiltroDto;
import com.example.cper_core.entities.PedidoMaterial;
import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class PedidoMaterialSpecification {

    public static Specification<PedidoMaterial> filter(PedidoMaterialFiltroDto filtro) {
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
            if (filtro.getDescricao() != null && !filtro.getDescricao().isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("descricao")), "%" + filtro.getDescricao().toLowerCase() + "%"));
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
            if (filtro.getIdsAvarias() != null && !filtro.getIdsAvarias().isEmpty()) {
                predicates.add(root.get("avaria").get("id").in(filtro.getIdsAvarias()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}