package com.example.cper_core.mappers;

import com.example.cper_core.dtos.stock.*;
import com.example.cper_core.entities.Stock;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StockMapper {

    // -------- To DTO --------

    @Named("toDto")
    StockDto toDto(Stock entity);

    @IterableMapping(qualifiedByName = "toDto")
    List<StockDto> toDtoList(List<Stock> entities);

    @Named("toDetailsDto")
    StockDetailsDto toDetailsDto(Stock entity);

    @IterableMapping(qualifiedByName = "toDetailsDto")
    List<StockDetailsDto> toDetailsDtoList(List<Stock> entities);

    @Named("toWithArmazemDto")
    StockWithArmazemDto toWithArmazemDto(Stock entity);

    @IterableMapping(qualifiedByName = "toWithArmazemDto")
    List<StockWithArmazemDto> toWithArmazemDtoList(List<Stock> entities);

    @Named("toWithLoteDto")
    StockWithLoteDto toWithLoteDto(Stock entity);

    @IterableMapping(qualifiedByName = "toWithLoteDto")
    List<StockWithLoteDto> toWithLoteDtoList(List<Stock> entities);

    // -------- To Entity --------

    @Named("toEntity")
    Stock toEntity(StockDto dto);

    @Named("toEntity")
    Stock toEntity(StockDetailsDto dto);

    @Named("toEntity")
    Stock toEntity(StockWithArmazemDto dto);

    @Named("toEntity")
    Stock toEntity(StockWithLoteDto dto);

    // -------- Conversões de listas inversas --------

    @IterableMapping(qualifiedByName = "toEntity")
    List<Stock> toEntityList(List<StockDto> dtos);

    @IterableMapping(qualifiedByName = "toEntity")
    List<Stock> toEntityDetailsList(List<StockDetailsDto> dtos);

    @IterableMapping(qualifiedByName = "toEntity")
    List<Stock> toEntityWithArmazemList(List<StockWithArmazemDto> dtos);

    @IterableMapping(qualifiedByName = "toEntity")
    List<Stock> toEntityWithLoteList(List<StockWithLoteDto> dtos);

    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromExtendedDto(StockDetailsDto dto, @MappingTarget Stock entity);
}
