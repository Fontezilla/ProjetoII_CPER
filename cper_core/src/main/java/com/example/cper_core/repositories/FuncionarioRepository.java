package com.example.cper_core.repositories;

import com.example.cper_core.entities.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer>, JpaSpecificationExecutor<Funcionario> {

    Optional<Funcionario> findByEmail(String email);

    @Query("""
        SELECT DISTINCT f FROM Funcionario f
        LEFT JOIN FETCH f.departamento
        LEFT JOIN FETCH f.endereco
        LEFT JOIN FETCH f.responsavelTickets
        LEFT JOIN FETCH f.responsavelFaturas
        LEFT JOIN FETCH f.responsavelEquipas
        LEFT JOIN FETCH f.responsavelAnomalias
        LEFT JOIN FETCH f.responsavelArmazems
        LEFT JOIN FETCH f.responsavelContratos
        LEFT JOIN FETCH f.responsavelPedidoGeracoes
        LEFT JOIN FETCH f.armazens
        LEFT JOIN FETCH f.centroProducoes
        LEFT JOIN FETCH f.solicitacaoEnergeticas
        LEFT JOIN FETCH f.equipas
        LEFT JOIN FETCH f.Departamentos
        LEFT JOIN FETCH f.comentario
        LEFT JOIN FETCH f.respostas
        WHERE f.id = :idFuncionario
    """)
    Optional<Funcionario> findByIdWithAllRelations(@Param("idFuncionario") Integer idFuncionario);
}