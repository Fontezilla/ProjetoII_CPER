package com.example.cper_core.services.interfaces;

import com.example.cper_core.dtos.solicitacao_material.*;

public interface ISolicitacaoMaterialService extends IXService<
        SolicitacaoMaterialDto,
        SolicitacaoMaterialDetailsDto,
        SolicitacaoMaterialDetailsExtendedDto,
        SolicitacaoMaterialFiltroDto,
        Integer
        > {
}