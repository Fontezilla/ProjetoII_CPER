package com.example.cper_core.services;

import com.example.cper_core.dtos.fatura.*;
import com.example.cper_core.entities.*;
import com.example.cper_core.mappers.FaturaMapper;
import com.example.cper_core.repositories.*;
import com.example.cper_core.services.interfaces.IFaturaService;
import com.example.cper_core.specifications.FaturaSpecification;
import jakarta.validation.Validator;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FaturaService extends AbstractXService<
        Fatura,
        FaturaDto,
        FaturaDetailsDto,
        FaturaDetailsExtendedDto,
        FaturaFiltroDto,
        FaturaWithRelationshipsDto,
        Integer
        > implements IFaturaService {

    private final FaturaMapper faturaMapper;

    public FaturaService(
            FaturaRepository faturaRepository,
            FaturaMapper faturaMapper,
            Validator validator
    ) {
        super(faturaRepository, faturaRepository, validator);
        this.faturaMapper = faturaMapper;
    }

    @Override
    protected Fatura toEntity(FaturaDetailsExtendedDto dto) {
        return faturaMapper.toEntity(dto);
    }

    @Override
    protected void updateEntityFromDto(FaturaDetailsExtendedDto dto, Fatura entity) {
        faturaMapper.updateEntityFromExtendedDto(dto, entity);
    }

    @Override
    protected FaturaDetailsExtendedDto toExtendedDto(Fatura entity) {
        return faturaMapper.toExtendedDto(entity);
    }

    @Override
    protected FaturaDetailsDto toDetailsDto(Fatura entity) {
        return faturaMapper.toDetailsDto(entity);
    }

    @Override
    protected Specification<Fatura> getSpecificationFromFiltro(FaturaFiltroDto filtro) {
        return FaturaSpecification.filter(filtro);
    }

    @Override
    protected void marcarComoEliminado(Fatura entity) {
        entity.setIsDeleted(true);
    }
}