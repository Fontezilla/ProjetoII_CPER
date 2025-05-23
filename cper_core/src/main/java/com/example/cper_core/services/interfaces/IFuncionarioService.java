package com.example.cper_core.services.interfaces;

import com.example.cper_core.dtos.funcionario.*;

public interface IFuncionarioService extends IXService<
        FuncionarioDto,
        FuncionarioDetailsDto,
        FuncionarioDetailsExtendedDto,
        FuncionarioFiltroDto,
        FuncionarioWithRelationshipsDto,
        Integer
        > {
}
