package com.example.cper_core.repositories;

import com.example.cper_core.entities.Localidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface LocalidadeRepository extends JpaRepository<Localidade, Integer>, JpaSpecificationExecutor<Localidade> {
  @Query("SELECT l FROM Localidade l WHERE LOWER(TRIM(l.nome)) = LOWER(TRIM(:nome)) AND l.codigoPostal = :codigoPostal")
  Optional<Localidade> findByCodigoPostalAndNomeIgnoreCase(String codigoPostal, String nome);
}