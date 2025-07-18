package com.example.cper_core.services.interfaces;

import com.example.cper_core.dtos.fatura.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IFaturaService extends IXService<
        FaturaDto,
        FaturaDetailsDto,
        FaturaDetailsExtendedDto,
        FaturaFiltroDto,
        Integer
        > {
}