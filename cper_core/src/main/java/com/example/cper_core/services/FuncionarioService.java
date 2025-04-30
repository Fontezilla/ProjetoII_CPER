package com.example.cper_core.services;

import com.example.cper_core.dtos.funcionario.*;
import com.example.cper_core.entities.*;
import com.example.cper_core.enums.*;
import com.example.cper_core.mappers.FuncionarioMapper;
import com.example.cper_core.repositories.*;
import com.example.cper_core.services.interfaces.IFuncionarioService;
import com.example.cper_core.services.interfaces.IJwtService;
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
public class FuncionarioService implements IFuncionarioService {

    private final FuncionarioRepository funcionarioRepository;
    private final FuncionarioMapper funcionarioMapper;
    private final IJwtService jwtService;
    private final TicketRepository ticketRepository;
    private final FaturaRepository faturaRepository;
    private final EquipaRepository equipaRepository;
    private final AnomaliaRepository anomaliaRepository;
    private final ArmazemRepository armazemRepository;
    private final PedidoGeracaoRepository pedidoGeracaoRepository;
    private final CentroProducaoRepository centroProducaoRepository;
    private final ContratoRepository contratoRepository;
    private final DepartamentoRepository departamentoRepository;
    private final SolicitacaoEnergeticaRepository solicitacaoEnergeticaRepository;


    @Autowired
    public FuncionarioService(FuncionarioRepository funcionarioRepository, FuncionarioMapper funcionarioMapper,
                              IJwtService jwtService, TicketRepository ticketRepository, FaturaRepository faturaRepository,
                              EquipaRepository equipaRepository, AnomaliaRepository anomaliaRepository,
                              ArmazemRepository armazemRepository, PedidoGeracaoRepository pedidoGeracaoRepository,
                              CentroProducaoRepository centroProducaoRepository, ContratoRepository contratoRepository,
                              DepartamentoRepository departamentoRepository, SolicitacaoEnergeticaRepository solicitacaoEnergeticaRepository) {
        this.funcionarioRepository = funcionarioRepository;
        this.funcionarioMapper = funcionarioMapper;
        this.jwtService = jwtService;
        this.ticketRepository = ticketRepository;
        this.faturaRepository = faturaRepository;
        this.equipaRepository = equipaRepository;
        this.anomaliaRepository = anomaliaRepository;
        this.armazemRepository = armazemRepository;
        this.pedidoGeracaoRepository = pedidoGeracaoRepository;
        this.centroProducaoRepository = centroProducaoRepository;
        this.contratoRepository = contratoRepository;
        this.departamentoRepository = departamentoRepository;
        this.solicitacaoEnergeticaRepository = solicitacaoEnergeticaRepository;
    }

    // -------- Login --------
    @Override
    public Optional<FuncionarioLoginResponseDto> login(FuncionarioLoginDto loginDto) {
        return funcionarioRepository.findByEmail(loginDto.getEmail())
                .filter(f -> f.getPassword().equals(loginDto.getPassword()))
                .map(f -> {
                    String token = jwtService.generateToken(funcionarioMapper.toTokenDto(f));
                    return new FuncionarioLoginResponseDto(token, f.getId(), f.getNome(), funcionarioMapper.toTokenDto(f).getSetores());
                });
    }

    // -------- CRUD --------
    @Override
    public Page<FuncionarioDetailsDto> listAll(Pageable pageable) {
        return funcionarioRepository.findAll(pageable)
                .map(funcionarioMapper::toDetailsDto);
    }

    @Override
    public Page<FuncionarioDetailsDto> listFiltered(Pageable pageable, FuncionarioFiltroDto filter) {
        return funcionarioRepository.findAll(createSpecificationFromFiltro(filter), pageable)
                .map(funcionarioMapper::toDetailsDto);
    }

    @Override
    public Optional<FuncionarioDetailsExtendedDto> getById(Integer id) {
        return funcionarioRepository.findByIdWithAllRelations(id)
                .map(funcionarioMapper::toDetailsExtendedDto);
    }

    @Override
    public FuncionarioDetailsExtendedDto create(FuncionarioDetailsExtendedDto dto) {
        normalizeDefaults(dto);
        Funcionario entity = funcionarioMapper.toEntity(dto);
        entity = funcionarioRepository.save(entity);
        return funcionarioMapper.toDetailsExtendedDto(entity);
    }

