package com.example.cper_core.services;

import com.example.cper_core.dtos.solicitacao_energetica.*;
import com.example.cper_core.entities.Funcionario;
import com.example.cper_core.entities.SolicitacaoEnergetica;
import com.example.cper_core.mappers.SolicitacaoEnergeticaMapper;
import com.example.cper_core.repositories.FuncionarioRepository;
import com.example.cper_core.repositories.SolicitacaoEnergeticaRepository;
import com.example.cper_core.services.interfaces.ISolicitacaoEnergeticaService;
import com.example.cper_core.specifications.SolicitacaoEnergeticaSpecification;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class SolicitacaoEnergeticaService extends AbstractXService<
        SolicitacaoEnergetica,
        SolicitacaoEnergeticaDto,
        SolicitacaoEnergeticaDetailsDto,
        SolicitacaoEnergeticaDetailsExtendedDto,
        SolicitacaoEnergeticaFiltroDto,
        Integer
        > implements ISolicitacaoEnergeticaService {

    private final SolicitacaoEnergeticaRepository solicitacaoEnergeticaRepository;
    private final SolicitacaoEnergeticaMapper solicitacaoEnergeticaMapper;
    private final FuncionarioRepository funcionarioRepository;

    public SolicitacaoEnergeticaService(
            SolicitacaoEnergeticaRepository solicitacaoEnergeticaRepository,
            SolicitacaoEnergeticaMapper solicitacaoEnergeticaMapper,
            jakarta.validation.Validator validator, FuncionarioRepository funcionarioRepository
    ) {
        super(solicitacaoEnergeticaRepository, solicitacaoEnergeticaRepository, validator);
        this.solicitacaoEnergeticaRepository = solicitacaoEnergeticaRepository;
        this.solicitacaoEnergeticaMapper = solicitacaoEnergeticaMapper;
        this.funcionarioRepository = funcionarioRepository;
    }

    @Override
    protected SolicitacaoEnergetica toEntity(SolicitacaoEnergeticaDetailsExtendedDto dto) {
        return solicitacaoEnergeticaMapper.toEntity(dto);
    }

    @Override
    protected void updateEntityFromDto(SolicitacaoEnergeticaDetailsExtendedDto dto, SolicitacaoEnergetica entity) {
        solicitacaoEnergeticaMapper.updateEntityFromExtendedDto(dto, entity);
    }

    @Override
    protected SolicitacaoEnergeticaDetailsExtendedDto toExtendedDto(SolicitacaoEnergetica entity) {
        return solicitacaoEnergeticaMapper.toExtendedDto(entity);
    }

    @Override
    protected SolicitacaoEnergeticaDetailsDto toDetailsDto(SolicitacaoEnergetica entity) {
        return solicitacaoEnergeticaMapper.toDetailsDto(entity);
    }

    @Override
    protected Specification<SolicitacaoEnergetica> getSpecificationFromFiltro(SolicitacaoEnergeticaFiltroDto filtro) {
        return SolicitacaoEnergeticaSpecification.filter(filtro);
    }

    @Override
    protected void markedDeleted(SolicitacaoEnergetica entity) {
        entity.setIsDeleted(true);
    }

    // --- Linking and Unlinking Methods ---

    @Override
    public void linkToFuncionarios(Integer idSolicitacaoEnergetica, Set<Integer> idsFuncionarios) {
        SolicitacaoEnergetica solicitacaoEnergetica = getEntity(idSolicitacaoEnergetica);

        Set<Funcionario> funcionarios = idsFuncionarios.stream()
                .map(id -> funcionarioRepository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException("Funcionário não encontrado: " + id))) // Exceção personalizada
                .collect(Collectors.toSet());

        solicitacaoEnergetica.getFuncionarios().addAll(funcionarios);

        solicitacaoEnergeticaRepository.save(solicitacaoEnergetica);
    }

    @Override
    public void unlinkFromFuncionarios(Integer idSolicitacaoEnergetica, Set<Integer> idsFuncionarios) {
        SolicitacaoEnergetica solicitacaoEnergetica = getEntity(idSolicitacaoEnergetica);

        solicitacaoEnergetica.getFuncionarios().removeIf(funcionario -> idsFuncionarios.contains(funcionario.getId()));

        solicitacaoEnergeticaRepository.save(solicitacaoEnergetica);
    }


    // --- Aux ---
    private SolicitacaoEnergetica getEntity(Integer id) {
        return solicitacaoEnergeticaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Solicitação Energética não encontrada"));
    }
}
