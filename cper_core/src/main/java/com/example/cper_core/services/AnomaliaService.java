package com.example.cper_core.services;

import com.example.cper_core.dtos.anomalia.*;
import com.example.cper_core.entities.*;
import com.example.cper_core.enums.EstadoAnomalia;
import com.example.cper_core.enums.Prioridade;
import com.example.cper_core.enums.TipoAnomalia;
import com.example.cper_core.mappers.AnomaliaMapper;
import com.example.cper_core.repositories.*;
import com.example.cper_core.services.interfaces.IAnomaliaService;
import com.example.cper_core.specifications.AnomaliaSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AnomaliaService extends AbstractXService<
        Anomalia,
        AnomaliaDto,
        AnomaliaDetailsDto,
        AnomaliaDetailsExtendedDto,
        AnomaliaFiltroDto,
        AnomaliaWithRelationshipsDto,
        Integer
        > implements IAnomaliaService {

    private final AnomaliaMapper anomaliaMapper;
    private final CentroProducaoRepository centroProducaoRepository;
    private final FuncionarioRepository funcionarioRepository;

    public AnomaliaService(
            AnomaliaRepository anomaliaRepository,
            AnomaliaMapper anomaliaMapper,
            CentroProducaoRepository centroProducaoRepository,
            FuncionarioRepository funcionarioRepository,
            jakarta.validation.Validator validator
    ) {
        super(anomaliaRepository, anomaliaRepository, validator);
        this.anomaliaMapper = anomaliaMapper;
        this.centroProducaoRepository = centroProducaoRepository;
        this.funcionarioRepository = funcionarioRepository;
    }

    @Override
    protected Anomalia toEntity(AnomaliaDetailsExtendedDto dto) {
        return anomaliaMapper.toEntity(dto);
    }

    @Override
    protected void updateEntityFromDto(AnomaliaDetailsExtendedDto dto, Anomalia entity) {
        if (dto.getTipoAnomalia() != null) entity.setTipoAnomalia(TipoAnomalia.getIdFromEnum(dto.getTipoAnomalia()));
        if (dto.getTitulo() != null) entity.setTitulo(dto.getTitulo());
        if (dto.getDescricao() != null) entity.setDescricao(dto.getDescricao());
        if (dto.getEstado() != null) entity.setEstado(EstadoAnomalia.getIdFromEnum(dto.getEstado()));
        if (dto.getGravidade() != null) entity.setGravidade(Prioridade.getIdFromEnum(dto.getGravidade()));

        if (dto.getCentroProducao() != null) {
            CentroProducao centro = centroProducaoRepository.findById(dto.getCentroProducao().getId())
                    .orElseThrow(() -> new RuntimeException("Centro de Produção não encontrado"));
            entity.setCentroProducao(centro);
        }

        if (dto.getFuncionario() != null) {
            Funcionario funcionario = funcionarioRepository.findById(dto.getFuncionario().getId())
                    .orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));
            entity.setFuncionario(funcionario);
        }
    }

    @Override
    protected AnomaliaDetailsExtendedDto toExtendedDto(Anomalia entity) {
        return anomaliaMapper.toExtendedDto(entity);
    }

    @Override
    protected AnomaliaDetailsDto toDetailsDto(Anomalia entity) {
        return anomaliaMapper.toDetailsDto(entity);
    }

    @Override
    protected Specification<Anomalia> getSpecificationFromFiltro(AnomaliaFiltroDto filtro) {
        return AnomaliaSpecification.filter(filtro);
    }

    @Override
    protected void marcarComoEliminado(Anomalia entity) {
        entity.setIsDeleted(true);
    }
}
