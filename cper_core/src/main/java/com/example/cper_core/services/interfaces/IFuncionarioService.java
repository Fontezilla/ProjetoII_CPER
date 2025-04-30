package com.example.cper_core.services.interfaces;

import com.example.cper_core.dtos.funcionario.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IFuncionarioService {

    // System operations

    Optional<FuncionarioLoginResponseDto> login(FuncionarioLoginDto loginDto);

    // Generic CRUD operations + Extra

    Page<FuncionarioDetailsDto> listAll(Pageable pageable);

    Page<FuncionarioDetailsDto> listFiltered(Pageable pageable, FuncionarioFiltroDto filter);

    Optional<FuncionarioDetailsExtendedDto> getById(Integer id);

    FuncionarioDetailsExtendedDto create(FuncionarioDetailsExtendedDto dto);

    FuncionarioDetailsExtendedDto update(Integer id, FuncionarioDetailsExtendedDto dto);

    void softDelete(Integer id);

    // Association operations (Responsibility)

    FuncionarioWithR_TicketDto linkRTicket(Integer idFuncionario, Integer idTicket);

    FuncionarioWithR_TicketDto unlinkRTicket(Integer idFuncionario, Integer idTicket);

    FuncionarioWithR_FaturaDto linkRFatura(Integer idFuncionario, Integer idFatura);

    FuncionarioWithR_FaturaDto unlinkRFatura(Integer idFuncionario, Integer idFatura);

    FuncionarioWithR_EquipaDto linkREquipa(Integer idFuncionario, Integer idEquipa);

    FuncionarioWithR_EquipaDto unlinkREquipa(Integer idFuncionario, Integer idEquipa);

    FuncionarioWithR_AnomaliaDto linkRAnomalia(Integer idFuncionario, Integer idAnomalia);

    FuncionarioWithR_AnomaliaDto unlinkRAnomalia(Integer idFuncionario, Integer idAnomalia);

    FuncionarioWithR_ArmazemDto linkRArmazem(Integer idFuncionario, Integer idArmazem);

    FuncionarioWithR_ArmazemDto unlinkRArmazem(Integer idFuncionario, Integer idArmazem);

    FuncionarioWithR_PedidoGeracaoDto linkRPedidoGeracao(Integer idFuncionario, Integer idPedidoGeracao);

    FuncionarioWithR_PedidoGeracaoDto unlinkRPedidoGeracao(Integer idFuncionario, Integer idPedidoGeracao);

    // Association operations (Participation)


    FuncionarioWithContratoDto linkContrato(Integer idFuncionario, Integer idContrato);

    FuncionarioWithContratoDto unlinkContrato(Integer idFuncionario, Integer idContrato);

    FuncionarioWithArmazemDto linkArmazem(Integer idFuncionario, Integer idArmazem);

    FuncionarioWithArmazemDto unlinkArmazem(Integer idFuncionario, Integer idArmazem);

    FuncionarioWithCentroProducaoDto linkCentroProducao(Integer idFuncionario, Integer idCentroProducao);

    FuncionarioWithCentroProducaoDto unlinkCentroProducao(Integer idFuncionario, Integer idCentroProducao);

    FuncionarioWithDepartamentoDto linkDepartamento(Integer idFuncionario, Integer idDepartamento);

    FuncionarioWithDepartamentoDto unlinkDepartamento(Integer idFuncionario, Integer idDepartamento);

    FuncionarioWithEquipaDto linkEquipa(Integer idFuncionario, Integer idEquipa);

    FuncionarioWithEquipaDto unlinkEquipa(Integer idFuncionario, Integer idEquipa);

    FuncionarioWithSolicitacaoDto linkSolicitacao(Integer idFuncionario, Integer idSolicitacao);

    FuncionarioWithSolicitacaoDto unlinkSolicitacao(Integer idFuncionario, Integer idSolicitacao);
}