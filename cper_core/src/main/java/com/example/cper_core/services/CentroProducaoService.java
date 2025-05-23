package com.example.cper_core.services;

import com.example.cper_core.dtos.centro_producao.*;
import com.example.cper_core.entities.*;
import com.example.cper_core.mappers.CentroProducaoMapper;
import com.example.cper_core.repositories.*;
import com.example.cper_core.services.interfaces.ICentroProducaoService;
import com.example.cper_core.specifications.CentroProducaoSpecification;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Validator;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class CentroProducaoService extends AbstractXService<
        CentroProducao,
        CentroProducaoDto,
        CentroProducaoDetailsDto,
        CentroProducaoDetailsExtendedDto,
        CentroProducaoFiltroDto,
        CentroProducaoWithRelationshipsDto,
        Integer
        > implements ICentroProducaoService {

    private final CentroProducaoRepository centroProducaoRepository;
    private final CentroProducaoMapper centroProducaoMapper;
    private final DepartamentoRepository departamentoRepository;
    private final EnderecoRepository enderecoRepository;
    private final FuncionarioRepository funcionarioRepository;
    private final AvariaRepository avariaRepository;
    private final InspecaoRepository inspecaoRepository;
    private final AnomaliaRepository anomaliaRepository;
    private final PedidoGeracaoRepository pedidoGeracaoRepository;

    public CentroProducaoService(
            CentroProducaoRepository centroProducaoRepository,
            CentroProducaoMapper centroProducaoMapper,
            DepartamentoRepository departamentoRepository,
            EnderecoRepository enderecoRepository,
            FuncionarioRepository funcionarioRepository,
            AvariaRepository avariaRepository,
            InspecaoRepository inspecaoRepository,
            AnomaliaRepository anomaliaRepository,
            PedidoGeracaoRepository pedidoGeracaoRepository,
            Validator validator
    ) {
        super(centroProducaoRepository, centroProducaoRepository, validator);
        this.centroProducaoRepository = centroProducaoRepository;
        this.centroProducaoMapper = centroProducaoMapper;
        this.departamentoRepository = departamentoRepository;
        this.enderecoRepository = enderecoRepository;
        this.funcionarioRepository = funcionarioRepository;
        this.avariaRepository = avariaRepository;
        this.inspecaoRepository = inspecaoRepository;
        this.anomaliaRepository = anomaliaRepository;
        this.pedidoGeracaoRepository = pedidoGeracaoRepository;
    }

    @Override
    protected CentroProducao toEntity(CentroProducaoDetailsExtendedDto dto) {
        return centroProducaoMapper.toEntity(dto);
    }

    @Override
    protected void updateEntityFromDto(CentroProducaoDetailsExtendedDto dto, CentroProducao entity) {
        centroProducaoMapper.updateEntityFromExtendedDto(dto, entity);
    }

    @Override
    protected CentroProducaoDetailsExtendedDto toExtendedDto(CentroProducao entity) {
        return centroProducaoMapper.toExtendedDto(entity);
    }

    @Override
    protected CentroProducaoDetailsDto toDetailsDto(CentroProducao entity) {
        return centroProducaoMapper.toDetailsDto(entity);
    }

    @Override
    protected Specification<CentroProducao> getSpecificationFromFiltro(CentroProducaoFiltroDto filtro) {
        return CentroProducaoSpecification.filter(filtro);
    }

    @Override
    protected void marcarComoEliminado(CentroProducao entity) {
        entity.setIsDeleted(true);
    }

    // --- Linking and Unlinking Methods ---
    @Override
    public void linkToFuncionarios(Integer id, Set<Integer> ids) {
        CentroProducao centro = getEntity(id);

        Set<Funcionario> funcionarios = ids.stream()
                .map(i -> funcionarioRepository.findById(i)
                        .orElseThrow(() -> new EntityNotFoundException("Funcionário não encontrado: " + i)))
                .collect(Collectors.toSet());
        centro.getFuncionarios().addAll(funcionarios);

        centroProducaoRepository.save(centro);
    }

    @Override
    public void unlinkFromFuncionarios(Integer id, Set<Integer> ids) {
        CentroProducao centro = getEntity(id);

        centro.getFuncionarios().removeIf(f -> ids.contains(f.getId()));

        centroProducaoRepository.save(centro);
    }

    // --- Aux ---
    private CentroProducao getEntity(Integer id) {
        return centroProducaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Centro de produção não encontrado"));
    }
}