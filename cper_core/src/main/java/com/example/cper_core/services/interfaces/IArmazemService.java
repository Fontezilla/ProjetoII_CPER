package com.example.cper_core.services.interfaces;

import com.example.cper_core.dtos.armazem.*;

import java.util.Set;

public interface IArmazemService extends IXService<
        ArmazemDto,
        ArmazemDetailsDto,
        ArmazemDetailsExtendedDto,
        ArmazemFiltroDto,
        Integer
        > {

    void linkToFuncionarios(Integer idArmazem, Set<Integer> idsFuncionarios);
    void unlinkFromFuncionarios(Integer idArmazem, Set<Integer> idsFuncionarios);
}
