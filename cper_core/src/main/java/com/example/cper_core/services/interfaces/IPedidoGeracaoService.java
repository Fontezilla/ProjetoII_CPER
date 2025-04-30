package com.example.cper_core.services.interfaces;

import com.example.cper_core.dtos.pedido_geracao.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IPedidoGeracaoService {

    // Generic CRUD operations

    Page<PedidoGeracaoDetailsDto> listAll(Pageable pageable);

    Page<PedidoGeracaoDetailsDto> listFiltered(Pageable pageable, PedidoGeracaoDto filter);

    Optional<PedidoGeracaoDetailsExtendedDto> getById(Integer id);

    PedidoGeracaoDetailsExtendedDto create(PedidoGeracaoDetailsExtendedDto dto);

    PedidoGeracaoDetailsExtendedDto update(Integer id, PedidoGeracaoDetailsExtendedDto dto);

    void softDelete(Integer id);
}
