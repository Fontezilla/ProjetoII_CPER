package com.example.cper_core.mappers;

import com.example.cper_core.dtos.anomalia.AnomaliaDetailsExtendedDto;
import com.example.cper_core.dtos.material.*;
import com.example.cper_core.entities.Anomalia;
import com.example.cper_core.entities.Material;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MaterialMapper {

    // -------- To DTO --------

    @Named("toDto")
    MaterialDto toDto(Material entity);

    @IterableMapping(qualifiedByName = "toDto")
    List<MaterialDto> toDtoList(List<Material> entities);

    @Named("toDetailsDto")
    MaterialDetailsDto toDetailsDto(Material entity);

    @IterableMapping(qualifiedByName = "toDetailsDto")
    List<MaterialDetailsDto> toDetailsDtoList(List<Material> entities);

    @Named("toDetailsExtendedDto")
    MaterialDetailsExtendedDto toDetailsExtendedDto(Material entity);

    @IterableMapping(qualifiedByName = "toDetailsExtendedDto")
    List<MaterialDetailsExtendedDto> toDetailsExtendedDtoList(List<Material> entities);

    @Named("toWithLoteDto")
    MaterialWithLoteDto toWithLoteDto(Material entity);

    @IterableMapping(qualifiedByName = "toWithLoteDto")
    List<MaterialWithLoteDto> toWithLoteDtoList(List<Material> entities);

    @Named("toWithPedidoDto")
    MaterialWithPedidoMaterialDto toWithPedidoDto(Material entity);

    @IterableMapping(qualifiedByName = "toWithPedidoDto")
    List<MaterialWithPedidoMaterialDto> toWithPedidoDtoList(List<Material> entities);

    @Named("toWithSolicitacaoDto")
    MaterialWithSolicitacaoMaterialDto toWithSolicitacaoDto(Material entity);

    @IterableMapping(qualifiedByName = "toWithSolicitacaoDto")
    List<MaterialWithSolicitacaoMaterialDto> toWithSolicitacaoDtoList(List<Material> entities);

    // -------- To Entity --------

    @Named("toEntityFromDto")
    Material toEntity(MaterialDto dto);

    @Named("toEntityFromDetails")
    Material toEntity(MaterialDetailsDto dto);

    @Named("toEntityFromExtended")
    Material toEntity(MaterialDetailsExtendedDto dto);

    @Named("toEntityFromLote")
    Material toEntity(MaterialWithLoteDto dto);

    @Named("toEntityFromPedido")
    Material toEntity(MaterialWithPedidoMaterialDto dto);

    @Named("toEntityFromSolicitacao")
    Material toEntity(MaterialWithSolicitacaoMaterialDto dto);

    // -------- Conversões de listas inversas --------

    @IterableMapping(qualifiedByName = "toEntityFromDto")
    List<Material> toEntityList(List<MaterialDto> dtos);

    @IterableMapping(qualifiedByName = "toEntityFromDetails")
    List<Material> toEntityDetailsList(List<MaterialDetailsDto> dtos);

    @IterableMapping(qualifiedByName = "toEntityFromExtended")
    List<Material> toEntityExtendedList(List<MaterialDetailsExtendedDto> dtos);

    @IterableMapping(qualifiedByName = "toEntityFromLote")
    List<Material> toEntityWithLoteList(List<MaterialWithLoteDto> dtos);

    @IterableMapping(qualifiedByName = "toEntityFromPedido")
    List<Material> toEntityWithPedidoMaterialList(List<MaterialWithPedidoMaterialDto> dtos);

    @IterableMapping(qualifiedByName = "toEntityFromSolicitacao")
    List<Material> toEntityWithSolicitacaoMaterialList(List<MaterialWithSolicitacaoMaterialDto> dtos);

    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromExtendedDto(MaterialDetailsExtendedDto dto, @MappingTarget Material entity);
}
