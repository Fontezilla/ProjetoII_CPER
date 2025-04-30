package com.example.cper_core.services.interfaces;

import com.example.cper_core.dtos.fatura.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IFaturaService {

    // Generic CRUD operations

    Page<FaturaDetailsDto> listAll(Pageable pageable);

    Page<FaturaDetailsDto> listFiltered(Pageable pageable, FaturaFiltroDto filter);

    Optional<FaturaDetailsExtendedDto> getById(Integer id);

    FaturaDetailsExtendedDto create(FaturaDetailsExtendedDto dto);

    FaturaDetailsExtendedDto update(Integer id, FaturaDetailsExtendedDto dto);

    void softDelete(Integer id);
}
