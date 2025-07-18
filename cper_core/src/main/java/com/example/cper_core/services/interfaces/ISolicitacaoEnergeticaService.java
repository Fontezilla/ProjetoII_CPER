
package com.example.cper_core.services.interfaces;

import com.example.cper_core.dtos.solicitacao_energetica.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Set;

public interface ISolicitacaoEnergeticaService extends IXService<
        SolicitacaoEnergeticaDto,
        SolicitacaoEnergeticaDetailsDto,
        SolicitacaoEnergeticaDetailsExtendedDto,
        SolicitacaoEnergeticaFiltroDto,
        Integer
        > {

    void linkToFuncionarios(Integer idSolicitacaoEnergetica, Set<Integer> idsFuncionarios);
    void unlinkFromFuncionarios(Integer idSolicitacaoEnergetica, Set<Integer> idsFuncionarios);
}