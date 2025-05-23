package com.example.cper_core.services;

import com.example.cper_core.dtos.avaria.*;
import com.example.cper_core.entities.*;
import com.example.cper_core.mappers.AvariaMapper;
import com.example.cper_core.repositories.*;
import com.example.cper_core.services.interfaces.IAvariaService;
import com.example.cper_core.specifications.AvariaSpecification;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Validator;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class AvariaService extends AbstractXService<
        Avaria,
        AvariaDto,
        AvariaDetailsDto,
        AvariaDetailsExtendedDto,
        AvariaFiltroDto,
        AvariaWithRelationshipsDto,
        Integer
        > implements IAvariaService {

    private final AvariaRepository avariaRepository;
    private final AvariaMapper avariaMapper;
    private final EquipaRepository equipaRepository;
    private final PedidoMaterialRepository pedidoMaterialRepository;

    public AvariaService(
            AvariaRepository avariaRepository,
            AvariaMapper avariaMapper,
            EquipaRepository equipaRepository,
            PedidoMaterialRepository pedidoMaterialRepository,
            Validator validator
    ) {
        super(avariaRepository, avariaRepository, validator);
        this.avariaRepository = avariaRepository;
        this.avariaMapper = avariaMapper;
        this.equipaRepository = equipaRepository;
        this.pedidoMaterialRepository = pedidoMaterialRepository;
    }

    @Override
    protected Avaria toEntity(AvariaDetailsExtendedDto dto) {
        return avariaMapper.toEntity(dto);
    }

    @Override
    protected void updateEntityFromDto(AvariaDetailsExtendedDto dto, Avaria entity) {
        avariaMapper.updateEntityFromExtendedDto(dto, entity);
    }

    @Override
    protected AvariaDetailsExtendedDto toExtendedDto(Avaria entity) {
        return avariaMapper.toExtendedDto(entity);
    }

    @Override
    protected AvariaDetailsDto toDetailsDto(Avaria entity) {
        return avariaMapper.toDetailsDto(entity);
    }

    @Override
    protected Specification<Avaria> getSpecificationFromFiltro(AvariaFiltroDto filtro) {
        return AvariaSpecification.filter(filtro);
    }

    @Override
    protected void marcarComoEliminado(Avaria entity) {
        entity.setIsDeleted(true);
    }

    // --- Linking and Unlinking Methods ---
    @Override
    public void linkToEquipas(Integer id, Set<Integer> idsEquipas) {
        Avaria avaria = getEntity(id);
        Set<Equipa> equipas = idsEquipas.stream()
                .map(e -> equipaRepository.findById(e)
                        .orElseThrow(() -> new EntityNotFoundException("Equipa não encontrada: " + e)))
                .collect(Collectors.toSet());
        avaria.getEquipas().addAll(equipas);
        avariaRepository.save(avaria);
    }

    @Override
    public void unlinkFromEquipas(Integer id, Set<Integer> idsEquipas) {
        Avaria avaria = getEntity(id);
        avaria.getEquipas().removeIf(e -> idsEquipas.contains(e.getId()));
        avariaRepository.save(avaria);
    }

    @Override
    public void linkToPedidoMateriais(Integer id, Set<Integer> idsPedidos) {
        Avaria avaria = getEntity(id);
        Set<PedidoMaterial> pedidos = idsPedidos.stream()
                .map(p -> pedidoMaterialRepository.findById(p)
                        .orElseThrow(() -> new EntityNotFoundException("PedidoMaterial não encontrado: " + p)))
                .collect(Collectors.toSet());
        avaria.getPedidoMateriais().addAll(pedidos);
        avariaRepository.save(avaria);
    }

    @Override
    public void unlinkFromPedidoMateriais(Integer id, Set<Integer> idsPedidos) {
        Avaria avaria = getEntity(id);
        avaria.getPedidoMateriais().removeIf(p -> idsPedidos.contains(p.getId()));
        avariaRepository.save(avaria);
    }

    // --- Aux ---
    private Avaria getEntity(Integer id) {
        return avariaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Avaria não encontrada"));
    }
}