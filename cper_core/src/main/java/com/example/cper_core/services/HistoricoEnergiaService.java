package com.example.cper_core.services;

import com.example.cper_core.dtos.historico_energia.*;
import com.example.cper_core.entities.HistoricoEnergia;
import com.example.cper_core.entities.PedidoGeracao;
import com.example.cper_core.enums.EstadoPedidoGeracao;
import com.example.cper_core.mappers.HistoricoEnergiaMapper;
import com.example.cper_core.repositories.HistoricoEnergiaRepository;
import com.example.cper_core.repositories.PedidoGeracaoRepository;
import com.example.cper_core.services.interfaces.IHistoricoEnergiaService;
import com.example.cper_core.specifications.HistoricoEnergiaSpecification;
import jakarta.validation.Validator;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class HistoricoEnergiaService extends AbstractXService<
        HistoricoEnergia,
        HistoricoEnergiaDto,
        HistoricoEnergiaDetailsDto,
        HistoricoEnergiaDetailsExtendedDto,
        HistoricoEnergiaFiltroDto,
        HistoricoEnergiaDetailsExtendedDto,
        Integer
        > implements IHistoricoEnergiaService {

    private final HistoricoEnergiaMapper mapper;
    private final PedidoGeracaoRepository pedidoGeracaoRepository;
    private final HistoricoEnergiaRepository historicoEnergiaRepository;

    public HistoricoEnergiaService(
            HistoricoEnergiaRepository historicoEnergiaRepository,
            PedidoGeracaoRepository pedidoGeracaoRepository,
            HistoricoEnergiaMapper mapper,
            Validator validator) {
        super(historicoEnergiaRepository, historicoEnergiaRepository, validator);
        this.mapper = mapper;
        this.pedidoGeracaoRepository = pedidoGeracaoRepository;
        this.historicoEnergiaRepository = historicoEnergiaRepository;
    }

    @Override
    protected HistoricoEnergia toEntity(HistoricoEnergiaDetailsExtendedDto dto) {
        HistoricoEnergia entity = mapper.toEntity(dto);

        if (dto.getPedidoGeracao() != null && dto.getPedidoGeracao().getId() != null) {
            PedidoGeracao pedido = pedidoGeracaoRepository.findById(dto.getPedidoGeracao().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Pedido de geração não encontrado"));
            entity.setPedidoGeracao(pedido);
        }

        return entity;
    }

    @Override
    protected void updateEntityFromDto(HistoricoEnergiaDetailsExtendedDto dto, HistoricoEnergia entity) {
        if (dto.getData() != null) entity.setData(dto.getData());
        if (dto.getEnergiaGerada() != null) entity.setEnergiaGerada(dto.getEnergiaGerada());
        if (dto.getEnergiaHora() != null) entity.setEnergiaHora(dto.getEnergiaHora());

        if (dto.getPedidoGeracao() != null && dto.getPedidoGeracao().getId() != null) {
            PedidoGeracao pedido = pedidoGeracaoRepository.findById(dto.getPedidoGeracao().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Pedido de geração não encontrado"));
            entity.setPedidoGeracao(pedido);
        }
    }

    @Override
    protected HistoricoEnergiaDetailsExtendedDto toExtendedDto(HistoricoEnergia entity) {
        return mapper.toExtendedDto(entity);
    }

    @Override
    protected HistoricoEnergiaDetailsDto toDetailsDto(HistoricoEnergia entity) {
        return mapper.toDetailsDto(entity);
    }

    @Override
    protected Specification<HistoricoEnergia> getSpecificationFromFiltro(HistoricoEnergiaFiltroDto filtro) {
        return HistoricoEnergiaSpecification.filter(filtro);
    }

    @Override
    protected void marcarComoEliminado(HistoricoEnergia entity) {
        throw new UnsupportedOperationException("Histórico de energia não pode ser eliminado logicamente.");
    }

    @Scheduled(cron = "0 0 * * * *")
    public void processarTodosOsPedidosAtivos() {
        OffsetDateTime agora = OffsetDateTime.now().truncatedTo(ChronoUnit.HOURS);

        List<PedidoGeracao> pedidos = pedidoGeracaoRepository.findAll();

        for (PedidoGeracao pedido : pedidos) {
            try {
                atualizarEnergiaPorHora(pedido, agora);
            } catch (Exception e) {
                System.err.println("Erro ao atualizar energia para o pedido ID " + pedido.getId());
            }
        }
    }


    @Transactional
    public void atualizarEnergiaPorHora(PedidoGeracao pedido, OffsetDateTime fim) {
        OffsetDateTime fimHora = fim.truncatedTo(ChronoUnit.HOURS);

        Optional<HistoricoEnergia> ultimo = historicoEnergiaRepository
                .findTopByPedidoGeracaoOrderByDataDesc(pedido);

        OffsetDateTime inicio = ultimo.map(HistoricoEnergia::getData)
                .orElse(pedido.getDataCriacao().truncatedTo(ChronoUnit.HOURS));

        long horas = ChronoUnit.HOURS.between(inicio, fimHora);

        BigDecimal energiaHoraAtual = pedido.getQtdEnergiaH();
        BigDecimal energiaGerada = energiaHoraAtual.multiply(BigDecimal.valueOf(Math.max(horas, 1)));

        BigDecimal produzido = pedido.getQtdEnergiaProduzida() != null
                ? pedido.getQtdEnergiaProduzida()
                : BigDecimal.ZERO;

        BigDecimal limite = pedido.getQtdEnergia();
        BigDecimal restante = limite.subtract(produzido);

        if (restante.compareTo(BigDecimal.ZERO) <= 0) return;

        boolean deveForcarUpdate = horas == 0 && (
                pedido.getQtdEnergiaProduzidaH() == null ||
                        pedido.getQtdEnergiaProduzidaH().compareTo(energiaHoraAtual) != 0
        );

        if (horas <= 0 && !deveForcarUpdate) return;

        if (energiaGerada.compareTo(restante) > 0) {
            energiaGerada = restante;
            horas = energiaGerada.divide(energiaHoraAtual, 0, RoundingMode.DOWN).longValue();
        }

        if (energiaGerada.compareTo(BigDecimal.ZERO) <= 0 || horas <= 0) return;

        HistoricoEnergia novo = HistoricoEnergia.builder()
                .data(fimHora)
                .energiaHora(energiaHoraAtual)
                .energiaGerada(energiaGerada)
                .pedidoGeracao(pedido)
                .build();

        historicoEnergiaRepository.save(novo);

        pedido.setQtdEnergiaProduzida(produzido.add(energiaGerada));
        pedido.setQtdEnergiaProduzidaH(energiaHoraAtual);

        if (pedido.getQtdEnergiaProduzida().compareTo(limite) >= 0) {
            pedido.setEstado(EstadoPedidoGeracao.CONCLUIDO.getId());
        }

        pedidoGeracaoRepository.save(pedido);
    }
}