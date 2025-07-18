package com.example.cper_core.services.interfaces;

import com.example.cper_core.repositories.JpaRepositoryWithExtendedFetch;

public interface IWithRelationshipsSupport<TEntity, TWithRelationshipsDto, TId> {
    JpaRepositoryWithExtendedFetch<TEntity, TId> getRepository();

    TWithRelationshipsDto toWithRelationshipsDto(TEntity entity);

    default TWithRelationshipsDto getWithRelationshipsById(TId id) {
        TEntity entity = getRepository().findByIdExtended(id)
                .orElseThrow(() -> new IllegalArgumentException("Registo não encontrado com ID " + id));
        return toWithRelationshipsDto(entity);
    }
}
