package com.example.cper_core.services.interfaces;

import com.example.cper_core.dtos.material_solicitacao_material.*;
import com.example.cper_core.entities.MaterialSolicitacaoMaterialId;

public interface IMaterialSolicitacaoMaterialService extends IXService<
        MaterialSolicitacaoMaterialDto,
        MaterialSolicitacaoMaterialDetailsDto,
        MaterialSolicitacaoMaterialDetailsDto,
        MaterialSolicitacaoMaterialFiltroDto,
        MaterialSolicitacaoMaterialDto,
        MaterialSolicitacaoMaterialId
        > {
}