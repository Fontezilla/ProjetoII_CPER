package com.example.cper_core.services.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IXService<
        TDto,
        TDetailsDto,
        TExtendedDto,
        TFiltroDto,
        TId
        > {

    TExtendedDto create(TExtendedDto dto);

    TDetailsDto getById(TId id);

    TExtendedDto getExtendedById(TId id);

    Page<TDetailsDto> listAll(Pageable pageable);

    Page<TDetailsDto> listFiltered(Pageable pageable, TFiltroDto filtro);

    TExtendedDto update(TId id, TExtendedDto dto);

    void delete(TId id);
}