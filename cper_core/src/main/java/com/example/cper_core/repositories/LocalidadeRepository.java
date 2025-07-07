package com.example.cper_core.repositories;

import com.example.cper_core.entities.Localidade;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface LocalidadeRepository extends JpaRepositoryWithExtendedFetch<Localidade, Integer>,
        JpaSpecificationExecutor<Localidade> {

  @EntityGraph(attributePaths = {
          "enderecos"
  })
  @Query("SELECT l FROM Localidade l WHERE l.id = :id")
  @Override
  Optional<Localidade> findByIdExtended(Integer id);

  @Query("SELECT l FROM Localidade l WHERE LOWER(TRIM(l.nome)) = LOWER(TRIM(:nome)) AND l.codigoPostal = :codigoPostal")
  Optional<Localidade> findByCodigoPostalAndNomeIgnoreCase(String codigoPostal, String nome);
}
