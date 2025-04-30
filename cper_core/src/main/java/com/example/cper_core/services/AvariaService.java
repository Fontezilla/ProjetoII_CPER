package com.example.cper_core.services;

import com.example.cper_core.dtos.avaria.*;
import com.example.cper_core.entities.*;
import com.example.cper_core.enums.EstadoAvaria;
import com.example.cper_core.mappers.AvariaMapper;
import com.example.cper_core.repositories.*;
import com.example.cper_core.services.interfaces.IAvariaService;
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
public class AvariaService implements IAvariaService {

    private final AvariaRepository avariaRepository;
    private final AvariaMapper avariaMapper;
    private final NotaRepository notaRepository;
    private final EquipaRepository equipaRepository;
    private final PedidoMaterialRepository pedidoMaterialRepository;
    private final CentroProducaoRepository centroProducaoRepository;
    private final InspecaoRepository inspecaoRepository;

    @Autowired
    public AvariaService(
            AvariaRepository avariaRepository,
            AvariaMapper avariaMapper,
            NotaRepository notaRepository,
            EquipaRepository equipaRepository,
            PedidoMaterialRepository pedidoMaterialRepository,
            CentroProducaoRepository centroProducaoRepository,
            InspecaoRepository inspecaoRepository
    ) {
        this.avariaRepository = avariaRepository;
        this.avariaMapper = avariaMapper;
        this.notaRepository = notaRepository;
        this.equipaRepository = equipaRepository;
        this.pedidoMaterialRepository = pedidoMaterialRepository;
        this.centroProducaoRepository = centroProducaoRepository;
        this.inspecaoRepository = inspecaoRepository;
    }

    // -------- CRUD --------

    @Override
    public Page<AvariaDetailsDto> listAll(Pageable pageable) {
        return avariaRepository.findAll(pageable).map(avariaMapper::toDetailsDto);
    }

    @Override
    public Page<AvariaDetailsDto> listFiltered(Pageable pageable, AvariaFiltroDto filtro) {
        Specification<Avaria> spec = filtroAvaria(filtro);
        return avariaRepository.findAll(spec, pageable).map(avariaMapper::toDetailsDto);
    }

    @Override
    public Optional<AvariaDetailsExtendedDto> getById(Integer id) {
        return avariaRepository.findByIdWithAllRelations(id).map(avariaMapper::toDetailsExtendedDto);
    }

    @Override
    public AvariaDetailsExtendedDto create(AvariaDetailsExtendedDto dto) {
        clearEmptyEntries(dto);
        Avaria avaria = avariaMapper.toEntity(dto);
        return avariaMapper.toDetailsExtendedDto(avariaRepository.save(avaria));
    }

    @Override
    public AvariaDetailsExtendedDto update(AvariaDetailsExtendedDto dto) {
        if (dto.getId() == null)
            throw new IllegalArgumentException("ID da Avaria não pode ser nulo para atualização.");

        Avaria entity = findAvaria(dto.getId());
        clearEmptyEntries(dto);
        Avaria atualizada = avariaMapper.toEntity(dto);
        avariaMapper.updateEntityFromExtendedDto(dto, entity);
        return avariaMapper.toDetailsExtendedDto(avariaRepository.save(entity));
    }

    @Override
    public void softDelete(Integer id) {
        Avaria avaria = findAvaria(id);
        avaria.setEstado(EstadoAvaria.APAGADA.getId());
        avariaRepository.save(avaria);
    }

    // -------- Association Methods --------

    @Override
    public AvariaWithNotasDto linkNota(Integer idAvaria, Integer idNota) {
        Avaria avaria = findAvaria(idAvaria);
        Nota nota = notaRepository.findById(idNota)
                .orElseThrow(() -> new IllegalArgumentException("Nota não encontrada."));
        nota.setAvaria(avaria);
        notaRepository.save(nota);
        return avariaMapper.toWithNotasDto(findAvaria(idAvaria));
    }

    @Override
    public AvariaWithNotasDto unlinkNota(Integer idAvaria, Integer idNota) {
        Nota nota = notaRepository.findById(idNota)
                .orElseThrow(() -> new IllegalArgumentException("Nota não encontrada."));
        nota.setAvaria(null);
        notaRepository.save(nota);
        return avariaMapper.toWithNotasDto(findAvaria(idAvaria));
    }

    @Override
    public AvariaWithEquipaDto linkEquipa(Integer idAvaria, Integer idEquipa) {
        Avaria avaria = findAvaria(idAvaria);
        Equipa equipa = equipaRepository.findById(idEquipa)
                .orElseThrow(() -> new IllegalArgumentException("Equipa não encontrada."));
        avaria.getEquipas().add(equipa);
        return avariaMapper.toWithEquipaDto(avariaRepository.save(avaria));
    }

