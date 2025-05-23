package com.example.cper_core.services;

import com.example.cper_core.dtos.lote.*;
import com.example.cper_core.entities.*;
import com.example.cper_core.mappers.LoteMapper;
import com.example.cper_core.repositories.*;
import com.example.cper_core.services.interfaces.ILoteService;
import com.example.cper_core.specifications.LoteSpecification;
import jakarta.validation.Validator;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LoteService extends AbstractXService<
        Lote,
        LoteDto,
        LoteDetailsDto,
        LoteDetailsExtendedDto,
        LoteFiltroDto,
        LoteWithRelationshipsDto,
        Integer
        > implements ILoteService {

    private final LoteMapper loteMapper;

    public LoteService(
            LoteRepository loteRepository,
            LoteMapper loteMapper,
            Validator validator
    ) {
        super(loteRepository, loteRepository, validator);
        this.loteMapper = loteMapper;
    }

    @Override
    protected Lote toEntity(LoteDetailsExtendedDto dto) {
        return loteMapper.toEntity(dto);
    }

    @Override
    protected void updateEntityFromDto(LoteDetailsExtendedDto dto, Lote entity) {
        loteMapper.updateEntityFromExtendedDto(dto, entity);
    }

    @Override
    protected LoteDetailsExtendedDto toExtendedDto(Lote entity) {
        return loteMapper.toExtendedDto(entity);
    }

    @Override
    protected LoteDetailsDto toDetailsDto(Lote entity) {
        return loteMapper.toDetailsDto(entity);
    }

    @Override
    protected Specification<Lote> getSpecificationFromFiltro(LoteFiltroDto filtro) {
        return LoteSpecification.filter(filtro);
    }

    @Override
    protected void marcarComoEliminado(Lote entity) {
        entity.setIsDeleted(true);
    }
}