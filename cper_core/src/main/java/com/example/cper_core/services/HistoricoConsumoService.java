package com.example.cper_core.services;

import com.example.cper_core.dtos.historico_consumo.*;
import com.example.cper_core.entities.*;
import com.example.cper_core.mappers.HistoricoConsumoMapper;
import com.example.cper_core.repositories.HistoricoConsumoRepository;
import com.example.cper_core.services.interfaces.IHistoricoConsumoService;
import com.example.cper_core.specifications.HistoricoConsumoSpecification;
import jakarta.validation.Validator;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class HistoricoConsumoService extends AbstractXService<
        HistoricoConsumo,
        HistoricoConsumoDto,
        HistoricoConsumoDetailsDto,
        HistoricoConsumoDetailsExtendedDto,
        HistoricoConsumoFiltroDto,
        HistoricoConsumoWithRelationshipsDto,
        Integer
        > implements IHistoricoConsumoService {

    private final HistoricoConsumoMapper historicoMapper;

    public HistoricoConsumoService(
            HistoricoConsumoRepository historicoRepository,
            HistoricoConsumoMapper historicoMapper,
            Validator validator
    ) {
        super(historicoRepository, historicoRepository, validator);
        this.historicoMapper = historicoMapper;
    }

    @Override
    protected HistoricoConsumo toEntity(HistoricoConsumoDetailsExtendedDto dto) {
        return historicoMapper.toEntity(dto);
    }

    @Override
    protected void updateEntityFromDto(HistoricoConsumoDetailsExtendedDto dto, HistoricoConsumo entity) {
        historicoMapper.updateEntityFromExtendedDto(dto, entity);
    }

    @Override
    protected HistoricoConsumoDetailsExtendedDto toExtendedDto(HistoricoConsumo entity) {
        return historicoMapper.toExtendedDto(entity);
    }

    @Override
    protected HistoricoConsumoDetailsDto toDetailsDto(HistoricoConsumo entity) {
        return historicoMapper.toDetailsDto(entity);
    }

    @Override
    protected Specification<HistoricoConsumo> getSpecificationFromFiltro(HistoricoConsumoFiltroDto filtro) {
        return HistoricoConsumoSpecification.filter(filtro);
    }

    @Override
    protected void marcarComoEliminado(HistoricoConsumo entity) {
        entity.setIsDeleted(true);
    }
}