package com.example.cper_core.services;

import com.example.cper_core.dtos.pedido_material.*;
import com.example.cper_core.entities.Avaria;
import com.example.cper_core.entities.PedidoMaterial;
import com.example.cper_core.mappers.PedidoMaterialMapper;
import com.example.cper_core.repositories.AvariaRepository;
import com.example.cper_core.repositories.PedidoMaterialRepository;
import com.example.cper_core.services.interfaces.IPedidoMaterialService;
import com.example.cper_core.specifications.PedidoMaterialSpecification;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class PedidoMaterialService extends AbstractXService<
        PedidoMaterial,
        PedidoMaterialDto,
        PedidoMaterialDetailsDto,
        PedidoMaterialDetailsExtendedDto,
        PedidoMaterialFiltroDto,
        Integer
        > implements IPedidoMaterialService {

    private final PedidoMaterialRepository pedidoMaterialRepository;
    private final PedidoMaterialMapper pedidoMaterialMapper;
    private final AvariaRepository avariaRepository;

    public PedidoMaterialService(
            PedidoMaterialRepository pedidoMaterialRepository,
            PedidoMaterialMapper pedidoMaterialMapper,
            jakarta.validation.Validator validator, AvariaRepository avariaRepository
    ) {
        super(pedidoMaterialRepository, pedidoMaterialRepository, validator);
        this.pedidoMaterialRepository = pedidoMaterialRepository;
        this.pedidoMaterialMapper = pedidoMaterialMapper;
        this.avariaRepository = avariaRepository;
    }

    @Override
    protected PedidoMaterial toEntity(PedidoMaterialDetailsExtendedDto dto) {
        return pedidoMaterialMapper.toEntity(dto);
    }

    @Override
    protected void updateEntityFromDto(PedidoMaterialDetailsExtendedDto dto, PedidoMaterial entity) {
        pedidoMaterialMapper.updateEntityFromExtendedDto(dto, entity);
    }

    @Override
    protected PedidoMaterialDetailsExtendedDto toExtendedDto(PedidoMaterial entity) {
        return pedidoMaterialMapper.toExtendedDto(entity);
    }

    @Override
    protected PedidoMaterialDetailsDto toDetailsDto(PedidoMaterial entity) {
        return pedidoMaterialMapper.toDetailsDto(entity);
    }

    @Override
    protected Specification<PedidoMaterial> getSpecificationFromFiltro(PedidoMaterialFiltroDto filtro) {
        return PedidoMaterialSpecification.filter(filtro);
    }

    @Override
    protected void markedDeleted(PedidoMaterial entity) {
        entity.setIsDeleted(true);
    }

    // --- Linking and Unlinking Methods ---
    @Override
    public void linkToAvarias(Integer idPedidoMaterial, Set<Integer> idsAvarias) {
        PedidoMaterial pedidoMaterial = getEntity(idPedidoMaterial);

        Set<Avaria> avarias = idsAvarias.stream()
                .map(id -> avariaRepository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException("Avaria não encontrada: " + id))) // Exceção personalizada
                .collect(Collectors.toSet());
        pedidoMaterial.getAvarias().addAll(avarias);

        pedidoMaterialRepository.save(pedidoMaterial);
    }

    @Override
    public void unlinkFromAvarias(Integer idPedidoMaterial, Set<Integer> idsAvarias) {
        PedidoMaterial pedidoMaterial = getEntity(idPedidoMaterial);

        pedidoMaterial.getAvarias().removeIf(avaria -> idsAvarias.contains(avaria.getId()));

        pedidoMaterialRepository.save(pedidoMaterial);
    }

    // --- Aux ---
    private PedidoMaterial getEntity(Integer id) {
        return pedidoMaterialRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido de Material não encontrado"));
    }
}