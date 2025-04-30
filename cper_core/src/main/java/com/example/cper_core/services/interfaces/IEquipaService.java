package com.example.cper_core.services.interfaces;

import com.example.cper_core.dtos.equipa.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IEquipaService {

    // Generic CRUD operations

    Page<EquipaDetailsDto> listAll(Pageable pageable);

    Page<EquipaDetailsDto> listFiltered(Pageable pageable, EquipaFiltroDto filter);

    Optional<EquipaDetailsDto> getById(Integer id);

    EquipaDetailsDto create(EquipaDetailsDto dto);

    EquipaDetailsDto update(Integer id, EquipaDetailsDto dto);

    void softDelete(Integer id);

    // Association operations: Avaria

    EquipaWithAvariaDto linkAvaria(Integer idEquipa, Integer idAvaria);

    EquipaWithAvariaDto unlinkAvaria(Integer idEquipa, Integer idAvaria);

    // Association operations: Funcionario

    EquipaWithFuncionarioDto linkFuncionario(Integer idEquipa, Integer idFuncionario);

    EquipaWithFuncionarioDto unlinkFuncionario(Integer idEquipa, Integer idFuncionario);

    // Association operations: Inspecao

    EquipaWithInspecaoDto linkInspecao(Integer idEquipa, Integer idInspecao);

    EquipaWithInspecaoDto unlinkInspecao(Integer idEquipa, Integer idInspecao);
}