    @Override
    public FuncionarioDetailsExtendedDto update(Integer id, FuncionarioDetailsExtendedDto dto) {
        Funcionario entity = funcionarioRepository.findByIdWithAllRelations(id)
                .orElseThrow(() -> new IllegalArgumentException("Funcionário não encontrado com ID: " + id));

        normalizeDefaults(dto);
        funcionarioMapper.updateEntityFromExtendedDto(dto, entity);
        return funcionarioMapper.toDetailsExtendedDto(funcionarioRepository.save(entity));
    }

    @Override
    public void softDelete(Integer id) {
        Funcionario entity = funcionarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Funcionário não encontrado com ID: " + id));
        entity.setEstado(EstadoFuncionario.APAGADO.getId());
        funcionarioRepository.save(entity);
    }

// ----------------- RESPONSABILIDADE -----------------

    @Override
    public FuncionarioWithR_TicketDto linkRTicket(Integer idFuncionario, Integer idTicket) {
        Funcionario funcionario = getFuncionarioOrThrow(idFuncionario);
        Ticket ticket = ticketRepository.findById(idTicket)
                .orElseThrow(() -> new IllegalArgumentException("Ticket não encontrado com ID: " + idTicket));
        ticket.setFuncionario(funcionario);
        ticketRepository.save(ticket);
        return funcionarioMapper.toWithR_TicketDto(funcionario);
    }

    @Override
    public FuncionarioWithR_TicketDto unlinkRTicket(Integer idFuncionario, Integer idTicket) {
        Ticket ticket = ticketRepository.findById(idTicket)
                .orElseThrow(() -> new IllegalArgumentException("Ticket não encontrado com ID: " + idTicket));
        if (ticket.getFuncionario() != null && ticket.getFuncionario().getId().equals(idFuncionario)) {
            ticket.setFuncionario(null);
            ticketRepository.save(ticket);
        }
        return funcionarioMapper.toWithR_TicketDto(getFuncionarioOrThrow(idFuncionario));
    }

    @Override
    public FuncionarioWithR_FaturaDto linkRFatura(Integer idFuncionario, Integer idFatura) {
        Funcionario funcionario = getFuncionarioOrThrow(idFuncionario);
        Fatura fatura = faturaRepository.findById(idFatura)
                .orElseThrow(() -> new IllegalArgumentException("Fatura não encontrada com ID: " + idFatura));
        fatura.setFuncionario(funcionario);
        faturaRepository.save(fatura);
        return funcionarioMapper.toWithR_FaturaDto(funcionario);
    }

    @Override
    public FuncionarioWithR_FaturaDto unlinkRFatura(Integer idFuncionario, Integer idFatura) {
        Fatura fatura = faturaRepository.findById(idFatura)
                .orElseThrow(() -> new IllegalArgumentException("Fatura não encontrada com ID: " + idFatura));
        if (fatura.getFuncionario() != null && fatura.getFuncionario().getId().equals(idFuncionario)) {
            fatura.setFuncionario(null);
            faturaRepository.save(fatura);
        }
        return funcionarioMapper.toWithR_FaturaDto(getFuncionarioOrThrow(idFuncionario));
    }

    @Override
    public FuncionarioWithR_EquipaDto linkREquipa(Integer idFuncionario, Integer idEquipa) {
        Funcionario funcionario = getFuncionarioOrThrow(idFuncionario);
        Equipa equipa = equipaRepository.findById(idEquipa)
                .orElseThrow(() -> new IllegalArgumentException("Equipa não encontrada com ID: " + idEquipa));
        equipa.setFuncionario(funcionario);
        equipaRepository.save(equipa);
        return funcionarioMapper.toWithR_EquipaDto(funcionario);
    }

    @Override
    public FuncionarioWithR_EquipaDto unlinkREquipa(Integer idFuncionario, Integer idEquipa) {
        Equipa equipa = equipaRepository.findById(idEquipa)
                .orElseThrow(() -> new IllegalArgumentException("Equipa não encontrada com ID: " + idEquipa));
        if (equipa.getFuncionario() != null && equipa.getFuncionario().getId().equals(idFuncionario)) {
            equipa.setFuncionario(null);
            equipaRepository.save(equipa);
        }
        return funcionarioMapper.toWithR_EquipaDto(getFuncionarioOrThrow(idFuncionario));
    }

    @Override
    public FuncionarioWithR_AnomaliaDto linkRAnomalia(Integer idFuncionario, Integer idAnomalia) {
        Funcionario funcionario = getFuncionarioOrThrow(idFuncionario);
        Anomalia anomalia = anomaliaRepository.findById(idAnomalia)
                .orElseThrow(() -> new IllegalArgumentException("Anomalia não encontrada com ID: " + idAnomalia));
        anomalia.setFuncionario(funcionario);
        anomaliaRepository.save(anomalia);
        return funcionarioMapper.toWithR_AnomaliaDto(funcionario);
    }

