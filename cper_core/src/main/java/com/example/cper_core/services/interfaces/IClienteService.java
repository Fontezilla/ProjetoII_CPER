package com.example.cper_core.services.interfaces;

import com.example.cper_core.dtos.cliente.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IClienteService {

    // System operations

    Optional<ClienteLoginResponseDto> login(ClienteLoginDto loginDto);

    // Generic CRUD operations

    Page<ClienteDetailsDto> listAll(Pageable pageable);

    Page<ClienteDetailsDto> listFiltered(Pageable pageable, ClienteFiltroDto filter);

    Optional<ClienteDetailsExtendedDto> getById(Integer id);

    ClienteDetailsExtendedDto create(ClienteDetailsExtendedDto dto);

    ClienteDetailsExtendedDto update(Integer id, ClienteDetailsExtendedDto dto);

    void softDelete(Integer id);

    // Association operations: Password

    ClienteWithPasswordDto updatePassword(Integer idCliente, ClienteWithPasswordDto passwordDto);

    // Association operations: Perfil

    ClienteWithPerfilDto linkPerfil(Integer idCliente, Integer idPerfil);

    ClienteWithPerfilDto unlinkPerfil(Integer idCliente, Integer idPerfil);

    // Association operations: Solicitacao

    ClienteWithSolicitacaoDto linkSolicitacao(Integer idCliente, Integer idSolicitacao);

    ClienteWithSolicitacaoDto unlinkSolicitacao(Integer idCliente, Integer idSolicitacao);

    // Association operations: Ticket

    ClienteWithTicketDto linkTicket(Integer idCliente, Integer idTicket);

    ClienteWithTicketDto unlinkTicket(Integer idCliente, Integer idTicket);
}
