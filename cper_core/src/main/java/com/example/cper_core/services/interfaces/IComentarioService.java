package com.example.cper_core.services.interfaces;

import com.example.cper_core.dtos.comentario.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IComentarioService {

    // Generic CRUD operations

    Page<ComentarioDetailsDto> listAll(Pageable pageable);

    Page<ComentarioDetailsDto> listFiltered(Pageable pageable, ComentarioFiltroDto filter);

    Optional<ComentarioDetailsDto> getById(Integer id);

    ComentarioDetailsDto create(ComentarioDetailsDto dto);

    ComentarioDetailsDto update(Integer id, ComentarioDetailsDto dto);

    void softDelete(Integer id);
}
