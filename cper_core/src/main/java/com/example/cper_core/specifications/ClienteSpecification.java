package com.example.cper_core.specifications;

import com.example.cper_core.dtos.cliente.ClienteFiltroDto;
import com.example.cper_core.entities.Cliente;
import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class ClienteSpecification {

    public static Specification<Cliente> filter(ClienteFiltroDto filtro) {
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
            if (filtro.getTipoCliente() != null) {
                predicates.add(cb.equal(root.get("tipoCliente"), filtro.getTipoCliente().getId()));
            }
            if (filtro.getEstado() != null) {
                predicates.add(cb.equal(root.get("estado"), filtro.getEstado().getId()));
            }
            if (filtro.getDataCadastroInicio() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("dataCadastro"), filtro.getDataCadastroInicio()));
            }
            if (filtro.getDataCadastroFim() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("dataCadastro"), filtro.getDataCadastroFim()));
            }
            if (filtro.getConsumoMedioMin() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("consumoMedio"), filtro.getConsumoMedioMin()));
            }
            if (filtro.getConsumoMedioMax() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("consumoMedio"), filtro.getConsumoMedioMax()));
            }
            if (filtro.getDemandaContratadaMin() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("demandaContratada"), filtro.getDemandaContratadaMin()));
            }
            if (filtro.getDemandaContratadaMax() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("demandaContratada"), filtro.getDemandaContratadaMax()));
            }
            if (filtro.getIsDeleted() != null) {
                predicates.add(cb.equal(root.get("isDeleted"), filtro.getIsDeleted()));
            }
            if (filtro.getIdsEndereco() != null && !filtro.getIdsEndereco().isEmpty()) {
                predicates.add(root.get("endereco").get("id").in(filtro.getIdsEndereco()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}