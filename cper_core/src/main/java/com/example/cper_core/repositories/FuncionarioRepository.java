package com.example.cper_core.repositories;

import com.example.cper_core.entities.ArmazemLote;
import com.example.cper_core.entities.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer>, JpaSpecificationExecutor<Funcionario> {

  Optional<Funcionario> findByEmail(String email);
}