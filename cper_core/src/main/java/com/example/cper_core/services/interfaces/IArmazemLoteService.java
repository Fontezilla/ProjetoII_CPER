package com.example.cper_core.services.interfaces;

import com.example.cper_core.dtos.armazem_lote.*;
import com.example.cper_core.entities.ArmazemLoteId;

public interface IArmazemLoteService extends IXService<
        ArmazemLoteDto,
        ArmazemLoteDetailsDto,
        ArmazemLoteDetailsExtendedDto,
        ArmazemLoteFiltroDto,
        ArmazemLoteWithRelationshipsDto,
        ArmazemLoteId
        > {
}