    @Override
    public FuncionarioWithR_AnomaliaDto unlinkRAnomalia(Integer idFuncionario, Integer idAnomalia) {
        Anomalia anomalia = anomaliaRepository.findById(idAnomalia)
                .orElseThrow(() -> new IllegalArgumentException("Anomalia não encontrada com ID: " + idAnomalia));
        if (anomalia.getFuncionario() != null && anomalia.getFuncionario().getId().equals(idFuncionario)) {
            anomalia.setFuncionario(null);
            anomaliaRepository.save(anomalia);
        }
        return funcionarioMapper.toWithR_AnomaliaDto(getFuncionarioOrThrow(idFuncionario));
    }

    @Override
    public FuncionarioWithR_ArmazemDto linkRArmazem(Integer idFuncionario, Integer idArmazem) {
        Funcionario funcionario = getFuncionarioOrThrow(idFuncionario);
        Armazem armazem = armazemRepository.findById(idArmazem)
                .orElseThrow(() -> new IllegalArgumentException("Armazém não encontrado com ID: " + idArmazem));
        armazem.setResponsavel(funcionario);
        armazemRepository.save(armazem);
        return funcionarioMapper.toWithR_ArmazemDto(funcionario);
    }

    @Override
    public FuncionarioWithR_ArmazemDto unlinkRArmazem(Integer idFuncionario, Integer idArmazem) {
        Armazem armazem = armazemRepository.findById(idArmazem)
                .orElseThrow(() -> new IllegalArgumentException("Armazém não encontrado com ID: " + idArmazem));
        if (armazem.getResponsavel() != null && armazem.getResponsavel().getId().equals(idFuncionario)) {
            armazem.setResponsavel(null);
            armazemRepository.save(armazem);
        }
        return funcionarioMapper.toWithR_ArmazemDto(getFuncionarioOrThrow(idFuncionario));
    }

    @Override
    public FuncionarioWithR_PedidoGeracaoDto linkRPedidoGeracao(Integer idFuncionario, Integer idPedidoGeracao) {
        Funcionario funcionario = getFuncionarioOrThrow(idFuncionario);
        PedidoGeracao pedido = pedidoGeracaoRepository.findById(idPedidoGeracao)
                .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado com ID: " + idPedidoGeracao));
        pedido.setFuncionario(funcionario);
        pedidoGeracaoRepository.save(pedido);
        return funcionarioMapper.toWithR_PedidoDto(funcionario);
    }

    @Override
    public FuncionarioWithR_PedidoGeracaoDto unlinkRPedidoGeracao(Integer idFuncionario, Integer idPedidoGeracao) {
        PedidoGeracao pedido = pedidoGeracaoRepository.findById(idPedidoGeracao)
                .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado com ID: " + idPedidoGeracao));
        if (pedido.getFuncionario() != null && pedido.getFuncionario().getId().equals(idFuncionario)) {
            pedido.setFuncionario(null);
            pedidoGeracaoRepository.save(pedido);
        }
        return funcionarioMapper.toWithR_PedidoDto(getFuncionarioOrThrow(idFuncionario));
    }

// ----------------- PARTICIPAÇÃO (ManyToMany) -----------------

    @Override
    public FuncionarioWithArmazemDto linkArmazem(Integer idFuncionario, Integer idArmazem) {
        Funcionario funcionario = getFuncionarioOrThrow(idFuncionario);
        Armazem armazem = armazemRepository.findById(idArmazem)
                .orElseThrow(() -> new IllegalArgumentException("Armazém não encontrado com ID: " + idArmazem));
        funcionario.getArmazems().add(armazem);
        funcionarioRepository.save(funcionario);
        return funcionarioMapper.toWithArmazemDto(funcionario);
    }

    @Override
    public FuncionarioWithArmazemDto unlinkArmazem(Integer idFuncionario, Integer idArmazem) {
        Funcionario funcionario = getFuncionarioOrThrow(idFuncionario);
        Armazem armazem = armazemRepository.findById(idArmazem)
                .orElseThrow(() -> new IllegalArgumentException("Armazém não encontrado com ID: " + idArmazem));
        funcionario.getArmazems().remove(armazem);
        funcionarioRepository.save(funcionario);
        return funcionarioMapper.toWithArmazemDto(funcionario);
    }

