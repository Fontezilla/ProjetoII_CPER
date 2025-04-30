package com.example.cper_core.services;

import com.example.cper_core.dtos.centro_producao.*;
import com.example.cper_core.entities.*;
import com.example.cper_core.enums.EstadoCentro;
import com.example.cper_core.mappers.CentroProducaoMapper;
import com.example.cper_core.repositories.*;
import com.example.cper_core.services.interfaces.ICentroProducaoService;
import jakarta.persistence.criteria.Predicate;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class CentroProducaoService implements ICentroProducaoService {

    private final CentroProducaoRepository centroProducaoRepository;
    private final CentroProducaoMapper centroProducaoMapper;
    private final InspecaoRepository inspecaoRepository;
    private final AvariaRepository avariaRepository;
    private final AnomaliaRepository anomaliaRepository;
    private final FuncionarioRepository funcionarioRepository;
    private final PedidoGeracaoRepository pedidoGeracaoRepository;

    @Autowired
    public CentroProducaoService(
            CentroProducaoRepository centroProducaoRepository,
            CentroProducaoMapper centroProducaoMapper,
            InspecaoRepository inspecaoRepository,
            AvariaRepository avariaRepository,
            AnomaliaRepository anomaliaRepository,
            FuncionarioRepository funcionarioRepository,
            PedidoGeracaoRepository pedidoGeracaoRepository
    ) {
        this.centroProducaoRepository = centroProducaoRepository;
        this.centroProducaoMapper = centroProducaoMapper;
        this.inspecaoRepository = inspecaoRepository;
        this.avariaRepository = avariaRepository;
        this.anomaliaRepository = anomaliaRepository;
        this.funcionarioRepository = funcionarioRepository;
        this.pedidoGeracaoRepository = pedidoGeracaoRepository;
    }

    @Override
    public Page<CentroProducaoDetailsDto> listAll(Pageable pageable) {
        return centroProducaoRepository.findAll(pageable).map(centroProducaoMapper::toDetailsDto);
    }

    @Override
    public Page<CentroProducaoDetailsDto> listFiltered(Pageable pageable, CentroProducaoFiltroDto filtro) {
        Specification<CentroProducao> spec = filtroCentro(filtro);
        return centroProducaoRepository.findAll(spec, pageable).map(centroProducaoMapper::toDetailsDto);
    }

    @Override
    public Optional<CentroProducaoDetailsExtendedDto> getById(Integer id) {
        return centroProducaoRepository.findByIdWithAllRelations(id).map(centroProducaoMapper::toDetailsExtendedDto);
    }

    @Override
    public CentroProducaoDetailsExtendedDto create(CentroProducaoDetailsExtendedDto dto) {
        clearEmptyEntries(dto);
        CentroProducao entity = centroProducaoMapper.toEntity(dto);
        return centroProducaoMapper.toDetailsExtendedDto(centroProducaoRepository.save(entity));
    }

    @Override
    public CentroProducaoDetailsExtendedDto update(Integer id, CentroProducaoDetailsExtendedDto dto) {
        if (dto.getId() == null || !dto.getId().equals(id))
            throw new IllegalArgumentException("ID do corpo e da URL não coincidem.");

        CentroProducao entity = centroProducaoRepository.findByIdWithAllRelations(id)
                .orElseThrow(() -> new IllegalArgumentException("Centro de Produção não encontrado."));

        clearEmptyEntries(dto);
        centroProducaoMapper.updateEntityFromExtendedDto(dto, entity);
        return centroProducaoMapper.toDetailsExtendedDto(centroProducaoRepository.save(entity));
    }

    @Override
    public void softDelete(Integer id) {
        CentroProducao entity = centroProducaoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Centro de Produção não encontrado."));
        entity.setEstado(EstadoCentro.APAGADO.getId());
        centroProducaoRepository.save(entity);
    }

    @Override
    public CentroProducaoWithInspecaoDto linkInspecao(Integer idCentro, Integer idInspecao) {
        CentroProducao centro = findCentro(idCentro);
        Inspecao inspecao = inspecaoRepository.findById(idInspecao)
                .orElseThrow(() -> new IllegalArgumentException("Inspetção não encontrada."));
        inspecao.setCentroProducao(centro);
        inspecaoRepository.save(inspecao);
        return centroProducaoMapper.toWithInspecaoDto(findCentro(idCentro));
    }

    @Override
    public CentroProducaoWithInspecaoDto unlinkInspecao(Integer idCentro, Integer idInspecao) {
        Inspecao inspecao = inspecaoRepository.findById(idInspecao)
                .orElseThrow(() -> new IllegalArgumentException("Inspetção não encontrada."));
        inspecao.setCentroProducao(null);
        inspecaoRepository.save(inspecao);
        return centroProducaoMapper.toWithInspecaoDto(findCentro(idCentro));
    }

    @Override
    public CentroProducaoWithAvariaDto linkAvaria(Integer idCentro, Integer idAvaria) {
        CentroProducao centro = findCentro(idCentro);
        Avaria avaria = avariaRepository.findById(idAvaria)
                .orElseThrow(() -> new IllegalArgumentException("Avaria não encontrada."));
        avaria.setCentroProducao(centro);
        avariaRepository.save(avaria);
        return centroProducaoMapper.toWithAvariaDto(findCentro(idCentro));
    }

    @Override
    public CentroProducaoWithAvariaDto unlinkAvaria(Integer idCentro, Integer idAvaria) {
        Avaria avaria = avariaRepository.findById(idAvaria)
                .orElseThrow(() -> new IllegalArgumentException("Avaria não encontrada."));
        avaria.setCentroProducao(null);
        avariaRepository.save(avaria);
        return centroProducaoMapper.toWithAvariaDto(findCentro(idCentro));
    }

    @Override
    public CentroProducaoWithAnomaliaDto linkAnomalia(Integer idCentro, Integer idAnomalia) {
        CentroProducao centro = findCentro(idCentro);
        Anomalia anomalia = anomaliaRepository.findById(idAnomalia)
                .orElseThrow(() -> new IllegalArgumentException("Anomalia não encontrada."));
        anomalia.setCentroProducao(centro);
        anomaliaRepository.save(anomalia);
        return centroProducaoMapper.toWithAnomaliaDto(findCentro(idCentro));
    }

    @Override
    public CentroProducaoWithAnomaliaDto unlinkAnomalia(Integer idCentro, Integer idAnomalia) {
        Anomalia anomalia = anomaliaRepository.findById(idAnomalia)
                .orElseThrow(() -> new IllegalArgumentException("Anomalia não encontrada."));
        anomalia.setCentroProducao(null);
        anomaliaRepository.save(anomalia);
        return centroProducaoMapper.toWithAnomaliaDto(findCentro(idCentro));
    }

    @Override
    public CentroProducaoWithFuncionarioDto linkFuncionario(Integer idCentro, Integer idFuncionario) {
        CentroProducao centro = findCentro(idCentro);
        Funcionario funcionario = funcionarioRepository.findById(idFuncionario)
                .orElseThrow(() -> new IllegalArgumentException("Funcionário não encontrado."));
        centro.getFuncionarios().add(funcionario);
        return centroProducaoMapper.toWithFuncionarioDto(centroProducaoRepository.save(centro));
    }

    @Override
    public CentroProducaoWithFuncionarioDto unlinkFuncionario(Integer idCentro, Integer idFuncionario) {
        CentroProducao centro = findCentro(idCentro);
        Funcionario funcionario = funcionarioRepository.findById(idFuncionario)
                .orElseThrow(() -> new IllegalArgumentException("Funcionário não encontrado."));
        centro.getFuncionarios().remove(funcionario);
        return centroProducaoMapper.toWithFuncionarioDto(centroProducaoRepository.save(centro));
    }

    @Override
    public CentroProducaoWithPedidoGeracaoDto linkPedidoGeracao(Integer idCentro, Integer idPedido) {
        CentroProducao centro = findCentro(idCentro);
        PedidoGeracao pedido = pedidoGeracaoRepository.findById(idPedido)
                .orElseThrow(() -> new IllegalArgumentException("Pedido de Geração não encontrado."));
        pedido.setCentroProducao(centro);
        pedidoGeracaoRepository.save(pedido);
        return centroProducaoMapper.toWithPedidoDto(findCentro(idCentro));
    }

    @Override
    public CentroProducaoWithPedidoGeracaoDto unlinkPedidoGeracao(Integer idCentro, Integer idPedido) {
        PedidoGeracao pedido = pedidoGeracaoRepository.findById(idPedido)
                .orElseThrow(() -> new IllegalArgumentException("Pedido de Geração não encontrado."));
        pedido.setCentroProducao(null);
        pedidoGeracaoRepository.save(pedido);
        return centroProducaoMapper.toWithPedidoDto(findCentro(idCentro));
    }

    // -------- Helpers --------

    private CentroProducao findCentro(Integer id) {
        return centroProducaoRepository.findByIdWithAllRelations(id)
                .orElseThrow(() -> new IllegalArgumentException("Centro de Produção não encontrado com ID: " + id));
    }

    private void clearEmptyEntries(CentroProducaoDetailsExtendedDto dto) {
        if (dto.getDepartamento() != null && dto.getDepartamento().getId() == null)
            dto.setDepartamento(null);

        if (dto.getEndereco() != null && dto.getEndereco().getId() == null)
            dto.setEndereco(null);

        if (dto.getFuncionario() != null && dto.getFuncionario().getId() == null)
            dto.setFuncionario(null);

        if (dto.getNome() != null && dto.getNome().isBlank())
            dto.setNome(null);

        if (dto.getTipoEnergia() != null && dto.getTipoEnergia().isBlank())
            dto.setTipoEnergia(null);

        if (dto.getEstado() != null && dto.getEstado().isBlank())
            dto.setEstado(null);
    }

    private Specification<CentroProducao> filtroCentro(CentroProducaoFiltroDto filtro) {
        return (root, query, cb) -> {
            Predicate predicate = cb.conjunction();

            if (filtro.getId() != null)
                predicate = cb.and(predicate, cb.equal(root.get("id"), filtro.getId()));
            if (filtro.getNome() != null)
                predicate = cb.and(predicate, cb.like(cb.lower(root.get("nome")), "%" + filtro.getNome().toLowerCase() + "%"));
            if (filtro.getTipoEnergia() != null)
                predicate = cb.and(predicate, cb.equal(root.get("tipoEnergia"), filtro.getTipoEnergia()));
            if (filtro.getCapacidadeMaxMin() != null)
                predicate = cb.and(predicate, cb.greaterThanOrEqualTo(root.get("capacidadeMax"), filtro.getCapacidadeMaxMin()));
            if (filtro.getCapacidadeMaxMax() != null)
                predicate = cb.and(predicate, cb.lessThanOrEqualTo(root.get("capacidadeMax"), filtro.getCapacidadeMaxMax()));
            if (filtro.getDataInicioInicio() != null)
                predicate = cb.and(predicate, cb.greaterThanOrEqualTo(root.get("dataInicio"), filtro.getDataInicioInicio()));
            if (filtro.getDataInicioFim() != null)
                predicate = cb.and(predicate, cb.lessThanOrEqualTo(root.get("dataInicio"), filtro.getDataInicioFim()));
            if (filtro.getCustoOperacionalMin() != null)
                predicate = cb.and(predicate, cb.greaterThanOrEqualTo(root.get("custoOperacional"), filtro.getCustoOperacionalMin()));
            if (filtro.getCustoOperacionalMax() != null)
                predicate = cb.and(predicate, cb.lessThanOrEqualTo(root.get("custoOperacional"), filtro.getCustoOperacionalMax()));
            if (filtro.getEstado() != null)
                predicate = cb.and(predicate, cb.equal(root.get("estado"), filtro.getEstado()));
            if (filtro.getNPorta() != null)
                predicate = cb.and(predicate, cb.equal(root.get("nPorta"), filtro.getNPorta()));
            if (filtro.getIdEndereco() != null)
                predicate = cb.and(predicate, cb.equal(root.get("endereco").get("id"), filtro.getIdEndereco()));
            if (filtro.getIdDepartamento() != null)
                predicate = cb.and(predicate, cb.equal(root.get("departamento").get("id"), filtro.getIdDepartamento()));

            return predicate;
        };
    }
}
