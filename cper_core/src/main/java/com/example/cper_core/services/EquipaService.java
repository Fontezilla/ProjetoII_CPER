package com.example.cper_core.services;

import com.example.cper_core.dtos.equipa.*;
import com.example.cper_core.entities.*;
import com.example.cper_core.mappers.EquipaMapper;
import com.example.cper_core.repositories.*;
import com.example.cper_core.services.interfaces.IEquipaService;
import com.example.cper_core.specifications.EquipaSpecification;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Validator;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class EquipaService extends AbstractXService<
        Equipa,
        EquipaDto,
        EquipaDetailsDto,
        EquipaDetailsExtendedDto,
        EquipaFiltroDto,
        Integer
        > implements IEquipaService {

    private final EquipaRepository equipaRepository;
    private final EquipaMapper equipaMapper;
    private final FuncionarioRepository funcionarioRepository;
    private final AvariaRepository avariaRepository;
    private final InspecaoRepository inspecaoRepository;

    public EquipaService(
            EquipaRepository equipaRepository,
            EquipaMapper equipaMapper,
            FuncionarioRepository funcionarioRepository,
            AvariaRepository avariaRepository,
            InspecaoRepository inspecaoRepository,
            Validator validator
    ) {
        super(equipaRepository, equipaRepository, validator);
        this.equipaRepository = equipaRepository;
        this.equipaMapper = equipaMapper;
        this.funcionarioRepository = funcionarioRepository;
        this.avariaRepository = avariaRepository;
        this.inspecaoRepository = inspecaoRepository;
    }

    @Override
    protected Equipa toEntity(EquipaDetailsExtendedDto dto) {
        return equipaMapper.toEntity(dto);
    }

    @Override
    protected void updateEntityFromDto(EquipaDetailsExtendedDto dto, Equipa entity) {
        equipaMapper.updateEntityFromExtendedDto(dto, entity);
    }

    @Override
    protected EquipaDetailsExtendedDto toExtendedDto(Equipa entity) {
        return equipaMapper.toExtendedDto(entity);
    }

    @Override
    protected EquipaDetailsDto toDetailsDto(Equipa entity) {
        return equipaMapper.toDetailsDto(entity);
    }

    @Override
    protected Specification<Equipa> getSpecificationFromFiltro(EquipaFiltroDto filtro) {
        return EquipaSpecification.filter(filtro);
    }

    @Override
    protected void markedDeleted(Equipa entity) {
        entity.setIsDeleted(true);
    }

    // --- Linking and Unlinking Methods ---
    @Override
    public void linkToFuncionarios(Integer idEquipa, Set<Integer> idsFuncionarios) {
        Equipa equipa = getEntity(idEquipa);
        Set<Funcionario> funcionarios = idsFuncionarios.stream()
                .map(id -> funcionarioRepository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException("Funcionário não encontrado: " + id)))
                .collect(Collectors.toSet());
        equipa.getFuncionarios().addAll(funcionarios);
        equipaRepository.save(equipa);
    }

    @Override
    public void unlinkFromFuncionarios(Integer idEquipa, Set<Integer> idsFuncionarios) {
        Equipa equipa = getEntity(idEquipa);
        equipa.getFuncionarios().removeIf(f -> idsFuncionarios.contains(f.getId()));
        equipaRepository.save(equipa);
    }

    @Override
    public void linkToAvarias(Integer idEquipa, Set<Integer> idsAvarias) {
        Equipa equipa = getEntity(idEquipa);
        Set<Avaria> avarias = idsAvarias.stream()
                .map(id -> avariaRepository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException("Avaria não encontrada: " + id)))
                .collect(Collectors.toSet());
        equipa.getAvarias().addAll(avarias);
        equipaRepository.save(equipa);
    }

    @Override
    public void unlinkFromAvarias(Integer idEquipa, Set<Integer> idsAvarias) {
        Equipa equipa = getEntity(idEquipa);
        equipa.getAvarias().removeIf(a -> idsAvarias.contains(a.getId()));
        equipaRepository.save(equipa);
    }

    @Override
    public void linkToInspecoes(Integer idEquipa, Set<Integer> idsInspecoes) {
        Equipa equipa = getEntity(idEquipa);
        Set<Inspecao> inspecoes = idsInspecoes.stream()
                .map(id -> inspecaoRepository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException("Inspeção não encontrada: " + id)))
                .collect(Collectors.toSet());
        equipa.getInspecoes().addAll(inspecoes);
        equipaRepository.save(equipa);
    }

    @Override
    public void unlinkFromInspecoes(Integer idEquipa, Set<Integer> idsInspecoes) {
        Equipa equipa = getEntity(idEquipa);
        equipa.getInspecoes().removeIf(i -> idsInspecoes.contains(i.getId()));
        equipaRepository.save(equipa);
    }


    // --- Aux ---
    private Equipa getEntity(Integer id) {
        return equipaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Equipa não encontrada"));
    }
}