package com.example.cper_core.mappers;

import com.example.cper_core.dtos.anomalia.AnomaliaDetailsExtendedDto;
import com.example.cper_core.dtos.endereco.*;
import com.example.cper_core.entities.Anomalia;
import com.example.cper_core.entities.Endereco;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EnderecoMapper {

    // -------- To DTO --------

    @Named("toDto")
    EnderecoDto toDto(Endereco entity);

    @IterableMapping(qualifiedByName = "toDto")
    List<EnderecoDto> toDtoList(List<Endereco> entities);

    @Named("toDetailsDto")
    EnderecoDetailsDto toDetailsDto(Endereco entity);

    @IterableMapping(qualifiedByName = "toDetailsDto")
    List<EnderecoDetailsDto> toDetailsDtoList(List<Endereco> entities);

    @Named("toWithArmazemDto")
    EnderecoWithArmazemDto toWithArmazemDto(Endereco entity);

    @IterableMapping(qualifiedByName = "toWithArmazemDto")
    List<EnderecoWithArmazemDto> toWithArmazemDtoList(List<Endereco> entities);

    @Named("toWithCentroDto")
    EnderecoWithCentroDto toWithCentroDto(Endereco entity);

    @IterableMapping(qualifiedByName = "toWithCentroDto")
    List<EnderecoWithCentroDto> toWithCentroDtoList(List<Endereco> entities);

    @Named("toWithClienteDto")
    EnderecoWithClienteDto toWithClienteDto(Endereco entity);

    @IterableMapping(qualifiedByName = "toWithClienteDto")
    List<EnderecoWithClienteDto> toWithClienteDtoList(List<Endereco> entities);

    @Named("toWithContratoDto")
    EnderecoWithContratoDto toWithContratoDto(Endereco entity);

    @IterableMapping(qualifiedByName = "toWithContratoDto")
    List<EnderecoWithContratoDto> toWithContratoDtoList(List<Endereco> entities);

    @Named("toWithFuncionarioDto")
    EnderecoWithFuncionarioDto toWithFuncionarioDto(Endereco entity);

    @IterableMapping(qualifiedByName = "toWithFuncionarioDto")
    List<EnderecoWithFuncionarioDto> toWithFuncionarioDtoList(List<Endereco> entities);

    // -------- To Entity --------

    @Named("toEntityFromDto")
    Endereco toEntity(EnderecoDto dto);

    @Named("toEntityFromDetails")
    Endereco toEntity(EnderecoDetailsDto dto);

    @Named("toEntityFromArmazem")
    Endereco toEntity(EnderecoWithArmazemDto dto);

    @Named("toEntityFromCentro")
    Endereco toEntity(EnderecoWithCentroDto dto);

    @Named("toEntityFromCliente")
    Endereco toEntity(EnderecoWithClienteDto dto);

    @Named("toEntityFromContrato")
    Endereco toEntity(EnderecoWithContratoDto dto);

    @Named("toEntityFromFuncionario")
    Endereco toEntity(EnderecoWithFuncionarioDto dto);

    // -------- Conversões de listas inversas --------

    @IterableMapping(qualifiedByName = "toEntityFromDto")
    List<Endereco> toEntityList(List<EnderecoDto> dtos);

    @IterableMapping(qualifiedByName = "toEntityFromDetails")
    List<Endereco> toEntityDetailsList(List<EnderecoDetailsDto> dtos);

    @IterableMapping(qualifiedByName = "toEntityFromArmazem")
    List<Endereco> toEntityWithArmazemList(List<EnderecoWithArmazemDto> dtos);

    @IterableMapping(qualifiedByName = "toEntityFromCentro")
    List<Endereco> toEntityWithCentroList(List<EnderecoWithCentroDto> dtos);

    @IterableMapping(qualifiedByName = "toEntityFromCliente")
    List<Endereco> toEntityWithClienteList(List<EnderecoWithClienteDto> dtos);

    @IterableMapping(qualifiedByName = "toEntityFromContrato")
    List<Endereco> toEntityWithContratoList(List<EnderecoWithContratoDto> dtos);

    @IterableMapping(qualifiedByName = "toEntityFromFuncionario")
    List<Endereco> toEntityWithFuncionarioList(List<EnderecoWithFuncionarioDto> dtos);

    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromExtendedDto(EnderecoDetailsDto dto, @MappingTarget Endereco entity);
}
