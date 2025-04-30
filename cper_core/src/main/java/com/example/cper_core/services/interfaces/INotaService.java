package com.example.cper_core.services.interfaces;

import com.example.cper_core.dtos.nota.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface INotaService {

    // Generic CRUD operations

    Page<NotaDetailsDto> listAll(Pageable pageable);

    Page<NotaDetailsDto> listFiltered(Pageable pageable, NotaDto filter);

    Optional<NotaDetailsExtendedDto> getById(Integer id);

    NotaDetailsExtendedDto create(NotaDetailsExtendedDto dto);

    NotaDetailsExtendedDto update(Integer id, NotaDetailsExtendedDto dto);

    void softDelete(Integer id);
}