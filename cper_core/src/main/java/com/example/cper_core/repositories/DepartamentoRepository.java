package com.example.cper_core.repositories;

import com.example.cper_core.entities.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento, Integer>, JpaSpecificationExecutor<Departamento> {

    @Query("SELECT DISTINCT d.setor FROM Departamento d JOIN d.funcionarios f WHERE f.id = :userId")
    Set<Integer> findSetorIdsByFuncionarioId(@Param("userId") Integer userId);

    @Query("""
        SELECT DISTINCT d FROM Departamento d
        LEFT JOIN FETCH d.funcionarios
        LEFT JOIN FETCH d.armazens
        LEFT JOIN FETCH d.centrosProducao
        LEFT JOIN FETCH d.equipas
        LEFT JOIN FETCH d.responsaveis
        WHERE d.id = :idDepartamento
    """)
    Optional<Departamento> findByIdWithAllRelations(@Param("idDepartamento") Integer idDepartamento);
}
