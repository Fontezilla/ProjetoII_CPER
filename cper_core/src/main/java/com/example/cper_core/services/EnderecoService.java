package com.example.cper_core.services;

import com.example.cper_core.dtos.endereco.*;
import com.example.cper_core.entities.*;
import com.example.cper_core.mappers.EnderecoMapper;
import com.example.cper_core.repositories.*;
import com.example.cper_core.services.interfaces.IEnderecoService;
import com.example.cper_core.specifications.EnderecoSpecification;
import jakarta.validation.Validator;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EnderecoService extends AbstractXService<
        Endereco,
        EnderecoDto,
        EnderecoDetailsDto,
        EnderecoDetailsExtendedDto,
        EnderecoFiltroDto,
        EnderecoWithRelationshipsDto,
        Integer
        > implements IEnderecoService {

    private final EnderecoMapper enderecoMapper;
    public EnderecoService(
            EnderecoRepository enderecoRepository,
            EnderecoMapper enderecoMapper,
            Validator validator
    ) {
        super(enderecoRepository, enderecoRepository, validator);
        this.enderecoMapper = enderecoMapper;
    }

    @Override
    protected Endereco toEntity(EnderecoDetailsExtendedDto dto) {
        return enderecoMapper.toEntity(dto);
    }

    @Override
    protected void updateEntityFromDto(EnderecoDetailsExtendedDto dto, Endereco entity) {
        enderecoMapper.updateEntityFromExtendedDto(dto, entity);
    }

    @Override
    protected EnderecoDetailsExtendedDto toExtendedDto(Endereco entity) {
        return enderecoMapper.toExtendedDto(entity);
    }

    @Override
    protected EnderecoDetailsDto toDetailsDto(Endereco entity) {
        return enderecoMapper.toDetailsDto(entity);
    }

    @Override
    protected Specification<Endereco> getSpecificationFromFiltro(EnderecoFiltroDto filtro) {
        return EnderecoSpecification.filter(filtro);
    }

    @Override
    protected void marcarComoEliminado(Endereco entity) {
        throw new UnsupportedOperationException("Soft delete não suportado para Endereco.");
    }

    @Override
    protected void validateBeforeDeleting(Endereco entity) {
        if (!entity.getClientes().isEmpty()
                || !entity.getFuncionarios().isEmpty()
                || !entity.getCentrosProducao().isEmpty()
                || !entity.getContratos().isEmpty()
                || !entity.getArmazens().isEmpty()) {
            throw new IllegalStateException("O endereço está associado a outras entidades e não pode ser eliminado.");
        }
    }
}