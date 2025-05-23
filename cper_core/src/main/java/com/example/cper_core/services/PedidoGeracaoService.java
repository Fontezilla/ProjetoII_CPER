package com.example.cper_core.services;

import com.example.cper_core.dtos.pedido_geracao.*;
import com.example.cper_core.entities.PedidoGeracao;
import com.example.cper_core.mappers.PedidoGeracaoMapper;
import com.example.cper_core.repositories.CentroProducaoRepository;
import com.example.cper_core.repositories.ContratoRepository;
import com.example.cper_core.repositories.FuncionarioRepository;
import com.example.cper_core.repositories.PedidoGeracaoRepository;
import com.example.cper_core.services.interfaces.IPedidoGeracaoService;
import com.example.cper_core.specifications.PedidoGeracaoSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PedidoGeracaoService extends AbstractXService<
        PedidoGeracao,
        PedidoGeracaoDto,
        PedidoGeracaoDetailsDto,
        PedidoGeracaoDetailsExtendedDto,
        PedidoGeracaoFiltroDto,
        PedidoGeracaoWithRelationshipsDto,
        Integer
        > implements IPedidoGeracaoService {

    private final PedidoGeracaoMapper pedidoGeracaoMapper;

    public PedidoGeracaoService(
            PedidoGeracaoRepository pedidoGeracaoRepository,
            PedidoGeracaoMapper pedidoGeracaoMapper,
            jakarta.validation.Validator validator, ContratoRepository contratoRepository, CentroProducaoRepository centroProducaoRepository, FuncionarioRepository funcionarioRepository
    ) {
        super(pedidoGeracaoRepository, pedidoGeracaoRepository, validator);
        this.pedidoGeracaoMapper = pedidoGeracaoMapper;
    }

    @Override
    protected PedidoGeracao toEntity(PedidoGeracaoDetailsExtendedDto dto) {
        return pedidoGeracaoMapper.toEntity(dto);
    }

    @Override
    protected void updateEntityFromDto(PedidoGeracaoDetailsExtendedDto dto, PedidoGeracao entity) {
        pedidoGeracaoMapper.updateEntityFromExtendedDto(dto, entity);
    }

    @Override
    protected PedidoGeracaoDetailsExtendedDto toExtendedDto(PedidoGeracao entity) {
        return pedidoGeracaoMapper.toExtendedDto(entity);
    }

    @Override
    protected PedidoGeracaoDetailsDto toDetailsDto(PedidoGeracao entity) {
        return pedidoGeracaoMapper.toDetailsDto(entity);
    }

    @Override
    protected Specification<PedidoGeracao> getSpecificationFromFiltro(PedidoGeracaoFiltroDto filtro) {
        return PedidoGeracaoSpecification.filter(filtro);
    }

    @Override
    protected void marcarComoEliminado(PedidoGeracao entity) {
        entity.setIsDeleted(true);
    }
}