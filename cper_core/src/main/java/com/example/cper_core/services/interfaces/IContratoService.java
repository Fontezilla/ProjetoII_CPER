package com.example.cper_core.services.interfaces;

import com.example.cper_core.dtos.contrato.*;

import java.util.Set;

public interface IContratoService extends IXService<
        ContratoDto,
        ContratoDetailsDto,
        ContratoDetailsExtendedDto,
        ContratoFiltroDto,
        ContratoWithRelationshipsDto,
        Integer
        > {
}