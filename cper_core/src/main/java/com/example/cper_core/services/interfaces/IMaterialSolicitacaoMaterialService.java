package com.example.cper_core.services.interfaces;

import com.example.cper_core.dtos.material_solicitacao_material.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IMaterialSolicitacaoMaterialService {

    // Generic CRUD operations

    Page<MaterialSolicitacaoMaterialDto> listAll(Pageable pageable);

    Page<MaterialSolicitacaoMaterialDto> listFiltered(Pageable pageable, MaterialSolicitacaoMaterialDto filter);

    Optional<MaterialSolicitacaoMaterialDto> getById(Integer id);

    MaterialSolicitacaoMaterialDto create(MaterialSolicitacaoMaterialDto dto);

    MaterialSolicitacaoMaterialDto update(Integer id, MaterialSolicitacaoMaterialDto dto);

    void softDelete(Integer id);
}
