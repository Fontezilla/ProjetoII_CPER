package com.example.cper_core.services;

import com.example.cper_core.dtos.inspecao.*;
import com.example.cper_core.entities.*;
import com.example.cper_core.mappers.InspecaoMapper;
import com.example.cper_core.repositories.*;
import com.example.cper_core.services.interfaces.IInspecaoService;
import com.example.cper_core.specifications.InspecaoSpecification;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Validator;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class InspecaoService extends AbstractXService<
        Inspecao,
        InspecaoDto,
        InspecaoDetailsDto,
        InspecaoDetailsExtendedDto,
        InspecaoFiltroDto,
        InspecaoWithRelationshipsDto,
        Integer
        > implements IInspecaoService {

    private final InspecaoRepository inspecaoRepository;
    private final InspecaoMapper inspecaoMapper;
    private final EquipaRepository equipaRepository;

    public InspecaoService(
            InspecaoRepository inspecaoRepository,
            InspecaoMapper inspecaoMapper,
            EquipaRepository equipaRepository,
            Validator validator
    ) {
        super(inspecaoRepository, inspecaoRepository, validator);
        this.inspecaoRepository = inspecaoRepository;
        this.inspecaoMapper = inspecaoMapper;
        this.equipaRepository = equipaRepository;
    }

    @Override
    protected Inspecao toEntity(InspecaoDetailsExtendedDto dto) {
        return inspecaoMapper.toEntity(dto);
    }

    @Override
    protected void updateEntityFromDto(InspecaoDetailsExtendedDto dto, Inspecao entity) {
        inspecaoMapper.updateEntityFromExtendedDto(dto, entity);
    }

    @Override
    protected InspecaoDetailsExtendedDto toExtendedDto(Inspecao entity) {
        return inspecaoMapper.toExtendedDto(entity);
    }

    @Override
    protected InspecaoDetailsDto toDetailsDto(Inspecao entity) {
        return inspecaoMapper.toDetailsDto(entity);
    }

    @Override
    protected Specification<Inspecao> getSpecificationFromFiltro(InspecaoFiltroDto filtro) {
        return InspecaoSpecification.filter(filtro);
    }

    @Override
    protected void marcarComoEliminado(Inspecao entity) {
        entity.setIsDeleted(true);
    }

    // --- Linking and Unlinking Methods ---
    @Override
    public void linkToEquipas(Integer idInspecao, Set<Integer> idsEquipas) {
        Inspecao inspecao = getEntity(idInspecao);

        Set<Equipa> novasEquipas = idsEquipas.stream()
                .map(id -> equipaRepository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException("Equipa não encontrada: " + id)))
                .collect(Collectors.toSet());
        inspecao.getEquipas().addAll(novasEquipas);

        inspecaoRepository.save(inspecao);
    }

    @Override
    public void unlinkFromEquipas(Integer idInspecao, Set<Integer> idsEquipas) {
        Inspecao inspecao = getEntity(idInspecao);

        inspecao.getEquipas().removeIf(equipa -> idsEquipas.contains(equipa.getId()));

        inspecaoRepository.save(inspecao);
    }

    // --- Aux ---
    private Inspecao getEntity(Integer id) {
        return inspecaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inspeção não encontrada"));
    }
}