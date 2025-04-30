package com.example.cper_core.services;

import com.example.cper_core.dtos.armazem.*;
import com.example.cper_core.entities.Armazem;
import com.example.cper_core.entities.Funcionario;
import com.example.cper_core.entities.Stock;
import com.example.cper_core.enums.EstadoArmazem;
import com.example.cper_core.mappers.ArmazemMapper;
import com.example.cper_core.repositories.ArmazemRepository;
import com.example.cper_core.repositories.FuncionarioRepository;
import com.example.cper_core.repositories.StockRepository;
import com.example.cper_core.services.interfaces.IArmazemService;
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
public class ArmazemService implements IArmazemService {

    private final ArmazemRepository armazemRepository;
    private final StockRepository stockRepository;
    private final FuncionarioRepository funcionarioRepository;
    private final ArmazemMapper armazemMapper;

    @Autowired
    public ArmazemService(
            ArmazemRepository armazemRepository,
            StockRepository stockRepository,
            FuncionarioRepository funcionarioRepository,
            ArmazemMapper armazemMapper) {
        this.armazemRepository = armazemRepository;
        this.stockRepository = stockRepository;
        this.funcionarioRepository = funcionarioRepository;
        this.armazemMapper = armazemMapper;
    }

    // -------- CRUD --------

    @Override
    public Page<ArmazemDetailsDto> listAll(Pageable pageable) {
        return armazemRepository.findAll(pageable)
                .map(armazemMapper::toDetailsDto);
    }

    public static Specification<Armazem> filtroArmazem(ArmazemFiltroDto filtro) {
        return (root, query, cb) -> {
            Predicate predicate = cb.conjunction();

            if (filtro.getId() != null)
                predicate = cb.and(predicate, cb.equal(root.get("id"), filtro.getId()));
            if (filtro.getNome() != null && !filtro.getNome().isBlank())
                predicate = cb.and(predicate, cb.like(cb.lower(root.get("nome")), "%" + filtro.getNome().toLowerCase() + "%"));
            if (filtro.getDataCriacaoInicio() != null)
                predicate = cb.and(predicate, cb.greaterThanOrEqualTo(root.get("dataCriacao"), filtro.getDataCriacaoInicio()));
            if (filtro.getDataCriacaoFim() != null)
                predicate = cb.and(predicate, cb.lessThanOrEqualTo(root.get("dataCriacao"), filtro.getDataCriacaoFim()));
            if (filtro.getDataUpdateInicio() != null)
                predicate = cb.and(predicate, cb.greaterThanOrEqualTo(root.get("dataUpdate"), filtro.getDataUpdateInicio()));
            if (filtro.getDataUpdateFim() != null)
                predicate = cb.and(predicate, cb.lessThanOrEqualTo(root.get("dataUpdate"), filtro.getDataUpdateFim()));
            if (filtro.getCapacidadeTotalMin() != null)
                predicate = cb.and(predicate, cb.greaterThanOrEqualTo(root.get("capacidadeTotal"), filtro.getCapacidadeTotalMin()));
            if (filtro.getCapacidadeTotalMax() != null)
                predicate = cb.and(predicate, cb.lessThanOrEqualTo(root.get("capacidadeTotal"), filtro.getCapacidadeTotalMax()));
            if (filtro.getCapacidadeOcupadaMin() != null)
                predicate = cb.and(predicate, cb.greaterThanOrEqualTo(root.get("capacidadeOcupada"), filtro.getCapacidadeOcupadaMin()));
            if (filtro.getCapacidadeOcupadaMax() != null)
                predicate = cb.and(predicate, cb.lessThanOrEqualTo(root.get("capacidadeOcupada"), filtro.getCapacidadeOcupadaMax()));
            if (filtro.getNPorta() != null)
                predicate = cb.and(predicate, cb.equal(root.get("nPorta"), filtro.getNPorta()));
            if (filtro.getEstado() != null)
                predicate = cb.and(predicate, cb.equal(root.get("estado"), filtro.getEstado()));
            if (filtro.getIdEndereco() != null)
                predicate = cb.and(predicate, cb.equal(root.get("endereco").get("id"), filtro.getIdEndereco()));
            if (filtro.getIdDepartamento() != null)
                predicate = cb.and(predicate, cb.equal(root.get("departamento").get("id"), filtro.getIdDepartamento()));
            if (filtro.getIdResponsavel() != null)
                predicate = cb.and(predicate, cb.equal(root.get("responsavel").get("id"), filtro.getIdResponsavel()));

            return predicate;
        };
    }

