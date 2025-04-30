package com.example.cper_core.services.interfaces;

import com.example.cper_core.dtos.inspecao.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IInspecaoService {

    // Generic CRUD operations

    Page<InspecaoDetailsDto> listAll(Pageable pageable);

    Page<InspecaoDetailsDto> listFiltered(Pageable pageable, InspecaoFiltroDto filter);

    Optional<InspecaoDetailsExtendedDto> getById(Integer id);

    InspecaoDetailsExtendedDto create(InspecaoDetailsExtendedDto dto);

    InspecaoDetailsExtendedDto update(Integer id, InspecaoDetailsExtendedDto dto);

    void softDelete(Integer id);

    // Association operations: Avaria

    InspecaoWithAvariaDto linkAvaria(Integer idInspecao, Integer idAvaria);

    InspecaoWithAvariaDto unlinkAvaria(Integer idInspecao, Integer idAvaria);

    // Association operations: Equipa

    InspecaoWithEquipaDto linkEquipa(Integer idInspecao, Integer idEquipa);

    InspecaoWithEquipaDto unlinkEquipa(Integer idInspecao, Integer idEquipa);

    // Association operations: Notas

    InspecaoWithNotasDto linkNotas(Integer idInspecao, Integer idNota);

    InspecaoWithNotasDto unlinkNotas(Integer idInspecao, Integer idNota);
}
