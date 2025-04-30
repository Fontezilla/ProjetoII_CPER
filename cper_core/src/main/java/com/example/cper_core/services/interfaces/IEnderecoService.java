package com.example.cper_core.services.interfaces;

import com.example.cper_core.dtos.endereco.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IEnderecoService {

    // Generic CRUD operations

    Page<EnderecoDetailsDto> listAll(Pageable pageable);

    Page<EnderecoDetailsDto> listFiltered(Pageable pageable, EnderecoFiltroDto filter);

    Optional<EnderecoDetailsDto> getById(Integer id);

    EnderecoDetailsDto create(EnderecoDetailsDto dto);

    EnderecoDetailsDto update(Integer id, EnderecoDetailsDto dto);

    void softDelete(Integer id);

    // Association operations: Armazem

    EnderecoWithArmazemDto linkArmazem(Integer idEndereco, Integer idArmazem);

    EnderecoWithArmazemDto unlinkArmazem(Integer idEndereco, Integer idArmazem);

    // Association operations: Centro Producao

    EnderecoWithCentroDto linkCentro(Integer idEndereco, Integer idCentro);

    EnderecoWithCentroDto unlinkCentro(Integer idEndereco, Integer idCentro);

    // Association operations: Cliente

    EnderecoWithClienteDto linkCliente(Integer idEndereco, Integer idCliente);

    EnderecoWithClienteDto unlinkCliente(Integer idEndereco, Integer idCliente);

    // Association operations: Contrato

    EnderecoWithContratoDto linkContrato(Integer idEndereco, Integer idContrato);

    EnderecoWithContratoDto unlinkContrato(Integer idEndereco, Integer idContrato);

    // Association operations: Funcionario

    EnderecoWithFuncionarioDto linkFuncionario(Integer idEndereco, Integer idFuncionario);

    EnderecoWithFuncionarioDto unlinkFuncionario(Integer idEndereco, Integer idFuncionario);
}
