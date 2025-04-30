package com.example.cper_core.services.interfaces;

import com.example.cper_core.dtos.armazem.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IArmazemService {

    // Generic CRUD operations + Extra

    Page<ArmazemDetailsDto> listAll(Pageable pageable);

    Page<ArmazemDetailsDto> listFiltered(Pageable pageable, ArmazemFiltroDto filter);

    Optional<ArmazemDetailsExtendedDto> getById(Integer id);

    ArmazemDetailsExtendedDto create(ArmazemDetailsExtendedDto dto);

    ArmazemDetailsExtendedDto update(ArmazemDetailsExtendedDto dto);

    void softDelete(Integer id);

    // Association operations

    ArmazemWithStockDto linkNota(Integer idArmazem, Integer idStock);

    ArmazemWithStockDto unlinkNota(Integer idArmazem, Integer idStock);

    ArmazemWithFuncionarioDto linkFuncionario(Integer idArmazem, Integer idFuncionario);

    ArmazemWithFuncionarioDto unlinkFuncionario(Integer idArmazem, Integer idFuncionario);
}