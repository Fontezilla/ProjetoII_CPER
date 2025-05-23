package com.example.cper_core.services.interfaces;

import com.example.cper_core.dtos.resposta.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IRespostaService extends IXService<
        RespostaDto,
        RespostaDetailsDto,
        RespostaDetailsExtendedDto,
        RespostaFiltroDto,
        RespostaWithRelationshipsDto,
        Integer
        > {
}