package com.example.cper_core.mappers;

import com.example.cper_core.dtos.fatura.*;
import com.example.cper_core.entities.Fatura;
import org.mapstruct.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class FaturaMapper {

    // --- To DTO ---
    @Named("toDto")
    public abstract FaturaDto toDto(Fatura entity);

    @IterableMapping(qualifiedByName = "toDto")
    public abstract List<FaturaDto> toDtoList(List<Fatura> entities);

    // --- To Details DTO ---
    @Named("toDetailsDto")
    public abstract FaturaDetailsDto toDetailsDto(Fatura entity);

    @IterableMapping(qualifiedByName = "toDetailsDto")
    public abstract List<FaturaDetailsDto> toDetailsDtoList(List<Fatura> entities);

    // --- To Extended DTO ---
    @Named("toExtendedDto")
    public abstract FaturaDetailsExtendedDto toExtendedDto(Fatura entity);

    @IterableMapping(qualifiedByName = "toExtendedDto")
    public abstract List<FaturaDetailsExtendedDto> toExtendedDtoList(List<Fatura> entities);

    // --- To WithRelationships DTO ---
    @Named("toWithRelationshipsDto")
    public abstract FaturaWithRelationshipsDto toWithRelationshipsDto(Fatura entity);

    @IterableMapping(qualifiedByName = "toWithRelationshipsDto")
    public abstract List<FaturaWithRelationshipsDto> toWithRelationshipsDtoList(List<Fatura> entities);

    // --- To Entity ---
    public abstract Fatura toEntity(FaturaDto dto);

    public abstract Fatura toEntity(FaturaDetailsDto dto);

    public abstract Fatura toEntity(FaturaDetailsExtendedDto dto);

    public abstract Fatura toEntity(FaturaWithRelationshipsDto dto);

    // --- Partial Update ---
    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void updateEntityFromExtendedDto(FaturaDetailsExtendedDto dto, @MappingTarget Fatura entity);

    // --- Pós-processamento para calcular o valor total ---
    @AfterMapping
    protected void calcularValorTotal(Fatura entity, @MappingTarget FaturaDetailsDto dto) {
        if (entity == null || dto == null) return;

        BigDecimal valorUnitario = entity.getVElectricidade() != null ? entity.getVElectricidade() : BigDecimal.ZERO;
        BigDecimal quantidade = entity.getQtdEnergia() != null ? entity.getQtdEnergia() : BigDecimal.ZERO;
        BigDecimal iva = entity.getTaxa() != null
                ? BigDecimal.valueOf(entity.getTaxa()).divide(BigDecimal.valueOf(100), 6, RoundingMode.HALF_UP)
                : BigDecimal.ZERO;
        BigDecimal multa = entity.getVMulta() != null ? entity.getVMulta() : BigDecimal.ZERO;

        BigDecimal base = valorUnitario.multiply(quantidade);
        BigDecimal comIva = base.multiply(BigDecimal.ONE.add(iva));

        dto.setValorTotal(comIva.add(multa).setScale(2, RoundingMode.HALF_UP));
    }
}