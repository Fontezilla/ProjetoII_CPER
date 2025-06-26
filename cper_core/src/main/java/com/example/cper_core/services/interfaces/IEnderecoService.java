package com.example.cper_core.services.interfaces;

import com.example.cper_core.dtos.endereco.*;

public interface IEnderecoService extends IXService<
        EnderecoDto,
        EnderecoDetailsDto,
        EnderecoDetailsExtendedDto,
        EnderecoFiltroDto,
        EnderecoWithRelationshipsDto,
        Integer
        > {

    EnderecoDetailsExtendedDto findByRuaAndLocalidade(String rua, Integer localidadeId);
}
