package com.example.cper_core.services;

import com.example.cper_core.dtos.departamento.*;
import com.example.cper_core.entities.*;
import com.example.cper_core.enums.Setor;
import com.example.cper_core.mappers.DepartamentoMapper;
import com.example.cper_core.repositories.*;
import com.example.cper_core.services.interfaces.IDepartamentoService;
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
public class DepartamentoService implements IDepartamentoService {

    private final DepartamentoRepository departamentoRepository;
    private final FuncionarioRepository funcionarioRepository;
    private final ArmazemRepository armazemRepository;
    private final CentroProducaoRepository centroProducaoRepository;
    private final EquipaRepository equipaRepository;
    private final DepartamentoMapper departamentoMapper;

    @Autowired
    public DepartamentoService(
            DepartamentoRepository departamentoRepository,
            FuncionarioRepository funcionarioRepository,
            ArmazemRepository armazemRepository,
            CentroProducaoRepository centroProducaoRepository,
            EquipaRepository equipaRepository,
            DepartamentoMapper departamentoMapper
    ) {
        this.departamentoRepository = departamentoRepository;
        this.funcionarioRepository = funcionarioRepository;
        this.armazemRepository = armazemRepository;
        this.centroProducaoRepository = centroProducaoRepository;
        this.equipaRepository = equipaRepository;
        this.departamentoMapper = departamentoMapper;
    }

    @Override
    public Page<DepartamentoDetailsDto> listAll(Pageable pageable) {
        return departamentoRepository.findAll(pageable).map(departamentoMapper::toDetailsDto);
    }

    @Override
    public Page<DepartamentoDetailsDto> listFiltered(Pageable pageable, DepartamentoFiltroDto filtro) {
        return departamentoRepository.findAll(filtroDepartamento(filtro), pageable).map(departamentoMapper::toDetailsDto);
    }

    @Override
    public Optional<DepartamentoDetailsExtendedDto> getById(Integer id) {
        return departamentoRepository.findByIdWithAllRelations(id).map(departamentoMapper::toDetailsExtendedDto);
    }

    @Override
    public DepartamentoDetailsExtendedDto create(DepartamentoDetailsExtendedDto dto) {
        clearEmptyEntries(dto);
        Departamento entity = departamentoMapper.toEntity(dto);
        return departamentoMapper.toDetailsExtendedDto(departamentoRepository.save(entity));
    }

    @Override
    public DepartamentoDetailsExtendedDto update(Integer id, DepartamentoDetailsExtendedDto dto) {
        if (dto.getId() == null || !dto.getId().equals(id))
            throw new IllegalArgumentException("ID do corpo e da URL não coincidem.");

        Departamento entity = departamentoRepository.findByIdWithAllRelations(id)
                .orElseThrow(() -> new IllegalArgumentException("Departamento não encontrado."));

        clearEmptyEntries(dto);
        departamentoMapper.updateEntityFromExtendedDto(dto, entity);
        return departamentoMapper.toDetailsExtendedDto(departamentoRepository.save(entity));
    }

    @Override
    public void softDelete(Integer id) {
        Departamento entity = departamentoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Departamento não encontrado."));
        entity.setNome("REMOVIDO");
        departamentoRepository.save(entity);
    }

    // -------- Association methods --------

    @Override
    public DepartamentoWithResponsavelDto linkResponsavel(Integer idDepartamento, Integer idResponsavel) {
        Departamento dep = getDepartamento(idDepartamento);
        Funcionario func = getFuncionario(idResponsavel);
        dep.getResponsaveis().add(func);
        return departamentoMapper.toWithResponsavelDto(departamentoRepository.save(dep));
    }

    @Override
    public DepartamentoWithResponsavelDto unlinkResponsavel(Integer idDepartamento, Integer idResponsavel) {
        Departamento dep = getDepartamento(idDepartamento);
        Funcionario func = getFuncionario(idResponsavel);
        dep.getResponsaveis().remove(func);
        return departamentoMapper.toWithResponsavelDto(departamentoRepository.save(dep));
    }

    @Override
    public DepartamentoWithEquipaDto linkEquipa(Integer idDepartamento, Integer idEquipa) {
        Departamento dep = getDepartamento(idDepartamento);
        Equipa equipa = equipaRepository.findById(idEquipa)
                .orElseThrow(() -> new IllegalArgumentException("Equipa não encontrada."));
        equipa.setDepartamento(dep);
        equipaRepository.save(equipa);
        return departamentoMapper.toWithEquipaDto(dep);
    }

    @Override
    public DepartamentoWithEquipaDto unlinkEquipa(Integer idDepartamento, Integer idEquipa) {
        Equipa equipa = equipaRepository.findById(idEquipa)
                .orElseThrow(() -> new IllegalArgumentException("Equipa não encontrada."));
        equipa.setDepartamento(null);
        equipaRepository.save(equipa);
        return departamentoMapper.toWithEquipaDto(getDepartamento(idDepartamento));
    }

    @Override
    public DepartamentoWithCentroDto linkCentro(Integer idDepartamento, Integer idCentro) {
        Departamento dep = getDepartamento(idDepartamento);
        CentroProducao centro = centroProducaoRepository.findById(idCentro)
                .orElseThrow(() -> new IllegalArgumentException("Centro de produção não encontrado."));
        centro.setDepartamento(dep);
        centroProducaoRepository.save(centro);
        return departamentoMapper.toWithCentroDto(dep);
    }