    @Override
    public FuncionarioWithCentroProducaoDto linkCentroProducao(Integer idFuncionario, Integer idCentroProducao) {
        Funcionario funcionario = getFuncionarioOrThrow(idFuncionario);
        CentroProducao centro = centroProducaoRepository.findById(idCentroProducao)
                .orElseThrow(() -> new IllegalArgumentException("Centro não encontrado com ID: " + idCentroProducao));
        funcionario.getCentroProducoes().add(centro);
        funcionarioRepository.save(funcionario);
        return funcionarioMapper.toWithCentroDto(funcionario);
    }

    @Override
    public FuncionarioWithCentroProducaoDto unlinkCentroProducao(Integer idFuncionario, Integer idCentroProducao) {
        Funcionario funcionario = getFuncionarioOrThrow(idFuncionario);
        CentroProducao centro = centroProducaoRepository.findById(idCentroProducao)
                .orElseThrow(() -> new IllegalArgumentException("Centro não encontrado com ID: " + idCentroProducao));
        funcionario.getCentroProducoes().remove(centro);
        funcionarioRepository.save(funcionario);
        return funcionarioMapper.toWithCentroDto(funcionario);
    }

    @Override
    public FuncionarioWithContratoDto linkContrato(Integer idFuncionario, Integer idContrato) {
        Funcionario funcionario = getFuncionarioOrThrow(idFuncionario);
        Contrato contrato = contratoRepository.findById(idContrato)
                .orElseThrow(() -> new IllegalArgumentException("Contrato não encontrado com ID: " + idContrato));
        funcionario.getResponsavelContratos().add(contrato);
        funcionarioRepository.save(funcionario);
        return funcionarioMapper.toWithContratoDto(funcionario);
    }

    @Override
    public FuncionarioWithContratoDto unlinkContrato(Integer idFuncionario, Integer idContrato) {
        Funcionario funcionario = getFuncionarioOrThrow(idFuncionario);
        Contrato contrato = contratoRepository.findById(idContrato)
                .orElseThrow(() -> new IllegalArgumentException("Contrato não encontrado com ID: " + idContrato));
        funcionario.getResponsavelContratos().remove(contrato);
        funcionarioRepository.save(funcionario);
        return funcionarioMapper.toWithContratoDto(funcionario);
    }

    @Override
    public FuncionarioWithDepartamentoDto linkDepartamento(Integer idFuncionario, Integer idDepartamento) {
        Funcionario funcionario = getFuncionarioOrThrow(idFuncionario);
        Departamento departamento = departamentoRepository.findById(idDepartamento)
                .orElseThrow(() -> new IllegalArgumentException("Departamento não encontrado com ID: " + idDepartamento));
        funcionario.getDepartamentos().add(departamento);
        funcionarioRepository.save(funcionario);
        return funcionarioMapper.toWithDepartamentosDto(funcionario);
    }

    @Override
    public FuncionarioWithDepartamentoDto unlinkDepartamento(Integer idFuncionario, Integer idDepartamento) {
        Funcionario funcionario = getFuncionarioOrThrow(idFuncionario);
        Departamento departamento = departamentoRepository.findById(idDepartamento)
                .orElseThrow(() -> new IllegalArgumentException("Departamento não encontrado com ID: " + idDepartamento));
        funcionario.getDepartamentos().remove(departamento);
        funcionarioRepository.save(funcionario);
        return funcionarioMapper.toWithDepartamentosDto(funcionario);
    }

    @Override
    public FuncionarioWithEquipaDto linkEquipa(Integer idFuncionario, Integer idEquipa) {
        Funcionario funcionario = getFuncionarioOrThrow(idFuncionario);
        Equipa equipa = equipaRepository.findById(idEquipa)
                .orElseThrow(() -> new IllegalArgumentException("Equipa não encontrada com ID: " + idEquipa));
        funcionario.getEquipas().add(equipa);
        funcionarioRepository.save(funcionario);
        return funcionarioMapper.toWithEquipaDto(funcionario);
    }

    @Override
    public FuncionarioWithEquipaDto unlinkEquipa(Integer idFuncionario, Integer idEquipa) {
        Funcionario funcionario = getFuncionarioOrThrow(idFuncionario);
        Equipa equipa = equipaRepository.findById(idEquipa)
                .orElseThrow(() -> new IllegalArgumentException("Equipa não encontrada com ID: " + idEquipa));
        funcionario.getEquipas().remove(equipa);
        funcionarioRepository.save(funcionario);
        return funcionarioMapper.toWithEquipaDto(funcionario);
    }

