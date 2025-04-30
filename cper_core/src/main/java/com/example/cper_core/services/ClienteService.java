package com.example.cper_core.services;

import com.example.cper_core.dtos.IJwtUser;
import com.example.cper_core.dtos.cliente.*;
import com.example.cper_core.entities.*;
import com.example.cper_core.enums.EstadoCliente;
import com.example.cper_core.enums.JwtTipoUtilizador;
import com.example.cper_core.mappers.ClienteMapper;
import com.example.cper_core.repositories.*;
import com.example.cper_core.services.interfaces.IClienteService;
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
public class ClienteService implements IClienteService {

    private final ClienteRepository clienteRepository;
    private final PerfilConsumoRepository perfilConsumoRepository;
    private final SolicitacaoEnergeticaRepository solicitacaoRepository;
    private final TicketRepository ticketRepository;
    private final ClienteMapper clienteMapper;
    private final IJwtService jwtService;

    @Autowired
    public ClienteService(
            ClienteRepository clienteRepository,
            ClienteMapper clienteMapper,
            PerfilConsumoRepository perfilConsumoRepository,
            SolicitacaoEnergeticaRepository solicitacaoRepository,
            TicketRepository ticketRepository,
            IJwtService jwtService
    ) {
        this.clienteRepository = clienteRepository;
        this.clienteMapper = clienteMapper;
        this.perfilConsumoRepository = perfilConsumoRepository;
        this.solicitacaoRepository = solicitacaoRepository;
        this.ticketRepository = ticketRepository;
        this.jwtService = jwtService;
    }

    @Override
    public Optional<ClienteLoginResponseDto> login(ClienteLoginDto loginDto) {
        return clienteRepository.findAll().stream()
                .filter(c -> c.getEmail().equals(loginDto.getEmail()) && c.getPassword().equals(loginDto.getPassword()))
                .findFirst()
                .map(cliente -> {
                    String token = jwtService.generateToken(new ClienteTokenDto(cliente.getId(),cliente.getNome()));
                    return new ClienteLoginResponseDto(token, cliente.getId(), cliente.getNome());
                });
    }

    @Override
    public Page<ClienteDetailsDto> listAll(Pageable pageable) {
        return clienteRepository.findAll(pageable).map(clienteMapper::toDetailsDto);
    }

    @Override
    public Page<ClienteDetailsDto> listFiltered(Pageable pageable, ClienteFiltroDto filtro) {
        Specification<Cliente> spec = filtroCliente(filtro);
        return clienteRepository.findAll(spec, pageable).map(clienteMapper::toDetailsDto);
    }

    @Override
    public Optional<ClienteDetailsExtendedDto> getById(Integer id) {
        return clienteRepository.findByIdWithAllRelations(id).map(clienteMapper::toDetailsExtendedDto);
    }

    @Override
    public ClienteDetailsExtendedDto create(ClienteDetailsExtendedDto dto) {
        clearEmptyEntries(dto);
        Cliente entity = clienteMapper.toEntity(dto);
        return clienteMapper.toDetailsExtendedDto(clienteRepository.save(entity));
    }

    @Override
    public ClienteDetailsExtendedDto update(Integer id, ClienteDetailsExtendedDto dto) {
        if (dto.getId() == null || !dto.getId().equals(id))
            throw new IllegalArgumentException("ID do corpo e da URL não coincidem.");

        Cliente entity = clienteRepository.findByIdWithAllRelations(id)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado."));

        clearEmptyEntries(dto);
        clienteMapper.updateEntityFromExtendedDto(dto, entity);
        return clienteMapper.toDetailsExtendedDto(clienteRepository.save(entity));
    }

    @Override
    public void softDelete(Integer id) {
        Cliente entity = clienteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado."));
        entity.setEstado(EstadoCliente.APAGADO.getId());
        clienteRepository.save(entity);
    }

    @Override
    public ClienteWithPasswordDto updatePassword(Integer idCliente, ClienteWithPasswordDto dto) {
        Cliente cliente = clienteRepository.findById(idCliente)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado."));
        cliente.setPassword(dto.getPassword());
        return clienteMapper.toWithPasswordDto(clienteRepository.save(cliente));
    }

    @Override
    public ClienteWithPerfilDto linkPerfil(Integer idCliente, Integer idPerfil) {
        Cliente cliente = findCliente(idCliente);
        PerfilConsumo perfil = perfilConsumoRepository.findById(idPerfil)
                .orElseThrow(() -> new IllegalArgumentException("Perfil não encontrado."));
        cliente.getPerfilConsumos().add(perfil);
        return clienteMapper.toWithPerfilDto(clienteRepository.save(cliente));
    }

    @Override
    public ClienteWithPerfilDto unlinkPerfil(Integer idCliente, Integer idPerfil) {
        Cliente cliente = findCliente(idCliente);
        PerfilConsumo perfil = perfilConsumoRepository.findById(idPerfil)
                .orElseThrow(() -> new IllegalArgumentException("Perfil não encontrado."));
        cliente.getPerfilConsumos().remove(perfil);
        return clienteMapper.toWithPerfilDto(clienteRepository.save(cliente));
    }

