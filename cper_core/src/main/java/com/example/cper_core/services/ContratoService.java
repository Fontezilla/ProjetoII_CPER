package com.example.cper_core.services;

import com.example.cper_core.dtos.contrato.*;
import com.example.cper_core.entities.*;
import com.example.cper_core.mappers.ContratoMapper;
import com.example.cper_core.repositories.*;
import com.example.cper_core.services.interfaces.IContratoService;
import com.example.cper_core.specifications.ContratoSpecification;
import jakarta.validation.Validator;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ContratoService extends AbstractXService<
        Contrato,
        ContratoDto,
        ContratoDetailsDto,
        ContratoDetailsExtendedDto,
        ContratoFiltroDto,
        ContratoWithRelationshipsDto,
        Integer
        > implements IContratoService {

    private final ContratoMapper contratoMapper;

    public ContratoService(
            ContratoRepository contratoRepository,
            ContratoMapper contratoMapper,
            Validator validator
    ) {
        super(contratoRepository, contratoRepository, validator);
        this.contratoMapper = contratoMapper;
    }

    @Override
    protected Contrato toEntity(ContratoDetailsExtendedDto dto) {
        return contratoMapper.toEntity(dto);
    }

    @Override
    protected void updateEntityFromDto(ContratoDetailsExtendedDto dto, Contrato entity) {
        contratoMapper.updateEntityFromExtendedDto(dto, entity);
    }

    @Override
    protected ContratoDetailsExtendedDto toExtendedDto(Contrato entity) {
        return contratoMapper.toExtendedDto(entity);
    }

    @Override
    protected ContratoDetailsDto toDetailsDto(Contrato entity) {
        return contratoMapper.toDetailsDto(entity);
    }

    @Override
    protected Specification<Contrato> getSpecificationFromFiltro(ContratoFiltroDto filtro) {
        return ContratoSpecification.filter(filtro);
    }

    @Override
    protected void marcarComoEliminado(Contrato entity) {
        entity.setIsDeleted(true);
    }
}
