package com.example.cper_core.mappers;

import com.example.cper_core.dtos.anomalia.AnomaliaDetailsExtendedDto;
import com.example.cper_core.dtos.funcionario.*;
import com.example.cper_core.entities.Anomalia;
import com.example.cper_core.entities.Funcionario;
import com.example.cper_core.enums.EstadoFatura;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FuncionarioMapper {

    // -------- To DTO --------

    @Named("toDto")
    FuncionarioDto toDto(Funcionario entity);

    @IterableMapping(qualifiedByName = "toDto")
    List<FuncionarioDto> toDtoList(List<Funcionario> entities);

    @Named("toDetailsDto")
    @Mapping(source = "estado", target = "estado", qualifiedByName = "mapEstadoToString")
    FuncionarioDetailsDto toDetailsDto(Funcionario entity);

    @IterableMapping(qualifiedByName = "toDetailsDto")
    List<FuncionarioDetailsDto> toDetailsDtoList(List<Funcionario> entities);

    @Named("toDetailsExtendedDto")
    FuncionarioDetailsExtendedDto toDetailsExtendedDto(Funcionario entity);

    @IterableMapping(qualifiedByName = "toDetailsExtendedDto")
    List<FuncionarioDetailsExtendedDto> toDetailsExtendedDtoList(List<Funcionario> entities);

    @Named("toWithArmazemDto")
    FuncionarioWithArmazemDto toWithArmazemDto(Funcionario entity);

    @IterableMapping(qualifiedByName = "toWithArmazemDto")
    List<FuncionarioWithArmazemDto> toWithArmazemDtoList(List<Funcionario> entities);

    @Named("toWithR_ArmazemDto")
    FuncionarioWithR_ArmazemDto toWithR_ArmazemDto(Funcionario entity);

    @IterableMapping(qualifiedByName = "toWithR_ArmazemDto")
    List<FuncionarioWithR_ArmazemDto> toWithR_ArmazemDtoList(List<Funcionario> entities);

    @Named("toWithCentroDto")
    FuncionarioWithCentroProducaoDto toWithCentroDto(Funcionario entity);

    @IterableMapping(qualifiedByName = "toWithCentroDto")
    List<FuncionarioWithCentroProducaoDto> toWithCentroDtoList(List<Funcionario> entities);

    @Named("toWithContratoDto")
    FuncionarioWithContratoDto toWithContratoDto(Funcionario entity);

    @IterableMapping(qualifiedByName = "toWithContratoDto")
    List<FuncionarioWithContratoDto> toWithContratoDtoList(List<Funcionario> entities);

    @Named("toWithEquipaDto")
    FuncionarioWithEquipaDto toWithEquipaDto(Funcionario entity);

    @IterableMapping(qualifiedByName = "toWithEquipaDto")
    List<FuncionarioWithEquipaDto> toWithEquipaDtoList(List<Funcionario> entities);

    @Named("toWithPasswordDto")
    FuncionarioWithPasswordDto toWithPasswordDto(Funcionario entity);

    @IterableMapping(qualifiedByName = "toWithPasswordDto")
    List<FuncionarioWithPasswordDto> toWithPasswordDtoList(List<Funcionario> entities);

    @Named("toWithDepartamentosDto")
    FuncionarioWithDepartamentoDto toWithDepartamentosDto(Funcionario entity);

    @IterableMapping(qualifiedByName = "toWithDepartamentosDto")
    List<FuncionarioWithDepartamentoDto> toWithDepartamentosDtoList(List<Funcionario> entities);

    @Named("toWithR_AnomaliaDto")
    FuncionarioWithR_AnomaliaDto toWithR_AnomaliaDto(Funcionario entity);

    @IterableMapping(qualifiedByName = "toWithR_AnomaliaDto")
    List<FuncionarioWithR_AnomaliaDto> toWithR_AnomaliaDtoList(List<Funcionario> entities);

    @Named("toWithR_EquipaDto")
    FuncionarioWithR_EquipaDto toWithR_EquipaDto(Funcionario entity);

    @IterableMapping(qualifiedByName = "toWithR_EquipaDto")
    List<FuncionarioWithR_EquipaDto> toWithR_EquipaDtoList(List<Funcionario> entities);

    @Named("toWithR_FaturaDto")
    FuncionarioWithR_FaturaDto toWithR_FaturaDto(Funcionario entity);

    @IterableMapping(qualifiedByName = "toWithR_FaturaDto")
    List<FuncionarioWithR_FaturaDto> toWithR_FaturaDtoList(List<Funcionario> entities);

    @Named("toWithR_PedidoDto")
    FuncionarioWithR_PedidoGeracaoDto toWithR_PedidoDto(Funcionario entity);

    @IterableMapping(qualifiedByName = "toWithR_PedidoDto")
    List<FuncionarioWithR_PedidoGeracaoDto> toWithR_PedidoDtoList(List<Funcionario> entities);

    @Named("toWithR_TicketDto")
    FuncionarioWithR_TicketDto toWithR_TicketDto(Funcionario entity);

    @IterableMapping(qualifiedByName = "toWithR_TicketDto")
    List<FuncionarioWithR_TicketDto> toWithR_TicketDtoList(List<Funcionario> entities);

    @Named("toWithSolicitacaoDto")
    FuncionarioWithSolicitacaoDto toWithSolicitacaoDto(Funcionario entity);

    @IterableMapping(qualifiedByName = "toWithSolicitacaoDto")
    List<FuncionarioWithSolicitacaoDto> toWithSolicitacaoDtoList(List<Funcionario> entities);

    @Named("toWithComentarioDto")
    FuncionarioWithComentarioDto toWithComentarioDto(Funcionario entity);

    @IterableMapping(qualifiedByName = "toWithComentarioDto")
    List<FuncionarioWithComentarioDto> toWithComentarioDtoList(List<Funcionario> entities);

    @Named("toTokenDto")
    FuncionarioTokenDto toTokenDto(Funcionario entity);

    // -------- To Entity --------

    Funcionario toEntity(FuncionarioDto dto);

    @Mapping(source = "estado", target = "estado", qualifiedByName = "mapStringToEstado")
    Funcionario toEntity(FuncionarioDetailsDto dto);

    Funcionario toEntity(FuncionarioDetailsExtendedDto dto);

    Funcionario toEntity(FuncionarioWithArmazemDto dto);

    Funcionario toEntity(FuncionarioWithCentroProducaoDto dto);

    Funcionario toEntity(FuncionarioWithContratoDto dto);

    Funcionario toEntity(FuncionarioWithEquipaDto dto);

    Funcionario toEntity(FuncionarioWithPasswordDto dto);

    Funcionario toEntity(FuncionarioWithDepartamentoDto dto);

    Funcionario toEntity(FuncionarioWithR_AnomaliaDto dto);

    Funcionario toEntity(FuncionarioWithR_EquipaDto dto);

    Funcionario toEntity(FuncionarioWithR_FaturaDto dto);

    Funcionario toEntity(FuncionarioWithR_PedidoGeracaoDto dto);

    Funcionario toEntity(FuncionarioWithR_TicketDto dto);

    Funcionario toEntity(FuncionarioWithSolicitacaoDto dto);

    Funcionario toEntity(FuncionarioWithComentarioDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDetailsExtended(FuncionarioDetailsExtendedDto dto, @MappingTarget Funcionario entity);

    // -------- Métodos auxiliares --------

    @Named("mapEstadoToString")
    default String mapEstadoToString(Integer estadoId) {
        if (estadoId == null) return null;
        return EstadoFatura.fromId(estadoId).name();
    }

    @Named("mapStringToEstado")
    default Integer mapStringToEstado(String estadoName) {
        if (estadoName == null) return null;
        return EstadoFatura.fromName(estadoName).getId();
    }

    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromExtendedDto(FuncionarioDetailsExtendedDto dto, @MappingTarget Funcionario entity);
}
