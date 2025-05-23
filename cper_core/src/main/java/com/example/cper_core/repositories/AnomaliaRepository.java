package com.example.cper_core.repositories;

import com.example.cper_core.entities.Anomalia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AnomaliaRepository extends JpaRepository<Anomalia, Integer>, JpaSpecificationExecutor<Anomalia> {
}