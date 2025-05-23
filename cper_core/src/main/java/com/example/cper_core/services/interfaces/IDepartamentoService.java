package com.example.cper_core.services.interfaces;

import com.example.cper_core.dtos.departamento.*;

import java.util.Set;

public interface IDepartamentoService extends IXService<
        DepartamentoDto,
        DepartamentoDetailsDto,
        DepartamentoDetailsExtendedDto,
        DepartamentoFiltroDto,
        DepartamentoWithRelationshipsDto,
        Integer
        > {

    void linkToResponsaveis(Integer id, Set<Integer> ids);
    void unlinkFromResponsaveis(Integer id, Set<Integer> ids);
}