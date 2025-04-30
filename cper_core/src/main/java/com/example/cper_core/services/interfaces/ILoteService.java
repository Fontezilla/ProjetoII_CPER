package com.example.cper_core.services.interfaces;

import com.example.cper_core.dtos.lote.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ILoteService {

    // Generic CRUD operations

    Page<LoteDetailsDto> listAll(Pageable pageable);

    Page<LoteDetailsDto> listFiltered(Pageable pageable, LoteDto filter);

    Optional<LoteDetailsDto> getById(Integer id);

    LoteDetailsDto create(LoteDetailsDto dto);

    LoteDetailsDto update(Integer id, LoteDetailsDto dto);

    void softDelete(Integer id);
}
