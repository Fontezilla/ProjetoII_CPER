package com.example.cper_core.services.interfaces;

import com.example.cper_core.dtos.departamento.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IDepartamentoService {

    // Generic CRUD operations

    Page<DepartamentoDetailsDto> listAll(Pageable pageable);

    Page<DepartamentoDetailsDto> listFiltered(Pageable pageable, DepartamentoFiltroDto filter);

    Optional<DepartamentoDetailsExtendedDto> getById(Integer id);

    DepartamentoDetailsExtendedDto create(DepartamentoDetailsExtendedDto dto);

    DepartamentoDetailsExtendedDto update(Integer id, DepartamentoDetailsExtendedDto dto);

    void softDelete(Integer id);

    // Association operations: Responsavel (Funcionario)

    DepartamentoWithResponsavelDto linkResponsavel(Integer idDepartamento, Integer idResponsavel);

    DepartamentoWithResponsavelDto unlinkResponsavel(Integer idDepartamento, Integer idResponsavel);

    // Association operations: Equipa

    DepartamentoWithEquipaDto linkEquipa(Integer idDepartamento, Integer idEquipa);

    DepartamentoWithEquipaDto unlinkEquipa(Integer idDepartamento, Integer idEquipa);

    // Association operations: Centro Producao

    DepartamentoWithCentroDto linkCentro(Integer idDepartamento, Integer idCentro);

    DepartamentoWithCentroDto unlinkCentro(Integer idDepartamento, Integer idCentro);

    // Association operations: Armazem

    DepartamentoWithArmazemDto linkArmazem(Integer idDepartamento, Integer idArmazem);

    DepartamentoWithArmazemDto unlinkArmazem(Integer idDepartamento, Integer idArmazem);

    // Association operations: Funcionario

    DepartamentoWithFuncionarioDto linkFuncionario(Integer idDepartamento, Integer idFuncionario);

    DepartamentoWithFuncionarioDto unlinkFuncionario(Integer idDepartamento, Integer idFuncionario);
}
