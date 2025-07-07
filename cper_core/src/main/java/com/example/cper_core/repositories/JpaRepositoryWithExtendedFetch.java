package com.example.cper_core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface JpaRepositoryWithExtendedFetch<T, ID> extends JpaRepository<T, ID> {
    Optional<T> findByIdExtended(ID id);
}
