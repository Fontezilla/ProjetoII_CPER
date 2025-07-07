package com.example.cper_core.specifications;

import com.example.cper_core.dtos.anomalia.AnomaliaFiltroDto;
import com.example.cper_core.entities.Anomalia;
import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class AnomaliaSpecification {

    public static Specification<Anomalia> filter(AnomaliaFiltroDto filtro) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filtro.getId() != null) {
                predicates.add(cb.equal(root.get("id"), filtro.getId()));
            }
            if (filtro.getCodigo() != null && !filtro.getCodigo().isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("codigo")), "%" + filtro.getCodigo().toLowerCase() + "%"));
            }
            if (filtro.getTipoAnomalia() != null) {
                predicates.add(cb.equal(root.get("tipoAnomalia"), filtro.getTipoAnomalia().getId()));
            }
            if (filtro.getTitulo() != null && !filtro.getTitulo().isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("titulo")), "%" + filtro.getTitulo().toLowerCase() + "%"));
            }
            if (filtro.getDescricao() != null && !filtro.getDescricao().isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("descricao")), "%" + filtro.getDescricao().toLowerCase() + "%"));
            }
            if (filtro.getDataIdentificacaoInicio() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("dataIdentificacao"), filtro.getDataIdentificacaoInicio()));
            }
            if (filtro.getDataIdentificacaoFim() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("dataIdentificacao"), filtro.getDataIdentificacaoFim()));
            }
            if (filtro.getLocalizacao() != null && !filtro.getLocalizacao().isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("localizacao")), "%" + filtro.getLocalizacao().toLowerCase() + "%"));
            }
            if (filtro.getGravidade() != null) {
                predicates.add(cb.equal(root.get("gravidade"), filtro.getGravidade().getId()));
            }
            if (filtro.getEstado() != null) {
                predicates.add(cb.equal(root.get("estado"), filtro.getEstado().getId()));
            }
            if (filtro.getIsDeleted() != null) {
                predicates.add(cb.equal(root.get("isDeleted"), filtro.getIsDeleted()));
            }
            if (filtro.getIdsCentroProducao() != null && !filtro.getIdsCentroProducao().isEmpty()) {
                predicates.add(root.get("centroProducao").get("id").in(filtro.getIdsCentroProducao()));
            }
            if (filtro.getIdsFuncionario() != null && !filtro.getIdsFuncionario().isEmpty()) {
                predicates.add(root.get("funcionario").get("id").in(filtro.getIdsFuncionario()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}