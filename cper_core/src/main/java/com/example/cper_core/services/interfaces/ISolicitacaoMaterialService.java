package com.example.cper_core.services.interfaces;

import com.example.cper_core.dtos.solicitacao_material.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ISolicitacaoMaterialService {

    // Generic CRUD operations

    Page<SolicitacaoMaterialDetailsDto> listAll(Pageable pageable);

    Page<SolicitacaoMaterialDetailsDto> listFiltered(Pageable pageable, SolicitacaoMaterialDto filter);

    Optional<SolicitacaoMaterialDetailsDto> getById(Integer id);

    SolicitacaoMaterialDetailsDto create(SolicitacaoMaterialDetailsDto dto);

    SolicitacaoMaterialDetailsDto update(Integer id, SolicitacaoMaterialDetailsDto dto);

    void softDelete(Integer id);

    // Association operations: Solicitacao

    SolicitacaoMaterialWithSolicitacaoDto linkSolicitacao(Integer idSolicitacaoMaterial, Integer idSolicitacao);

    SolicitacaoMaterialWithSolicitacaoDto unlinkSolicitacao(Integer idSolicitacaoMaterial, Integer idSolicitacao);
}
