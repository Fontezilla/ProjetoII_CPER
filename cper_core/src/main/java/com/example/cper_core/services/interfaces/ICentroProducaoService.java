package com.example.cper_core.services.interfaces;

import com.example.cper_core.dtos.centro_producao.*;

import java.util.Set;

public interface ICentroProducaoService extends IXService<
        CentroProducaoDto,
        CentroProducaoDetailsDto,
        CentroProducaoDetailsExtendedDto,
        CentroProducaoFiltroDto,
        Integer
        > {

    void linkToFuncionarios(Integer id, Set<Integer> ids);
    void unlinkFromFuncionarios(Integer id, Set<Integer> ids);
}