    @Override
    public AvariaWithEquipaDto unlinkEquipa(Integer idAvaria, Integer idEquipa) {
        Avaria avaria = findAvaria(idAvaria);
        Equipa equipa = equipaRepository.findById(idEquipa)
                .orElseThrow(() -> new IllegalArgumentException("Equipa não encontrada."));
        avaria.getEquipas().remove(equipa);
        return avariaMapper.toWithEquipaDto(avariaRepository.save(avaria));
    }

    @Override
    public AvariaWithPedidoDto linkPedido(Integer idAvaria, Integer idPedido) {
        Avaria avaria = findAvaria(idAvaria);
        PedidoMaterial pedido = pedidoMaterialRepository.findById(idPedido)
                .orElseThrow(() -> new IllegalArgumentException("PedidoMaterial não encontrado."));
        avaria.getPedidoMateriais().add(pedido);
        return avariaMapper.toWithPedidoDto(avariaRepository.save(avaria));
    }

    @Override
    public AvariaWithPedidoDto unlinkPedido(Integer idAvaria, Integer idPedido) {
        Avaria avaria = findAvaria(idAvaria);
        PedidoMaterial pedido = pedidoMaterialRepository.findById(idPedido)
                .orElseThrow(() -> new IllegalArgumentException("PedidoMaterial não encontrado."));
        avaria.getPedidoMateriais().remove(pedido);
        return avariaMapper.toWithPedidoDto(avariaRepository.save(avaria));
    }

    // -------- Helper methods --------

    private Avaria findAvaria(Integer id) {
        return avariaRepository.findByIdWithAllRelations(id)
                .orElseThrow(() -> new IllegalArgumentException("Avaria não encontrada com ID: " + id));
    }

    private void clearEmptyEntries(AvariaDetailsExtendedDto dto) {
        if (dto.getCentroProducao() != null && dto.getCentroProducao().getId() == null) {
            dto.setCentroProducao(null);
        }
        if (dto.getInspecao() != null && dto.getInspecao().getId() == null) {
            dto.setInspecao(null);
        }
        if (dto.getDescricao() != null && dto.getDescricao().isBlank()) {
            dto.setDescricao(null);
        }
        if (dto.getImpactoProducao() != null && dto.getImpactoProducao().isBlank()) {
            dto.setImpactoProducao(null);
        }
    }

    private Specification<Avaria> filtroAvaria(AvariaFiltroDto filtro) {
        return (root, query, cb) -> {
            Predicate predicate = cb.conjunction();

            if (filtro.getId() != null)
                predicate = cb.and(predicate, cb.equal(root.get("id"), filtro.getId()));
            if (filtro.getDescricao() != null)
                predicate = cb.and(predicate, cb.like(cb.lower(root.get("descricao")), "%" + filtro.getDescricao().toLowerCase() + "%"));
            if (filtro.getGravidade() != null)
                predicate = cb.and(predicate, cb.equal(root.get("gravidade"), filtro.getGravidade()));
            if (filtro.getEstado() != null)
                predicate = cb.and(predicate, cb.equal(root.get("estado"), filtro.getEstado()));
            if (filtro.getImpactoProducao() != null)
                predicate = cb.and(predicate, cb.like(cb.lower(root.get("impactoProducao")), "%" + filtro.getImpactoProducao().toLowerCase() + "%"));
            if (filtro.getDataInicioInicio() != null)
                predicate = cb.and(predicate, cb.greaterThanOrEqualTo(root.get("dataInicio"), filtro.getDataInicioInicio()));
            if (filtro.getDataInicioFim() != null)
                predicate = cb.and(predicate, cb.lessThanOrEqualTo(root.get("dataInicio"), filtro.getDataInicioFim()));
            if (filtro.getDataResolucaoInicio() != null)
                predicate = cb.and(predicate, cb.greaterThanOrEqualTo(root.get("dataResolucao"), filtro.getDataResolucaoInicio()));
            if (filtro.getDataResolucaoFim() != null)
                predicate = cb.and(predicate, cb.lessThanOrEqualTo(root.get("dataResolucao"), filtro.getDataResolucaoFim()));
            if (filtro.getIdCentroProducao() != null)
                predicate = cb.and(predicate, cb.equal(root.get("centroProducao").get("id"), filtro.getIdCentroProducao()));
            if (filtro.getIdInspecao() != null)
                predicate = cb.and(predicate, cb.equal(root.get("inspecao").get("id"), filtro.getIdInspecao()));

            return predicate;
        };
    }
}