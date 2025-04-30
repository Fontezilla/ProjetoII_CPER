package com.example.cper_core.services.interfaces;

import com.example.cper_core.dtos.perfil_consumo.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IPerfilConsumoService {

    // Generic CRUD operations

    Page<PerfilConsumoDetailsDto> listAll(Pageable pageable);

    Page<PerfilConsumoDetailsDto> listFiltered(Pageable pageable, PerfilConsumoDto filter);

    Optional<PerfilConsumoDetailsDto> getById(Integer id);

    PerfilConsumoDetailsDto create(PerfilConsumoDetailsDto dto);

    PerfilConsumoDetailsDto update(Integer id, PerfilConsumoDetailsDto dto);

    void softDelete(Integer id);
}