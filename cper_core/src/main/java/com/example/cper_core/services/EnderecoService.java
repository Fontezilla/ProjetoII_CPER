package com.example.cper_core.services;

import com.example.cper_core.dtos.endereco.*;
import com.example.cper_core.entities.*;
import com.example.cper_core.mappers.EnderecoMapper;
import com.example.cper_core.repositories.*;
import com.example.cper_core.services.interfaces.IEnderecoService;
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
public class EnderecoService implements IEnderecoService {

    private final EnderecoRepository enderecoRepository;
    private final FuncionarioRepository funcionarioRepository;
    private final ClienteRepository clienteRepository;
    private final ContratoRepository contratoRepository;
    private final ArmazemRepository armazemRepository;
    private final CentroProducaoRepository centroProducaoRepository;
    private final LocalidadeRepository localidadeRepository;
    private final EnderecoMapper enderecoMapper;

    @Autowired
    public EnderecoService(
            EnderecoRepository enderecoRepository,
            FuncionarioRepository funcionarioRepository,
            ClienteRepository clienteRepository,
            ContratoRepository contratoRepository,
            ArmazemRepository armazemRepository,
            CentroProducaoRepository centroProducaoRepository,
            LocalidadeRepository localidadeRepository,
            EnderecoMapper enderecoMapper
    ) {
        this.enderecoRepository = enderecoRepository;
        this.funcionarioRepository = funcionarioRepository;
        this.clienteRepository = clienteRepository;
        this.contratoRepository = contratoRepository;
        this.armazemRepository = armazemRepository;
        this.centroProducaoRepository = centroProducaoRepository;
        this.localidadeRepository = localidadeRepository;
        this.enderecoMapper = enderecoMapper;
    }

    @Override
    public Page<EnderecoDetailsDto> listAll(Pageable pageable) {
        return enderecoRepository.findAll(pageable).map(enderecoMapper::toDetailsDto);
    }

    @Override
    public Page<EnderecoDetailsDto> listFiltered(Pageable pageable, EnderecoFiltroDto filtro) {
        return enderecoRepository.findAll(filtroEndereco(filtro), pageable).map(enderecoMapper::toDetailsDto);
    }

    @Override
    public Optional<EnderecoDetailsDto> getById(Integer id) {
        return enderecoRepository.findByIdWithAllRelations(id).map(enderecoMapper::toDetailsDto);
    }

    @Override
    public EnderecoDetailsDto create(EnderecoDetailsDto dto) {
        Endereco entity = enderecoMapper.toEntity(dto);
        entity.setLocalidade(localidadeRepository.findById(dto.getLocalidade().getId())
                .orElseThrow(() -> new IllegalArgumentException("Localidade não encontrada.")));
        return enderecoMapper.toDetailsDto(enderecoRepository.save(entity));
    }