    @Override
    public FuncionarioWithSolicitacaoDto linkSolicitacao(Integer idFuncionario, Integer idSolicitacao) {
        Funcionario funcionario = getFuncionarioOrThrow(idFuncionario);
        SolicitacaoEnergetica solicitacao = solicitacaoEnergeticaRepository.findById(idSolicitacao)
                .orElseThrow(() -> new IllegalArgumentException("Solicitação não encontrada com ID: " + idSolicitacao));
        funcionario.getSolicitacaoEnergeticas().add(solicitacao);
        funcionarioRepository.save(funcionario);
        return funcionarioMapper.toWithSolicitacaoDto(funcionario);
    }

    @Override
    public FuncionarioWithSolicitacaoDto unlinkSolicitacao(Integer idFuncionario, Integer idSolicitacao) {
        Funcionario funcionario = getFuncionarioOrThrow(idFuncionario);
        SolicitacaoEnergetica solicitacao = solicitacaoEnergeticaRepository.findById(idSolicitacao)
                .orElseThrow(() -> new IllegalArgumentException("Solicitação não encontrada com ID: " + idSolicitacao));
        funcionario.getSolicitacaoEnergeticas().remove(solicitacao);
        funcionarioRepository.save(funcionario);
        return funcionarioMapper.toWithSolicitacaoDto(funcionario);
    }

    // -------- Auxiliares --------

    private void normalizeDefaults(FuncionarioDetailsExtendedDto dto) {
        if (dto.getEstado() == null) dto.setEstado(EstadoFuncionario.ATIVO.name());
    }

    private Specification<Funcionario> createSpecificationFromFiltro(FuncionarioFiltroDto filtro) {
        return (root, query, cb) -> {
            Predicate predicate = cb.conjunction();

            if (filtro.getId() != null)
                predicate = cb.and(predicate, cb.equal(root.get("id"), filtro.getId()));

            if (filtro.getNome() != null && !filtro.getNome().isBlank())
                predicate = cb.and(predicate, cb.like(cb.lower(root.get("nome")), "%" + filtro.getNome().toLowerCase() + "%"));

            if (filtro.getNif() != null)
                predicate = cb.and(predicate, cb.equal(root.get("nif"), filtro.getNif()));

            if (filtro.getEmail() != null)
                predicate = cb.and(predicate, cb.equal(root.get("email"), filtro.getEmail()));

            if (filtro.getTelefone() != null)
                predicate = cb.and(predicate, cb.equal(root.get("telefone"), filtro.getTelefone()));

            if (filtro.getDataNascimentoInicio() != null)
                predicate = cb.and(predicate, cb.greaterThanOrEqualTo(root.get("dataNascimento"), filtro.getDataNascimentoInicio()));

            if (filtro.getDataNascimentoFim() != null)
                predicate = cb.and(predicate, cb.lessThanOrEqualTo(root.get("dataNascimento"), filtro.getDataNascimentoFim()));

            if (filtro.getDataContratacaoInicio() != null)
                predicate = cb.and(predicate, cb.greaterThanOrEqualTo(root.get("dataContratacao"), filtro.getDataContratacaoInicio()));

            if (filtro.getDataContratacaoFim() != null)
                predicate = cb.and(predicate, cb.lessThanOrEqualTo(root.get("dataContratacao"), filtro.getDataContratacaoFim()));

            if (filtro.getSalarioMin() != null)
                predicate = cb.and(predicate, cb.ge(root.get("salario"), filtro.getSalarioMin()));

            if (filtro.getSalarioMax() != null)
                predicate = cb.and(predicate, cb.le(root.get("salario"), filtro.getSalarioMax()));

            if (filtro.getCargo() != null && !filtro.getCargo().isBlank())
                predicate = cb.and(predicate, cb.like(cb.lower(root.get("cargo")), "%" + filtro.getCargo().toLowerCase() + "%"));

            if (filtro.getEstado() != null)
                predicate = cb.and(predicate, cb.equal(root.get("estado"), filtro.getEstado()));

            if (filtro.getIdDepartamento() != null)
                predicate = cb.and(predicate, cb.equal(root.get("departamento").get("id"), filtro.getIdDepartamento()));

            if (filtro.getIdEndereco() != null)
                predicate = cb.and(predicate, cb.equal(root.get("endereco").get("id"), filtro.getIdEndereco()));

            if (filtro.getNPorta() != null)
                predicate = cb.and(predicate, cb.equal(root.get("nPorta"), filtro.getNPorta()));

            return predicate;
        };
    }

    private Funcionario getFuncionarioOrThrow(Integer id) {
        return funcionarioRepository.findByIdWithAllRelations(id)
                .orElseThrow(() -> new IllegalArgumentException("Funcionário não encontrado com ID: " + id));
    }
}
