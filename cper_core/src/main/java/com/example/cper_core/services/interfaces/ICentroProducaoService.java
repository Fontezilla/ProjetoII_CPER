package com.example.cper_core.services.interfaces;

import com.example.cper_core.dtos.centro_producao.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ICentroProducaoService {

    // Generic CRUD operations

    Page<CentroProducaoDetailsDto> listAll(Pageable pageable);

    Page<CentroProducaoDetailsDto> listFiltered(Pageable pageable, CentroProducaoFiltroDto filter);

    Optional<CentroProducaoDetailsExtendedDto> getById(Integer id);

    CentroProducaoDetailsExtendedDto create(CentroProducaoDetailsExtendedDto dto);

    CentroProducaoDetailsExtendedDto update(Integer id, CentroProducaoDetailsExtendedDto dto);

    void softDelete(Integer id);

    // Association operations: Inspecao

    CentroProducaoWithInspecaoDto linkInspecao(Integer idCentroProducao, Integer idInspecao);

    CentroProducaoWithInspecaoDto unlinkInspecao(Integer idCentroProducao, Integer idInspecao);

    // Association operations: Avaria

    CentroProducaoWithAvariaDto linkAvaria(Integer idCentroProducao, Integer idAvaria);

    CentroProducaoWithAvariaDto unlinkAvaria(Integer idCentroProducao, Integer idAvaria);

    // Association operations: Anomalia

    CentroProducaoWithAnomaliaDto linkAnomalia(Integer idCentroProducao, Integer idAnomalia);

    CentroProducaoWithAnomaliaDto unlinkAnomalia(Integer idCentroProducao, Integer idAnomalia);

    // Association operations: Funcionario

    CentroProducaoWithFuncionarioDto linkFuncionario(Integer idCentroProducao, Integer idFuncionario);

    CentroProducaoWithFuncionarioDto unlinkFuncionario(Integer idCentroProducao, Integer idFuncionario);

    // Association operations: PedidoGeracao

    CentroProducaoWithPedidoGeracaoDto linkPedidoGeracao(Integer idCentroProducao, Integer idPedidoGeracao);

    CentroProducaoWithPedidoGeracaoDto unlinkPedidoGeracao(Integer idCentroProducao, Integer idPedidoGeracao);
}
