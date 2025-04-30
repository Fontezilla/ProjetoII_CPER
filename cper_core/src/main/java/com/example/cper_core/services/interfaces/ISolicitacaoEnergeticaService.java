package com.example.cper_core.services.interfaces;

import com.example.cper_core.dtos.solicitacao_energetica.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ISolicitacaoEnergeticaService {

    // Generic CRUD operations

    Page<SolicitacaoEnergeticaDetailsDto> listAll(Pageable pageable);

    Page<SolicitacaoEnergeticaDetailsDto> listFiltered(Pageable pageable, SolicitacaoEnergeticaDto filter);

    Optional<SolicitacaoEnergeticaDetailsExtendedDto> getById(Integer id);

    SolicitacaoEnergeticaDetailsExtendedDto create(SolicitacaoEnergeticaDetailsExtendedDto dto);

    SolicitacaoEnergeticaDetailsExtendedDto update(Integer id, SolicitacaoEnergeticaDetailsExtendedDto dto);

    void softDelete(Integer id);
}