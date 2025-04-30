package com.example.cper_core.services.interfaces;

import com.example.cper_core.dtos.stock.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IStockService {

    // Generic CRUD operations

    Page<StockDetailsDto> listAll(Pageable pageable);

    Page<StockDetailsDto> listFiltered(Pageable pageable, StockDto filter);

    Optional<StockDetailsDto> getById(Integer id);

    StockDetailsDto create(StockDetailsDto dto);

    StockDetailsDto update(Integer id, StockDetailsDto dto);

    void softDelete(Integer id);

    // Association operations: Armazem

    StockWithArmazemDto linkArmazem(Integer idStock, Integer idArmazem);

    StockWithArmazemDto unlinkArmazem(Integer idStock, Integer idArmazem);

    // Association operations: Lote

    StockWithLoteDto linkLote(Integer idStock, Integer idLote);

    StockWithLoteDto unlinkLote(Integer idStock, Integer idLote);
}
