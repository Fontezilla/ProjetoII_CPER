package com.example.cper_core.services.interfaces;

import com.example.cper_core.dtos.anomalia.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IAnomaliaService {

    // Generic CRUD operations + Extra

    Page<AnomaliaDetailsDto> listAll(Pageable pageable);

    Page<AnomaliaDetailsDto> listFiltered(Pageable pageable, AnomaliaFiltroDto filter);

    Optional<AnomaliaDetailsExtendedDto> getById(Integer id);

    AnomaliaDetailsExtendedDto create(AnomaliaDetailsExtendedDto dto);

    AnomaliaDetailsExtendedDto update(AnomaliaDetailsExtendedDto dto);

    void softDelete(Integer id);

    // Association operations

    AnomaliaWithNotasDto linkNota(Integer idAnomalia, Integer idNota);

    AnomaliaWithNotasDto unlinkNota(Integer idAnomalia, Integer idNota);
}
