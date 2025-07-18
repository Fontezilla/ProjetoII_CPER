package com.example.cper_core.services;

import com.example.cper_core.dtos.departamento.*;
import com.example.cper_core.entities.*;
import com.example.cper_core.mappers.DepartamentoMapper;
import com.example.cper_core.repositories.*;
import com.example.cper_core.services.interfaces.IDepartamentoService;
import com.example.cper_core.specifications.DepartamentoSpecification;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Validator;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class DepartamentoService extends AbstractXService<
        Departamento,
        DepartamentoDto,
        DepartamentoDetailsDto,
        DepartamentoDetailsExtendedDto,
        DepartamentoFiltroDto,
        Integer
        > implements IDepartamentoService {

    private final DepartamentoRepository departamentoRepository;
    private final DepartamentoMapper departamentoMapper;
    private final FuncionarioRepository funcionarioRepository;

    public DepartamentoService(
            DepartamentoRepository departamentoRepository,
            DepartamentoMapper departamentoMapper,
            FuncionarioRepository funcionarioRepository,
            Validator validator
    ) {
        super(departamentoRepository, departamentoRepository, validator);
        this.departamentoRepository = departamentoRepository;
        this.departamentoMapper = departamentoMapper;
        this.funcionarioRepository = funcionarioRepository;
    }

    @Override
    protected Departamento toEntity(DepartamentoDetailsExtendedDto dto) {
        return departamentoMapper.toEntity(dto);
    }

    @Override
    protected void updateEntityFromDto(DepartamentoDetailsExtendedDto dto, Departamento entity) {
        departamentoMapper.updateEntityFromExtendedDto(dto, entity);
    }

    @Override
    protected DepartamentoDetailsExtendedDto toExtendedDto(Departamento entity) {
        return departamentoMapper.toExtendedDto(entity);
    }

    @Override
    protected DepartamentoDetailsDto toDetailsDto(Departamento entity) {
        return departamentoMapper.toDetailsDto(entity);
    }

    @Override
    protected Specification<Departamento> getSpecificationFromFiltro(DepartamentoFiltroDto filtro) {
        return DepartamentoSpecification.filter(filtro);
    }

    @Override
    protected void markedDeleted(Departamento entity) {
        entity.setIsDeleted(true);
    }

    // --- Linking and Unlinking Methods ---
    @Override
    public void linkToResponsaveis(Integer id, Set<Integer> ids) {
        Departamento dep = getEntity(id);

        Set<Funcionario> list = ids.stream()
                .map(i -> funcionarioRepository.findById(i)
                        .orElseThrow(() -> new EntityNotFoundException("Responsável não encontrado: " + i)))
                .collect(Collectors.toSet());
        dep.getResponsaveis().addAll(list);

        departamentoRepository.save(dep);
    }

    @Override
    public void unlinkFromResponsaveis(Integer id, Set<Integer> ids) {
        Departamento dep = getEntity(id);

        dep.getResponsaveis().removeIf(e -> ids.contains(e.getId()));

        departamentoRepository.save(dep);
    }

    // --- Aux ---
    private Departamento getEntity(Integer id) {
        return departamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Departamento não encontrado"));
    }
}