package com.example.cper_core.repositories;

import com.example.cper_core.entities.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Integer>, JpaSpecificationExecutor<Endereco> {

    @Query("""
        SELECT DISTINCT e FROM Endereco e
        LEFT JOIN FETCH e.funcionarios
        LEFT JOIN FETCH e.clientes
        LEFT JOIN FETCH e.armazens
        LEFT JOIN FETCH e.centroProducaos
        LEFT JOIN FETCH e.contratoes
        LEFT JOIN FETCH e.localidade
        WHERE e.id = :idEndereco
    """)
    Optional<Endereco> findByIdWithAllRelations(@Param("idEndereco") Integer idEndereco);
}