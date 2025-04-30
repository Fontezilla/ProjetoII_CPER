package com.example.cper_core.services.interfaces;

import com.example.cper_core.dtos.contrato.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IContratoService {
    
    // Generic CRUD operations

    Page<ContratoDetailsDto> listAll(Pageable pageable);

    Page<ContratoDetailsDto> listFiltered(Pageable pageable, ContratoFiltroDto filter);

    Optional<ContratoDetailsExtendedDto> getById(Integer id);

    ContratoDetailsExtendedDto create(ContratoDetailsExtendedDto dto);

    ContratoDetailsExtendedDto update(Integer id, ContratoDetailsExtendedDto dto);

    void softDelete(Integer id);

    // Association operations: Pedido Geracao

    ContratoWithPedidoDto linkPedido(Integer idContrato, Integer idPedido);

    ContratoWithPedidoDto unlinkPedido(Integer idContrato, Integer idPedido);

    // Association operations: Fatura

    ContratoWithFaturaDto linkFatura(Integer idContrato, Integer idFatura);

    ContratoWithFaturaDto unlinkFatura(Integer idContrato, Integer idFatura);
}
