package com.example.cper_core.services.interfaces;

import com.example.cper_core.dtos.avaria.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IAvariaService {

    // Generic CRUD operations + Extra

    Page<AvariaDetailsDto> listAll(Pageable pageable);

    Page<AvariaDetailsDto> listFiltered(Pageable pageable, AvariaFiltroDto filter);

    Optional<AvariaDetailsExtendedDto> getById(Integer id);

    AvariaDetailsExtendedDto create(AvariaDetailsExtendedDto dto);

    AvariaDetailsExtendedDto update(AvariaDetailsExtendedDto dto);

    void softDelete(Integer id);

    // Association Operations

    AvariaWithEquipaDto linkEquipa(Integer idAvaria, Integer idEquipa);

    AvariaWithEquipaDto unlinkEquipa(Integer idAvaria, Integer idEquipa);

    AvariaWithPedidoDto linkPedido(Integer idAvaria, Integer idPedido);

    AvariaWithPedidoDto unlinkPedido(Integer idAvaria, Integer idPedido);

    AvariaWithNotasDto linkNota(Integer idAvaria, Integer idNota);

    AvariaWithNotasDto unlinkNota(Integer idAvaria, Integer idNota);
}
