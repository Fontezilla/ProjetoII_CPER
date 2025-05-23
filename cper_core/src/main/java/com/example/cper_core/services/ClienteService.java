package com.example.cper_core.services;

import com.example.cper_core.dtos.cliente.*;
import com.example.cper_core.entities.*;
import com.example.cper_core.mappers.ClienteMapper;
import com.example.cper_core.repositories.*;
import com.example.cper_core.services.interfaces.IClienteService;
import com.example.cper_core.specifications.ClienteSpecification;
import jakarta.validation.Validator;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ClienteService extends AbstractXService<
        Cliente,
        ClienteDto,
        ClienteDetailsDto,
        ClienteDetailsExtendedDto,
        ClienteFiltroDto,
        ClienteWithRelationshipsDto,
        Integer
        > implements IClienteService {

    private final ClienteMapper clienteMapper;

    public ClienteService(
            ClienteRepository clienteRepository,
            ClienteMapper clienteMapper,
            Validator validator
    ) {
        super(clienteRepository, clienteRepository, validator);
        this.clienteMapper = clienteMapper;

    }

    @Override
    protected Cliente toEntity(ClienteDetailsExtendedDto dto) {
        return clienteMapper.toEntity(dto);
    }

    @Override
    protected void updateEntityFromDto(ClienteDetailsExtendedDto dto, Cliente entity) {
        clienteMapper.updateEntityFromExtendedDto(dto, entity);
    }

    @Override
    protected ClienteDetailsExtendedDto toExtendedDto(Cliente entity) {
        return clienteMapper.toExtendedDto(entity);
    }

    @Override
    protected ClienteDetailsDto toDetailsDto(Cliente entity) {
        return clienteMapper.toDetailsDto(entity);
    }

    @Override
    protected Specification<Cliente> getSpecificationFromFiltro(ClienteFiltroDto filtro) {
        return ClienteSpecification.filter(filtro);
    }

    @Override
    protected void marcarComoEliminado(Cliente entity) {
        entity.setIsDeleted(true);
    }
}
