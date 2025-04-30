package com.example.cper_core.services;

import com.example.cper_core.dtos.comentario.*;
import com.example.cper_core.entities.Comentario;
import com.example.cper_core.entities.Funcionario;
import com.example.cper_core.entities.SolicitacaoEnergetica;
import com.example.cper_core.mappers.ComentarioMapper;
import com.example.cper_core.repositories.ComentarioRepository;
import com.example.cper_core.repositories.FuncionarioRepository;
import com.example.cper_core.repositories.SolicitacaoEnergeticaRepository;
import com.example.cper_core.services.interfaces.IComentarioService;
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
public class ComentarioService implements IComentarioService {

    private final ComentarioRepository comentarioRepository;
    private final ComentarioMapper comentarioMapper;
    private final FuncionarioRepository funcionarioRepository;
    private final SolicitacaoEnergeticaRepository solicitacaoEnergeticaRepository;

    @Autowired
    public ComentarioService(
            ComentarioRepository comentarioRepository,
            ComentarioMapper comentarioMapper,
            FuncionarioRepository funcionarioRepository,
            SolicitacaoEnergeticaRepository solicitacaoEnergeticaRepository
    ) {
        this.comentarioRepository = comentarioRepository;
        this.comentarioMapper = comentarioMapper;
        this.funcionarioRepository = funcionarioRepository;
        this.solicitacaoEnergeticaRepository = solicitacaoEnergeticaRepository;
    }

    @Override
    public Page<ComentarioDetailsDto> listAll(Pageable pageable) {
        return comentarioRepository.findAll(pageable).map(comentarioMapper::toDetailsDto);
    }

    @Override
    public Page<ComentarioDetailsDto> listFiltered(Pageable pageable, ComentarioFiltroDto filtro) {
        Specification<Comentario> spec = filtroComentario(filtro);
        return comentarioRepository.findAll(spec, pageable).map(comentarioMapper::toDetailsDto);
    }

    @Override
    public Optional<ComentarioDetailsDto> getById(Integer id) {
        return comentarioRepository.findByIdWithAllRelations(id).map(comentarioMapper::toDetailsDto);
    }

    @Override
    public ComentarioDetailsDto create(ComentarioDetailsDto dto) {
        Comentario entity = comentarioMapper.toEntity(dto);
        if (dto.getFuncionario() != null)
            entity.setFuncionario(funcionarioRepository.findById(dto.getFuncionario().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Funcionário não encontrado.")));

        if (dto.getSolicitacaoEnergetica() != null)
            entity.setSolicitacaoEnergetica(solicitacaoEnergeticaRepository.findById(dto.getSolicitacaoEnergetica().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Solicitação Energética não encontrada.")));

        return comentarioMapper.toDetailsDto(comentarioRepository.save(entity));
    }

    @Override
    public ComentarioDetailsDto update(Integer id, ComentarioDetailsDto dto) {
        if (dto.getId() == null || !dto.getId().equals(id))
            throw new IllegalArgumentException("ID do corpo e da URL não coincidem.");

        Comentario entity = comentarioRepository.findByIdWithAllRelations(id)
                .orElseThrow(() -> new IllegalArgumentException("Comentário não encontrado."));

        comentarioMapper.updateEntityFromExtendedDto(dto, entity);

        if (dto.getFuncionario() != null)
            entity.setFuncionario(funcionarioRepository.findById(dto.getFuncionario().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Funcionário não encontrado.")));

        if (dto.getSolicitacaoEnergetica() != null)
            entity.setSolicitacaoEnergetica(solicitacaoEnergeticaRepository.findById(dto.getSolicitacaoEnergetica().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Solicitação Energética não encontrada.")));

        return comentarioMapper.toDetailsDto(comentarioRepository.save(entity));
    }

    @Override
    public void softDelete(Integer id) {
        Comentario comentario = comentarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Comentário não encontrado."));
        comentarioRepository.delete(comentario);
    }

    private Specification<Comentario> filtroComentario(ComentarioFiltroDto filtro) {
        return (root, query, cb) -> {
            Predicate predicate = cb.conjunction();

            if (filtro.getId() != null)
                predicate = cb.and(predicate, cb.equal(root.get("id"), filtro.getId()));
            if (filtro.getDescricao() != null)
                predicate = cb.and(predicate, cb.like(cb.lower(root.get("descricao")), "%" + filtro.getDescricao().toLowerCase() + "%"));
            if (filtro.getDataCriacaoInicio() != null)
                predicate = cb.and(predicate, cb.greaterThanOrEqualTo(root.get("dataCriacao"), filtro.getDataCriacaoInicio()));
            if (filtro.getDataCriacaoFim() != null)
                predicate = cb.and(predicate, cb.lessThanOrEqualTo(root.get("dataCriacao"), filtro.getDataCriacaoFim()));
            if (filtro.getIdFuncionario() != null)
                predicate = cb.and(predicate, cb.equal(root.get("funcionario").get("id"), filtro.getIdFuncionario()));
            if (filtro.getIdSolicitacaoEnergetica() != null)
                predicate = cb.and(predicate, cb.equal(root.get("solicitacaoEnergetica").get("id"), filtro.getIdSolicitacaoEnergetica()));

            return predicate;
        };
    }
}