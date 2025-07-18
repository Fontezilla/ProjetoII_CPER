package com.example.cper_core.services;

import com.example.cper_core.dtos.armazem.*;
import com.example.cper_core.entities.*;
import com.example.cper_core.mappers.ArmazemMapper;
import com.example.cper_core.repositories.*;
import com.example.cper_core.services.interfaces.IArmazemService;
import com.example.cper_core.specifications.ArmazemSpecification;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Validator;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class ArmazemService extends AbstractXService<
        Armazem,
        ArmazemDto,
        ArmazemDetailsDto,
        ArmazemDetailsExtendedDto,
        ArmazemFiltroDto,
        Integer
        > implements IArmazemService {

    private final ArmazemRepository armazemRepository;
    private final ArmazemMapper armazemMapper;
    private final FuncionarioRepository funcionarioRepository;

    public ArmazemService(
            ArmazemRepository armazemRepository,
            ArmazemMapper armazemMapper,
            FuncionarioRepository funcionarioRepository,
            Validator validator
    ) {
        super(armazemRepository, armazemRepository, validator);
        this.armazemRepository = armazemRepository;
        this.armazemMapper = armazemMapper;
        this.funcionarioRepository = funcionarioRepository;
    }

    @Override
    protected Armazem toEntity(ArmazemDetailsExtendedDto dto) {
        return armazemMapper.toEntity(dto);
    }

    @Override
    protected void updateEntityFromDto(ArmazemDetailsExtendedDto dto, Armazem entity) {
        armazemMapper.updateEntityFromExtendedDto(dto, entity);
    }

    @Override
    protected ArmazemDetailsExtendedDto toExtendedDto(Armazem entity) {
        return armazemMapper.toExtendedDto(entity);
    }

    @Override
    protected ArmazemDetailsDto toDetailsDto(Armazem entity) {
        return armazemMapper.toDetailsDto(entity);
    }

    @Override
    protected Specification<Armazem> getSpecificationFromFiltro(ArmazemFiltroDto filtro) {
        return ArmazemSpecification.filter(filtro);
    }

    @Override
    protected void markedDeleted(Armazem entity) {
        entity.setIsDeleted(true);
    }

    // --- Linking and Unlinking Methods ---
    @Override
    public void linkToFuncionarios(Integer idArmazem, Set<Integer> idsFuncionarios) {
        Armazem armazem = getEntity(idArmazem);

        Set<Funcionario> funcionarios = idsFuncionarios.stream()
                .map(id -> funcionarioRepository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException("Funcionário não encontrado: " + id)))
                .collect(Collectors.toSet());
        armazem.getFuncionarios().addAll(funcionarios);

        armazemRepository.save(armazem);
    }

    @Override
    public void unlinkFromFuncionarios(Integer idArmazem, Set<Integer> idsFuncionarios) {
        Armazem armazem = getEntity(idArmazem);

        armazem.getFuncionarios().removeIf(funcionario -> idsFuncionarios.contains(funcionario.getId()));

        armazemRepository.save(armazem);
    }

    // --- Aux ---
    private Armazem getEntity(Integer id) {
        return armazemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Armazém não encontrado"));
    }
}