    @Override
    public ClienteWithSolicitacaoDto linkSolicitacao(Integer idCliente, Integer idSolicitacao) {
        Cliente cliente = findCliente(idCliente);
        SolicitacaoEnergetica solicitacao = solicitacaoRepository.findById(idSolicitacao)
                .orElseThrow(() -> new IllegalArgumentException("Solicitação não encontrada."));
        solicitacao.setCliente(cliente);
        solicitacaoRepository.save(solicitacao);
        return clienteMapper.toWithSolicitacaoDto(findCliente(idCliente));
    }

    @Override
    public ClienteWithSolicitacaoDto unlinkSolicitacao(Integer idCliente, Integer idSolicitacao) {
        SolicitacaoEnergetica solicitacao = solicitacaoRepository.findById(idSolicitacao)
                .orElseThrow(() -> new IllegalArgumentException("Solicitação não encontrada."));
        solicitacao.setCliente(null);
        solicitacaoRepository.save(solicitacao);
        return clienteMapper.toWithSolicitacaoDto(findCliente(idCliente));
    }

    @Override
    public ClienteWithTicketDto linkTicket(Integer idCliente, Integer idTicket) {
        Cliente cliente = findCliente(idCliente);
        Ticket ticket = ticketRepository.findById(idTicket)
                .orElseThrow(() -> new IllegalArgumentException("Ticket não encontrado."));
        ticket.setCliente(cliente);
        ticketRepository.save(ticket);
        return clienteMapper.toWithTicketDto(findCliente(idCliente));
    }

    @Override
    public ClienteWithTicketDto unlinkTicket(Integer idCliente, Integer idTicket) {
        Ticket ticket = ticketRepository.findById(idTicket)
                .orElseThrow(() -> new IllegalArgumentException("Ticket não encontrado."));
        ticket.setCliente(null);
        ticketRepository.save(ticket);
        return clienteMapper.toWithTicketDto(findCliente(idCliente));
    }

    // -------- Helpers --------

    private Cliente findCliente(Integer id) {
        return clienteRepository.findByIdWithAllRelations(id)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado com ID: " + id));
    }

    private void clearEmptyEntries(ClienteDetailsExtendedDto dto) {
        if (dto.getEndereco() != null && dto.getEndereco().getId() == null)
            dto.setEndereco(null);

        if (dto.getNome() != null && dto.getNome().isBlank())
            dto.setNome(null);

        if (dto.getEmail() != null && dto.getEmail().isBlank())
            dto.setEmail(null);

        if (dto.getTelefone() != null && dto.getTelefone().isBlank())
            dto.setTelefone(null);

        if (dto.getNif() != null && dto.getNif().isBlank())
            dto.setNif(null);

        if (dto.getTipoEnergia() != null && dto.getTipoEnergia().isBlank())
            dto.setTipoEnergia(null);

        if (dto.getCatConsumo() != null && dto.getCatConsumo().isBlank())
            dto.setCatConsumo(null);

        if (dto.getEstado() != null && dto.getEstado().isBlank())
            dto.setEstado(null);
    }

    private Specification<Cliente> filtroCliente(ClienteFiltroDto filtro) {
        return (root, query, cb) -> {
            Predicate predicate = cb.conjunction();

            if (filtro.getId() != null)
                predicate = cb.and(predicate, cb.equal(root.get("id"), filtro.getId()));
            if (filtro.getNome() != null)
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
            if (filtro.getDataCadastroInicio() != null)
                predicate = cb.and(predicate, cb.greaterThanOrEqualTo(root.get("dataCadastro"), filtro.getDataCadastroInicio()));
            if (filtro.getDataCadastroFim() != null)
                predicate = cb.and(predicate, cb.lessThanOrEqualTo(root.get("dataCadastro"), filtro.getDataCadastroFim()));
            if (filtro.getDemandaContratadaMin() != null)
                predicate = cb.and(predicate, cb.greaterThanOrEqualTo(root.get("demandaContratada"), filtro.getDemandaContratadaMin()));
            if (filtro.getDemandaContratadaMax() != null)
                predicate = cb.and(predicate, cb.lessThanOrEqualTo(root.get("demandaContratada"), filtro.getDemandaContratadaMax()));
            if (filtro.getConsumoMedioMin() != null)
                predicate = cb.and(predicate, cb.greaterThanOrEqualTo(root.get("consumoMedio"), filtro.getConsumoMedioMin()));
            if (filtro.getConsumoMedioMax() != null)
                predicate = cb.and(predicate, cb.lessThanOrEqualTo(root.get("consumoMedio"), filtro.getConsumoMedioMax()));
            if (filtro.getCatConsumo() != null)
                predicate = cb.and(predicate, cb.equal(root.get("catConsumo"), filtro.getCatConsumo()));
            if (filtro.getTipoEnergia() != null)
                predicate = cb.and(predicate, cb.equal(root.get("tipoEnergia"), filtro.getTipoEnergia()));
            if (filtro.getEstado() != null)
                predicate = cb.and(predicate, cb.equal(root.get("estado"), filtro.getEstado()));
            if (filtro.getNPorta() != null)
                predicate = cb.and(predicate, cb.equal(root.get("nPorta"), filtro.getNPorta()));
            if (filtro.getIdEndereco() != null)
                predicate = cb.and(predicate, cb.equal(root.get("endereco").get("id"), filtro.getIdEndereco()));

            return predicate;
        };
    }
}