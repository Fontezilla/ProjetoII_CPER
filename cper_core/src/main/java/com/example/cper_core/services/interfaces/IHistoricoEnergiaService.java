package com.example.cper_core.services.interfaces;

import com.example.cper_core.dtos.historico_energia.HistoricoEnergiaDto;
import com.example.cper_core.dtos.historico_energia.HistoricoEnergiaDetailsDto;
import com.example.cper_core.dtos.historico_energia.HistoricoEnergiaDetailsExtendedDto;
import com.example.cper_core.dtos.historico_energia.HistoricoEnergiaFiltroDto;

public interface IHistoricoEnergiaService extends IXService<
        HistoricoEnergiaDto,
        HistoricoEnergiaDetailsDto,
        HistoricoEnergiaDetailsExtendedDto,
        HistoricoEnergiaFiltroDto,
        HistoricoEnergiaDetailsExtendedDto,
        Integer
        > {
}