    @Override
    public Page<ArmazemDetailsDto> listFiltered(Pageable pageable, ArmazemFiltroDto filtro) {
        return armazemRepository.findAll(filtroArmazem(filtro), pageable)
                .map(armazemMapper::toDetailsDto);
    }

    @Override
    public Optional<ArmazemDetailsExtendedDto> getById(Integer id) {
        return armazemRepository.findByIdWithAllRelations(id)
                .map(armazemMapper::toDetailsExtendedDto);
    }

    @Override
    public ArmazemDetailsExtendedDto create(ArmazemDetailsExtendedDto dto) {
        Armazem armazem = armazemMapper.toEntity(dto);
        armazem = armazemRepository.save(armazem);
        return armazemMapper.toDetailsExtendedDto(armazem);
    }

    @Override
    public ArmazemDetailsExtendedDto update(ArmazemDetailsExtendedDto dto) {
        if (dto.getId() == null)
            throw new IllegalArgumentException("ID do armazém não pode ser nulo para atualização.");

        Armazem entity = armazemRepository.findByIdWithAllRelations(dto.getId())
                .orElseThrow(() -> new IllegalArgumentException("Armazém não encontrado com ID: " + dto.getId()));

        armazemMapper.updateEntityFromExtendedDto(dto, entity);
        return armazemMapper.toDetailsExtendedDto(armazemRepository.save(entity));
    }

    @Override
    public void softDelete(Integer id) {
        Armazem armazem = armazemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Armazém não encontrado com ID: " + id));
        armazem.setEstado(EstadoArmazem.APAGADO.getId());
        armazemRepository.save(armazem);
    }

    // -------- Associações --------

    @Override
    public ArmazemWithStockDto linkNota(Integer idArmazem, Integer idStock) {
        Armazem armazem = armazemRepository.findByIdWithAllRelations(idArmazem)
                .orElseThrow(() -> new IllegalArgumentException("Armazém não encontrado com ID: " + idArmazem));
        Stock stock = stockRepository.findById(idStock)
                .orElseThrow(() -> new IllegalArgumentException("Stock não encontrado com ID: " + idStock));
        armazem.getStocks().add(stock);
        armazemRepository.save(armazem);
        return armazemMapper.toWithStockDto(armazem);
    }

    @Override
    public ArmazemWithStockDto unlinkNota(Integer idArmazem, Integer idStock) {
        Armazem armazem = armazemRepository.findByIdWithAllRelations(idArmazem)
                .orElseThrow(() -> new IllegalArgumentException("Armazém não encontrado com ID: " + idArmazem));
        Stock stock = stockRepository.findById(idStock)
                .orElseThrow(() -> new IllegalArgumentException("Stock não encontrado com ID: " + idStock));
        armazem.getStocks().remove(stock);
        armazemRepository.save(armazem);
        return armazemMapper.toWithStockDto(armazem);
    }

    @Override
    public ArmazemWithFuncionarioDto linkFuncionario(Integer idArmazem, Integer idFuncionario) {
        Armazem armazem = armazemRepository.findByIdWithAllRelations(idArmazem)
                .orElseThrow(() -> new IllegalArgumentException("Armazém não encontrado com ID: " + idArmazem));
        Funcionario funcionario = funcionarioRepository.findById(idFuncionario)
                .orElseThrow(() -> new IllegalArgumentException("Funcionário não encontrado com ID: " + idFuncionario));
        armazem.getFuncionarios().add(funcionario);
        armazemRepository.save(armazem);
        return armazemMapper.toWithFuncionarioDto(armazem);
    }

    @Override
    public ArmazemWithFuncionarioDto unlinkFuncionario(Integer idArmazem, Integer idFuncionario) {
        Armazem armazem = armazemRepository.findByIdWithAllRelations(idArmazem)
                .orElseThrow(() -> new IllegalArgumentException("Armazém não encontrado com ID: " + idArmazem));
        Funcionario funcionario = funcionarioRepository.findById(idFuncionario)
                .orElseThrow(() -> new IllegalArgumentException("Funcionário não encontrado com ID: " + idFuncionario));
        armazem.getFuncionarios().remove(funcionario);
        armazemRepository.save(armazem);
        return armazemMapper.toWithFuncionarioDto(armazem);
    }
}
