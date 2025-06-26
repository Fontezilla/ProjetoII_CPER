package com.example.cper_core.repositories;

import com.example.cper_core.entities.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface EnderecoRepository extends JpaRepository<Endereco, Integer>, JpaSpecificationExecutor<Endereco> {
  @Query("SELECT e FROM Endereco e WHERE LOWER(TRIM(e.rua)) = LOWER(TRIM(:rua)) AND e.localidade.id = :localidadeId")
  Optional<Endereco> findByRuaIgnoreCaseAndLocalidadeId(String rua, Integer localidadeId);
}