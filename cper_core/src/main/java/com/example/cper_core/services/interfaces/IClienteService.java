package com.example.cper_core.services.interfaces;

import com.example.cper_core.dtos.cliente.*;

import java.util.Set;

public interface IClienteService extends IXService<
        ClienteDto,
        ClienteDetailsDto,
        ClienteDetailsExtendedDto,
        ClienteFiltroDto,
        ClienteWithRelationshipsDto,
        Integer
        > {
}