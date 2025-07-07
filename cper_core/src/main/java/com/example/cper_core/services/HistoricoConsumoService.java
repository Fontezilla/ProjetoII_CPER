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

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.OffsetDateTime;
import java.util.List;

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

    public HistoricoConsumoMetricasDto calcularMetricas(HistoricoConsumoFiltroDto filtro) {
        List<HistoricoConsumo> dados = specRepository.findAll(getSpecificationFromFiltro(filtro));

        HistoricoConsumoMetricasDto dto = new HistoricoConsumoMetricasDto();

        if (dados.isEmpty()) return dto;

        BigDecimal energiaTotal = BigDecimal.ZERO;
        BigDecimal consumoTotal = BigDecimal.ZERO;
        BigDecimal energiaMax = null;
        BigDecimal energiaMin = null;
        OffsetDateTime dataMin = null;
        OffsetDateTime dataMax = null;

        for (HistoricoConsumo h : dados) {
            if (h.getEnergiaTotal() != null) {
                energiaTotal = energiaTotal.add(h.getEnergiaTotal());

                if (energiaMax == null || h.getEnergiaTotal().compareTo(energiaMax) > 0)
                    energiaMax = h.getEnergiaTotal();

                if (energiaMin == null || h.getEnergiaTotal().compareTo(energiaMin) < 0)
                    energiaMin = h.getEnergiaTotal();
            }

            if (h.getConsumoPorHora() != null)
                consumoTotal = consumoTotal.add(h.getConsumoPorHora());

            OffsetDateTime data = h.getDataRegisto();
            if (dataMin == null || data.isBefore(dataMin)) dataMin = data;
            if (dataMax == null || data.isAfter(dataMax)) dataMax = data;
        }

        dto.setTotalRegistos(dados.size());
        dto.setEnergiaTotal(energiaTotal);
        dto.setEnergiaMaxima(energiaMax);
        dto.setEnergiaMinima(energiaMin);
        dto.setDataMaisAntiga(dataMin);
        dto.setDataMaisRecente(dataMax);

        dto.setConsumoMedioPorHora(
                consumoTotal.divide(BigDecimal.valueOf(dados.size()), 2, RoundingMode.HALF_UP)
        );

        return dto;
    }
}