    @Override
    public DepartamentoWithCentroDto unlinkCentro(Integer idDepartamento, Integer idCentro) {
        CentroProducao centro = centroProducaoRepository.findById(idCentro)
                .orElseThrow(() -> new IllegalArgumentException("Centro de produção não encontrado."));
        centro.setDepartamento(null);
        centroProducaoRepository.save(centro);
        return departamentoMapper.toWithCentroDto(getDepartamento(idDepartamento));
    }

    @Override
    public DepartamentoWithArmazemDto linkArmazem(Integer idDepartamento, Integer idArmazem) {
        Departamento dep = getDepartamento(idDepartamento);
        Armazem armazem = armazemRepository.findById(idArmazem)
                .orElseThrow(() -> new IllegalArgumentException("Armazém não encontrado."));
        armazem.setDepartamento(dep);
        armazemRepository.save(armazem);
        return departamentoMapper.toWithArmazemDto(dep);
    }

    @Override
    public DepartamentoWithArmazemDto unlinkArmazem(Integer idDepartamento, Integer idArmazem) {
        Armazem armazem = armazemRepository.findById(idArmazem)
                .orElseThrow(() -> new IllegalArgumentException("Armazém não encontrado."));
        armazem.setDepartamento(null);
        armazemRepository.save(armazem);
        return departamentoMapper.toWithArmazemDto(getDepartamento(idDepartamento));
    }

    @Override
    public DepartamentoWithFuncionarioDto linkFuncionario(Integer idDepartamento, Integer idFuncionario) {
        Departamento dep = getDepartamento(idDepartamento);
        Funcionario func = getFuncionario(idFuncionario);
        func.setDepartamento(dep);
        funcionarioRepository.save(func);
        return departamentoMapper.toWithFuncionarioDto(dep);
    }

    @Override
    public DepartamentoWithFuncionarioDto unlinkFuncionario(Integer idDepartamento, Integer idFuncionario) {
        Funcionario func = getFuncionario(idFuncionario);
        func.setDepartamento(null);
        funcionarioRepository.save(func);
        return departamentoMapper.toWithFuncionarioDto(getDepartamento(idDepartamento));
    }

    // -------- Helpers --------

    private Departamento getDepartamento(Integer id) {
        return departamentoRepository.findByIdWithAllRelations(id)
                .orElseThrow(() -> new IllegalArgumentException("Departamento não encontrado."));
    }

    private Funcionario getFuncionario(Integer id) {
        return funcionarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Funcionário não encontrado."));
    }

    private void clearEmptyEntries(DepartamentoDetailsExtendedDto dto) {
        if (dto.getNome() != null && dto.getNome().isBlank()) dto.setNome(null);
        if (dto.getDescricao() != null && dto.getDescricao().isBlank()) dto.setDescricao(null);
        if (dto.getSetor() == null) dto.setSetor(Setor.TEMPORARIO.name());
        if (dto.getNumFuncionarios() == null) dto.setNumFuncionarios(0);
        if (dto.getOrcamento() == null) dto.setOrcamento(java.math.BigDecimal.ZERO);
    }

    private Specification<Departamento> filtroDepartamento(DepartamentoFiltroDto filtro) {
        return (root, query, cb) -> {
            Predicate predicate = cb.conjunction();
            if (filtro.getId() != null) predicate = cb.and(predicate, cb.equal(root.get("id"), filtro.getId()));
            if (filtro.getNome() != null) predicate = cb.and(predicate, cb.like(cb.lower(root.get("nome")), "%%" + filtro.getNome().toLowerCase() + "%%"));
            if (filtro.getDescricao() != null) predicate = cb.and(predicate, cb.like(cb.lower(root.get("descricao")), "%%" + filtro.getDescricao().toLowerCase() + "%%"));
            if (filtro.getSetor() != null) predicate = cb.and(predicate, cb.equal(root.get("setor"), filtro.getSetor()));
            if (filtro.getDataCriacaoInicio() != null) predicate = cb.and(predicate, cb.greaterThanOrEqualTo(root.get("dataCriacao"), filtro.getDataCriacaoInicio()));
            if (filtro.getDataCriacaoFim() != null) predicate = cb.and(predicate, cb.lessThanOrEqualTo(root.get("dataCriacao"), filtro.getDataCriacaoFim()));
            if (filtro.getNumFuncionariosMin() != null) predicate = cb.and(predicate, cb.greaterThanOrEqualTo(root.get("numFuncionarios"), filtro.getNumFuncionariosMin()));
            if (filtro.getNumFuncionariosMax() != null) predicate = cb.and(predicate, cb.lessThanOrEqualTo(root.get("numFuncionarios"), filtro.getNumFuncionariosMax()));
            if (filtro.getOrcamentoMin() != null) predicate = cb.and(predicate, cb.greaterThanOrEqualTo(root.get("orcamento"), filtro.getOrcamentoMin()));
            if (filtro.getOrcamentoMax() != null) predicate = cb.and(predicate, cb.lessThanOrEqualTo(root.get("orcamento"), filtro.getOrcamentoMax()));
            return predicate;
        };
    }
}
