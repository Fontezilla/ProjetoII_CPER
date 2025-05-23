package com.example.cper_core.services.interfaces;

import com.example.cper_core.dtos.nota.*;

public interface INotaService extends IXService<
        NotaDto,
        NotaDetailsDto,
        NotaDetailsExtendedDto,
        NotaFiltroDto,
        NotaWithRelationshipsDto,
        Integer
        > {
}