package com.example.cper_core.services;

import com.example.cper_core.dtos.contrato.*;
import com.example.cper_core.entities.*;
import com.example.cper_core.enums.EstadoContrato;
import com.example.cper_core.enums.TipoContrato;
import com.example.cper_core.mappers.ContratoMapper;
import com.example.cper_core.repositories.*;
import com.example.cper_core.services.interfaces.IContratoService;
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
public class ContratoService implements IContratoService {

    private final ContratoRepository contratoRepository;
    private final PedidoGeracaoRepository pedidoGeracaoRepository;
    private final FaturaRepository faturaRepository;
    private final ContratoMapper contratoMapper;

    @Autowired
    public ContratoService(ContratoRepository contratoRepository,
                           PedidoGeracaoRepository pedidoGeracaoRepository,
                           FaturaRepository faturaRepository,
                           ContratoMapper contratoMapper) {
        this.contratoRepository = contratoRepository;
        this.pedidoGeracaoRepository = pedidoGeracaoRepository;
        this.faturaRepository = faturaRepository;
        this.contratoMapper = contratoMapper;
    }

    @Override
    public Page<ContratoDetailsDto> listAll(Pageable pageable) {
        return contratoRepository.findAll(pageable).map(contratoMapper::toDetailsDto);
    }

    @Override
    public Page<ContratoDetailsDto> listFiltered(Pageable pageable, ContratoFiltroDto filtro) {
        Specification<Contrato> spec = filtroContrato(filtro);
        return contratoRepository.findAll(spec, pageable).map(contratoMapper::toDetailsDto);
    }

    @Override
    public Optional<ContratoDetailsExtendedDto> getById(Integer id) {
        return contratoRepository.findByIdWithAllRelations(id).map(contratoMapper::toDetailsExtendedDto);
    }

    @Override
    public ContratoDetailsExtendedDto create(ContratoDetailsExtendedDto dto) {
        Contrato entity = contratoMapper.toEntity(dto);
        return contratoMapper.toDetailsExtendedDto(contratoRepository.save(entity));
    }

    @Override
    public ContratoDetailsExtendedDto update(Integer id, ContratoDetailsExtendedDto dto) {
        if (dto.getId() == null || !dto.getId().equals(id))
            throw new IllegalArgumentException("ID do corpo e da URL não coincidem.");

        Contrato entity = contratoRepository.findByIdWithAllRelations(id)
                .orElseThrow(() -> new IllegalArgumentException("Contrato não encontrado."));

        contratoMapper.updateEntityFromExtendedDto(dto, entity);
        return contratoMapper.toDetailsExtendedDto(contratoRepository.save(entity));
    }

    @Override
    public void softDelete(Integer id) {
        Contrato entity = contratoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Contrato não encontrado."));
        entity.setEstado(EstadoContrato.APAGADO.getId());
        contratoRepository.save(entity);
    }

    @Override
    public ContratoWithPedidoDto linkPedido(Integer idContrato, Integer idPedido) {
        Contrato contrato = findContrato(idContrato);
        PedidoGeracao pedido = pedidoGeracaoRepository.findById(idPedido)
                .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado."));
        pedido.setContrato(contrato);
        pedidoGeracaoRepository.save(pedido);
        return contratoMapper.toWithPedidoDto(findContrato(idContrato));
    }

    @Override
    public ContratoWithPedidoDto unlinkPedido(Integer idContrato, Integer idPedido) {
        PedidoGeracao pedido = pedidoGeracaoRepository.findById(idPedido)
                .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado."));
        pedido.setContrato(null);
        pedidoGeracaoRepository.save(pedido);
        return contratoMapper.toWithPedidoDto(findContrato(idContrato));
    }