    @Override
    public EnderecoDetailsDto update(Integer id, EnderecoDetailsDto dto) {
        if (dto.getId() == null || !dto.getId().equals(id))
            throw new IllegalArgumentException("ID do corpo e da URL não coincidem.");

        Endereco entity = enderecoRepository.findByIdWithAllRelations(id)
                .orElseThrow(() -> new IllegalArgumentException("Endereço não encontrado."));

        enderecoMapper.updateEntityFromExtendedDto(dto, entity);
        if (dto.getLocalidade() != null) {
            entity.setLocalidade(localidadeRepository.findById(dto.getLocalidade().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Localidade não encontrada.")));
        }
        return enderecoMapper.toDetailsDto(enderecoRepository.save(entity));
    }

    @Override
    public void softDelete(Integer id) {
        Endereco entity = enderecoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Endereço não encontrado."));
        entity.setRua("REMOVIDO");
        enderecoRepository.save(entity);
    }

    // -------- Association methods --------

    @Override
    public EnderecoWithArmazemDto linkArmazem(Integer idEndereco, Integer idArmazem) {
        Endereco endereco = getEndereco(idEndereco);
        Armazem armazem = armazemRepository.findById(idArmazem)
                .orElseThrow(() -> new IllegalArgumentException("Armazém não encontrado."));
        armazem.setEndereco(endereco);
        armazemRepository.save(armazem);
        return enderecoMapper.toWithArmazemDto(endereco);
    }

    @Override
    public EnderecoWithArmazemDto unlinkArmazem(Integer idEndereco, Integer idArmazem) {
        Armazem armazem = armazemRepository.findById(idArmazem)
                .orElseThrow(() -> new IllegalArgumentException("Armazém não encontrado."));
        armazem.setEndereco(null);
        armazemRepository.save(armazem);
        return enderecoMapper.toWithArmazemDto(getEndereco(idEndereco));
    }

    @Override
    public EnderecoWithCentroDto linkCentro(Integer idEndereco, Integer idCentro) {
        Endereco endereco = getEndereco(idEndereco);
        CentroProducao centro = centroProducaoRepository.findById(idCentro)
                .orElseThrow(() -> new IllegalArgumentException("Centro de produção não encontrado."));
        centro.setEndereco(endereco);
        centroProducaoRepository.save(centro);
        return enderecoMapper.toWithCentroDto(endereco);
    }

    @Override
    public EnderecoWithCentroDto unlinkCentro(Integer idEndereco, Integer idCentro) {
        CentroProducao centro = centroProducaoRepository.findById(idCentro)
                .orElseThrow(() -> new IllegalArgumentException("Centro de produção não encontrado."));
        centro.setEndereco(null);
        centroProducaoRepository.save(centro);
        return enderecoMapper.toWithCentroDto(getEndereco(idEndereco));
    }

    @Override
    public EnderecoWithClienteDto linkCliente(Integer idEndereco, Integer idCliente) {
        Endereco endereco = getEndereco(idEndereco);
        Cliente cliente = clienteRepository.findById(idCliente)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado."));
        cliente.setEndereco(endereco);
        clienteRepository.save(cliente);
        return enderecoMapper.toWithClienteDto(endereco);
    }

    @Override
    public EnderecoWithClienteDto unlinkCliente(Integer idEndereco, Integer idCliente) {
        Cliente cliente = clienteRepository.findById(idCliente)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado."));
        cliente.setEndereco(null);
        clienteRepository.save(cliente);
        return enderecoMapper.toWithClienteDto(getEndereco(idEndereco));
    }

    @Override
    public EnderecoWithContratoDto linkContrato(Integer idEndereco, Integer idContrato) {
        Endereco endereco = getEndereco(idEndereco);
        Contrato contrato = contratoRepository.findById(idContrato)
                .orElseThrow(() -> new IllegalArgumentException("Contrato não encontrado."));
        contrato.setEndereco(endereco);
        contratoRepository.save(contrato);
        return enderecoMapper.toWithContratoDto(endereco);
    }

    @Override
    public EnderecoWithContratoDto unlinkContrato(Integer idEndereco, Integer idContrato) {
        Contrato contrato = contratoRepository.findById(idContrato)
                .orElseThrow(() -> new IllegalArgumentException("Contrato não encontrado."));
        contrato.setEndereco(null);
        contratoRepository.save(contrato);
        return enderecoMapper.toWithContratoDto(getEndereco(idEndereco));
    }

    @Override
    public EnderecoWithFuncionarioDto linkFuncionario(Integer idEndereco, Integer idFuncionario) {
        Endereco endereco = getEndereco(idEndereco);
        Funcionario funcionario = funcionarioRepository.findById(idFuncionario)
                .orElseThrow(() -> new IllegalArgumentException("Funcionário não encontrado."));
        funcionario.setEndereco(endereco);
        funcionarioRepository.save(funcionario);
        return enderecoMapper.toWithFuncionarioDto(endereco);
    }

    @Override
    public EnderecoWithFuncionarioDto unlinkFuncionario(Integer idEndereco, Integer idFuncionario) {
        Funcionario funcionario = funcionarioRepository.findById(idFuncionario)
                .orElseThrow(() -> new IllegalArgumentException("Funcionário não encontrado."));
        funcionario.setEndereco(null);
        funcionarioRepository.save(funcionario);
        return enderecoMapper.toWithFuncionarioDto(getEndereco(idEndereco));
    }

    // -------- Helpers --------

    private Endereco getEndereco(Integer id) {
        return enderecoRepository.findByIdWithAllRelations(id)
                .orElseThrow(() -> new IllegalArgumentException("Endereço não encontrado."));
    }

    private Specification<Endereco> filtroEndereco(EnderecoFiltroDto filtro) {
        return (root, query, cb) -> {
            Predicate predicate = cb.conjunction();
            if (filtro.getId() != null)
                predicate = cb.and(predicate, cb.equal(root.get("id"), filtro.getId()));
            if (filtro.getRua() != null && !filtro.getRua().isBlank())
                predicate = cb.and(predicate, cb.like(cb.lower(root.get("rua")), "%" + filtro.getRua().toLowerCase() + "%"));
            if (filtro.getIdLocalidade() != null)
                predicate = cb.and(predicate, cb.equal(root.get("localidade").get("id"), filtro.getIdLocalidade()));
            return predicate;
        };
    }
}