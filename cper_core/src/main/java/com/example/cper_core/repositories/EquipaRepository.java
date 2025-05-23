package com.example.cper_core.repositories;

import com.example.cper_core.entities.ArmazemLote;
import com.example.cper_core.entities.Equipa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface EquipaRepository extends JpaRepository<Equipa, Integer>, JpaSpecificationExecutor<Equipa> {
}