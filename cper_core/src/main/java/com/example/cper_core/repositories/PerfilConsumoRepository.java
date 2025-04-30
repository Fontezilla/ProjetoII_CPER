package com.example.cper_core.repositories;

import com.example.cper_core.entities.PerfilConsumo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PerfilConsumoRepository extends JpaRepository<PerfilConsumo, Integer>, JpaSpecificationExecutor<PerfilConsumo> {

    @Query("""
        SELECT p FROM PerfilConsumo p
        LEFT JOIN FETCH p.cliente
        WHERE p.id = :idPerfilConsumo
    """)
    Optional<PerfilConsumo> findByIdWithCliente(@Param("idPerfilConsumo") Integer idPerfilConsumo);
}
