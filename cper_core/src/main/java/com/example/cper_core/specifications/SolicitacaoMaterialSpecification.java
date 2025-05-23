package com.example.cper_core.specifications;

import com.example.cper_core.dtos.solicitacao_material.SolicitacaoMaterialFiltroDto;
import com.example.cper_core.entities.SolicitacaoMaterial;
import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class SolicitacaoMaterialSpecification {

    public static Specification<SolicitacaoMaterial> filter(SolicitacaoMaterialFiltroDto filtro) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filtro.getId() != null) {
                predicates.add(cb.equal(root.get("id"), filtro.getId()));
            }
            if (filtro.getDataPedidoInicio() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("dataPedido"), filtro.getDataPedidoInicio()));
            }
            if (filtro.getDataPedidoFim() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("dataPedido"), filtro.getDataPedidoFim()));
            }
            if (filtro.getDescricao() != null && !filtro.getDescricao().isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("descricao")), "%" + filtro.getDescricao().toLowerCase() + "%"));
            }
            if (filtro.getEstado() != null) {
                predicates.add(cb.equal(root.get("estado"), filtro.getEstado().getId()));
            }
            if (filtro.getIsDeleted() != null) {
                predicates.add(cb.equal(root.get("isDeleted"), filtro.getIsDeleted()));
            }
            if (filtro.getIdsMaterialSolicitacao() != null && !filtro.getIdsMaterialSolicitacao().isEmpty()) {
                predicates.add(root.get("materialSolicitacaoMaterial").get("id").in(filtro.getIdsMaterialSolicitacao()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}