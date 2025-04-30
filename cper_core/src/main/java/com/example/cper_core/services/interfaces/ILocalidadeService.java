package com.example.cper_core.services.interfaces;

import com.example.cper_core.dtos.localidade.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ILocalidadeService {

    // Generic CRUD operations

    Page<LocalidadeDetailsDto> listAll(Pageable pageable);

    Page<LocalidadeDetailsDto> listFiltered(Pageable pageable, LocalidadeFiltroDto filter);

    Optional<LocalidadeDetailsDto> getById(Integer id);

    LocalidadeDetailsDto create(LocalidadeDetailsDto dto);

    LocalidadeDetailsDto update(Integer id, LocalidadeDetailsDto dto);

    void softDelete(Integer id);

    // Association operations: Enderecos

    LocalidadeWithEnderecosDto linkEndereco(Integer idLocalidade, Integer idEndereco);

    LocalidadeWithEnderecosDto unlinkEndereco(Integer idLocalidade, Integer idEndereco);
}