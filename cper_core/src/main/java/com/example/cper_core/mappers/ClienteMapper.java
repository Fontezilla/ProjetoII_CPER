package com.example.cper_core.mappers;

import com.example.cper_core.dtos.anomalia.AnomaliaDetailsExtendedDto;
import com.example.cper_core.dtos.cliente.*;
import com.example.cper_core.entities.Anomalia;
import com.example.cper_core.entities.Cliente;
import com.example.cper_core.enums.CategoriaConsumo;
import com.example.cper_core.enums.EstadoCliente;
import com.example.cper_core.enums.TipoEnergiaRenovavel;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ClienteMapper {

    // -------- To DTO --------

    @Named("toDto")
    ClienteDto toDto(Cliente entity);

    @IterableMapping(qualifiedByName = "toDto")
    List<ClienteDto> toDtoList(List<Cliente> entities);

    @Named("toDetailsDto")
    @Mapping(source = "tipoEnergia", target = "tipoEnergia", qualifiedByName = "mapTipoEnergiaToString")
    ClienteDetailsDto toDetailsDto(Cliente entity);

    @IterableMapping(qualifiedByName = "toDetailsDto")
    List<ClienteDetailsDto> toDetailsDtoList(List<Cliente> entities);

    @Named("toDetailsExtendedDto")
    @Mapping(source = "catConsumo", target = "catConsumo", qualifiedByName = "mapCategoriaToString")
    @Mapping(source = "estado", target = "estado", qualifiedByName = "mapEstadoToString")
    ClienteDetailsExtendedDto toDetailsExtendedDto(Cliente entity);

    @IterableMapping(qualifiedByName = "toDetailsExtendedDto")
    List<ClienteDetailsExtendedDto> toDetailsExtendedDtoList(List<Cliente> entities);

    @Named("toWithPasswordDto")
    ClienteWithPasswordDto toWithPasswordDto(Cliente entity);

    @IterableMapping(qualifiedByName = "toWithPasswordDto")
    List<ClienteWithPasswordDto> toWithPasswordDtoList(List<Cliente> entities);

    @Named("toWithPerfilDto")
    ClienteWithPerfilDto toWithPerfilDto(Cliente entity);

    @IterableMapping(qualifiedByName = "toWithPerfilDto")
    List<ClienteWithPerfilDto> toWithPerfilDtoList(List<Cliente> entities);

    @Named("toWithSolicitacaoDto")
    ClienteWithSolicitacaoDto toWithSolicitacaoDto(Cliente entity);

    @IterableMapping(qualifiedByName = "toWithSolicitacaoDto")
    List<ClienteWithSolicitacaoDto> toWithSolicitacaoDtoList(List<Cliente> entities);

    @Named("toWithTicketDto")
    ClienteWithTicketDto toWithTicketDto(Cliente entity);

    @IterableMapping(qualifiedByName = "toWithTicketDto")
    List<ClienteWithTicketDto> toWithTicketDtoList(List<Cliente> entities);

    @Named("toLoginResponseDto")
    ClienteLoginResponseDto toLoginResponseDto(Cliente entity);

    // -------- To Entity --------

    Cliente toEntity(ClienteDto dto);

    @Mapping(source = "tipoEnergia", target = "tipoEnergia", qualifiedByName = "mapStringToTipoEnergia")
    Cliente toEntity(ClienteDetailsDto dto);

    @Mapping(source = "catConsumo", target = "catConsumo", qualifiedByName = "mapStringToCategoria")
    @Mapping(source = "estado", target = "estado", qualifiedByName = "mapStringToEstado")
    Cliente toEntity(ClienteDetailsExtendedDto dto);

    Cliente toEntity(ClienteWithPasswordDto dto);

    Cliente toEntity(ClienteWithPerfilDto dto);

    Cliente toEntity(ClienteWithSolicitacaoDto dto);

    Cliente toEntity(ClienteWithTicketDto dto);

    // -------- Métodos auxiliares --------

    @Named("mapTipoEnergiaToString")
    default String mapTipoEnergiaToString(Integer tipoEnergiaId) {
        if (tipoEnergiaId == null) return null;
        return TipoEnergiaRenovavel.fromId(tipoEnergiaId).name();
    }

    @Named("mapStringToTipoEnergia")
    default Integer mapStringToTipoEnergia(String tipoEnergiaName) {
        if (tipoEnergiaName == null) return null;
        return TipoEnergiaRenovavel.fromName(tipoEnergiaName).getId();
    }

    @Named("mapCategoriaToString")
    default String mapCategoriaToString(Integer categoriaId) {
        if (categoriaId == null) return null;
        return CategoriaConsumo.fromId(categoriaId).name();
    }

    @Named("mapStringToCategoria")
    default Integer mapStringToCategoria(String categoriaName) {
        if (categoriaName == null) return null;
        return CategoriaConsumo.fromName(categoriaName).getId();
    }

    @Named("mapEstadoToString")
    default String mapEstadoToString(Integer estadoId) {
        if (estadoId == null) return null;
        return EstadoCliente.fromId(estadoId).name();
    }

    @Named("mapStringToEstado")
    default Integer mapStringToEstado(String estadoName) {
        if (estadoName == null) return null;
        return EstadoCliente.fromName(estadoName).getId();
    }

    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromExtendedDto(ClienteDetailsExtendedDto dto, @MappingTarget Cliente entity);
}
