package com.example.cper_core.services.interfaces;

import com.example.cper_core.dtos.equipa.*;
import com.example.cper_core.entities.Equipa;

import java.util.Set;

public interface IEquipaService extends IXService<
        EquipaDto,
        EquipaDetailsDto,
        EquipaDetailsExtendedDto,
        EquipaFiltroDto,
        Integer
        > {

    void linkToFuncionarios(Integer idEquipa, Set<Integer> idsFuncionarios);
    void unlinkFromFuncionarios(Integer idEquipa, Set<Integer> idsFuncionarios);

    void linkToAvarias(Integer idEquipa, Set<Integer> idsAvarias);
    void unlinkFromAvarias(Integer idEquipa, Set<Integer> idsAvarias);

    void linkToInspecoes(Integer idEquipa, Set<Integer> idsInspecoes);
    void unlinkFromInspecoes(Integer idEquipa, Set<Integer> idsInspecoes);
}
