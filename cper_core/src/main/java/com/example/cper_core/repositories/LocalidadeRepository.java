package com.example.cper_core.repositories;

import com.example.cper_core.entities.Localidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LocalidadeRepository extends JpaRepository<Localidade, Integer>, JpaSpecificationExecutor<Localidade> {

    @Query("""
        SELECT DISTINCT l FROM Localidade l
        LEFT JOIN FETCH l.enderecos
        WHERE l.id = :idLocalidade
    """)
    Optional<Localidade> findByIdWithEnderecos(@Param("idLocalidade") Integer idLocalidade);
}
