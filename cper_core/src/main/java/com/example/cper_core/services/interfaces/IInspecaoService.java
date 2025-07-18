package com.example.cper_core.services.interfaces;

import com.example.cper_core.dtos.inspecao.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Set;

public interface IInspecaoService extends IXService<
        InspecaoDto,
        InspecaoDetailsDto,
        InspecaoDetailsExtendedDto,
        InspecaoFiltroDto,
        Integer
        > {

    void linkToEquipas(Integer idInspecao, Set<Integer> idsEquipas);
    void unlinkFromEquipas(Integer idInspecao, Set<Integer> idsEquipas);
}