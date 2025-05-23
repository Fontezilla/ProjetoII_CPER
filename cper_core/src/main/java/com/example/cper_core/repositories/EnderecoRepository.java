package com.example.cper_core.repositories;

import com.example.cper_core.entities.ArmazemLote;
import com.example.cper_core.entities.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface EnderecoRepository extends JpaRepository<Endereco, Integer>, JpaSpecificationExecutor<Endereco> {
  }