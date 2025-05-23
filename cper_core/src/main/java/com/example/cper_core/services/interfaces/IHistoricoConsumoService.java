package com.example.cper_core.services.interfaces;

import com.example.cper_core.dtos.historico_consumo.*;

public interface IHistoricoConsumoService extends IXService<
        HistoricoConsumoDto,
        HistoricoConsumoDetailsDto,
        HistoricoConsumoDetailsExtendedDto,
        HistoricoConsumoFiltroDto,
        HistoricoConsumoWithRelationshipsDto,
        Integer
        > {
}