    @Override
    public ContratoWithFaturaDto linkFatura(Integer idContrato, Integer idFatura) {
        Contrato contrato = findContrato(idContrato);
        Fatura fatura = faturaRepository.findById(idFatura)
                .orElseThrow(() -> new IllegalArgumentException("Fatura não encontrada."));
        fatura.setContrato(contrato);
        faturaRepository.save(fatura);
        return contratoMapper.toWithFaturaDto(findContrato(idContrato));
    }

    @Override
    public ContratoWithFaturaDto unlinkFatura(Integer idContrato, Integer idFatura) {
        Fatura fatura = faturaRepository.findById(idFatura)
                .orElseThrow(() -> new IllegalArgumentException("Fatura não encontrada."));
        fatura.setContrato(null);
        faturaRepository.save(fatura);
        return contratoMapper.toWithFaturaDto(findContrato(idContrato));
    }

    private Contrato findContrato(Integer id) {
        return contratoRepository.findByIdWithAllRelations(id)
                .orElseThrow(() -> new IllegalArgumentException("Contrato não encontrado com ID: " + id));
    }

    private Specification<Contrato> filtroContrato(ContratoFiltroDto filtro) {
        return (root, query, cb) -> {
            Predicate predicate = cb.conjunction();

            if (filtro.getId() != null)
                predicate = cb.and(predicate, cb.equal(root.get("id"), filtro.getId()));
            if (filtro.getDataInicioInicio() != null)
                predicate = cb.and(predicate, cb.greaterThanOrEqualTo(root.get("dataInicio"), filtro.getDataInicioInicio()));
            if (filtro.getDataInicioFim() != null)
                predicate = cb.and(predicate, cb.lessThanOrEqualTo(root.get("dataInicio"), filtro.getDataInicioFim()));
            if (filtro.getDataFimInicio() != null)
                predicate = cb.and(predicate, cb.greaterThanOrEqualTo(root.get("dataFim"), filtro.getDataFimInicio()));
            if (filtro.getDataFimFim() != null)
                predicate = cb.and(predicate, cb.lessThanOrEqualTo(root.get("dataFim"), filtro.getDataFimFim()));
            if (filtro.getTipoContrato() != null)
                predicate = cb.and(predicate, cb.equal(root.get("tipoContrato"), filtro.getTipoContrato()));
            if (filtro.getQtdEnergiaMin() != null)
                predicate = cb.and(predicate, cb.ge(root.get("qtdEnergia"), filtro.getQtdEnergiaMin()));
            if (filtro.getQtdEnergiaMax() != null)
                predicate = cb.and(predicate, cb.le(root.get("qtdEnergia"), filtro.getQtdEnergiaMax()));
            if (filtro.getPrazoPagamentoMin() != null)
                predicate = cb.and(predicate, cb.ge(root.get("prazoPagamento"), filtro.getPrazoPagamentoMin()));
            if (filtro.getPrazoPagamentoMax() != null)
                predicate = cb.and(predicate, cb.le(root.get("prazoPagamento"), filtro.getPrazoPagamentoMax()));
            if (filtro.getMultaAtrasoMin() != null)
                predicate = cb.and(predicate, cb.ge(root.get("multaAtraso"), filtro.getMultaAtrasoMin()));
            if (filtro.getMultaAtrasoMax() != null)
                predicate = cb.and(predicate, cb.le(root.get("multaAtraso"), filtro.getMultaAtrasoMax()));
            if (filtro.getEstado() != null)
                predicate = cb.and(predicate, cb.equal(root.get("estado"), filtro.getEstado()));
            if (filtro.getNPorta() != null)
                predicate = cb.and(predicate, cb.equal(root.get("nPorta"), filtro.getNPorta()));
            if (filtro.getIdFuncionario() != null)
                predicate = cb.and(predicate, cb.equal(root.get("funcionario").get("id"), filtro.getIdFuncionario()));
            if (filtro.getIdEndereco() != null)
                predicate = cb.and(predicate, cb.equal(root.get("endereco").get("id"), filtro.getIdEndereco()));

            return predicate;
        };
    }
}
