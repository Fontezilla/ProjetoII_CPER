package com.example.cper_core.services;

import com.example.cper_core.dtos.anomalia.*;
import com.example.cper_core.entities.Anomalia;
import com.example.cper_core.entities.Nota;
import com.example.cper_core.enums.EstadoAnomalia;
import com.example.cper_core.mappers.AnomaliaMapper;
import com.example.cper_core.repositories.AnomaliaRepository;
import com.example.cper_core.repositories.NotaRepository;
import com.example.cper_core.services.interfaces.IAnomaliaService;
import jakarta.persistence.criteria.Predicate;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class AnomaliaService implements IAnomaliaService {

    private final AnomaliaRepository anomaliaRepository;
    private final NotaRepository notaRepository;
    private final AnomaliaMapper anomaliaMapper;

    @Autowired
    public AnomaliaService(AnomaliaRepository anomaliaRepository, NotaRepository notaRepository, AnomaliaMapper anomaliaMapper) {
        this.anomaliaRepository = anomaliaRepository;
        this.notaRepository = notaRepository;
        this.anomaliaMapper = anomaliaMapper;
    }

    // -------- CRUD --------

    @Override
    public Page<AnomaliaDetailsDto> listAll(Pageable pageable) {
        return anomaliaRepository.findAll(pageable)
                .map(anomaliaMapper::toDetailsDto);
    }

    @Override
    public Page<AnomaliaDetailsDto> listFiltered(Pageable pageable, AnomaliaFiltroDto filtro) {
        return anomaliaRepository.findAll(createSpecificationFromFiltro(filtro), pageable)
                .map(anomaliaMapper::toDetailsDto);
    }

    @Override
    public Optional<AnomaliaDetailsExtendedDto> getById(Integer id) {
        return anomaliaRepository.findByIdWithAllRelations(id)
                .map(anomaliaMapper::toExtendedDto);
    }

    @Override
    public AnomaliaDetailsExtendedDto create(AnomaliaDetailsExtendedDto dto) {
        normalizeDefaults(dto);
        Anomalia entity = anomaliaMapper.toEntity(dto);
        entity = anomaliaRepository.save(entity);
        return anomaliaMapper.toExtendedDto(entity);
    }

    @Override
    public AnomaliaDetailsExtendedDto update(AnomaliaDetailsExtendedDto dto) {
        if (dto.getId() == null) {
            throw new IllegalArgumentException("ID da Anomalia não pode ser nulo para atualização.");
        }

        Anomalia entity = anomaliaRepository.findByIdWithAllRelations(dto.getId())
                .orElseThrow(() -> new IllegalArgumentException("Anomalia não encontrada com ID: " + dto.getId()));

        normalizeDefaults(dto);
        anomaliaMapper.updateEntityFromExtendedDto(dto, entity);
        return anomaliaMapper.toExtendedDto(anomaliaRepository.save(entity));
    }

    @Override
    public void softDelete(Integer id) {
        Anomalia entity = anomaliaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Anomalia não encontrada com ID: " + id));
        entity.setEstado(EstadoAnomalia.APAGADA.getId());
        anomaliaRepository.save(entity);
    }

    // -------- Association Methods --------

    @Override
    public AnomaliaWithNotasDto linkNota(Integer idAnomalia, Integer idNota) {
        return alterarAssociacaoNota(idAnomalia, idNota, true);
    }

    @Override
    public AnomaliaWithNotasDto unlinkNota(Integer idAnomalia, Integer idNota) {
        return alterarAssociacaoNota(idAnomalia, idNota, false);
    }

    private AnomaliaWithNotasDto alterarAssociacaoNota(Integer idAnomalia, Integer idNota, boolean associar) {
        Anomalia anomalia = anomaliaRepository.findByIdWithAllRelations(idAnomalia)
                .orElseThrow(() -> new IllegalArgumentException("Anomalia não encontrada com ID: " + idAnomalia));
        Nota nota = notaRepository.findById(idNota)
                .orElseThrow(() -> new IllegalArgumentException("Nota não encontrada com ID: " + idNota));

        if (associar) {
            anomalia.getNotas().add(nota);
        } else {
            anomalia.getNotas().remove(nota);
        }

        return anomaliaMapper.toWithNotasDto(anomaliaRepository.save(anomalia));
    }

    // -------- Auxiliares --------

    private void normalizeDefaults(AnomaliaDetailsExtendedDto dto) {
        if (dto.getDescricao() != null && dto.getDescricao().isBlank()) dto.setDescricao(null);
        if (dto.getLocalizacao() != null && dto.getLocalizacao().isBlank()) dto.setLocalizacao(null);
        if (dto.getEstado() == null) dto.setEstado(EstadoAnomalia.DETECTADA.name());
        if (dto.getDataIdentificacao() == null) dto.setDataIdentificacao(LocalDate.now());
    }

    private Specification<Anomalia> createSpecificationFromFiltro(AnomaliaFiltroDto filtro) {
        return (root, query, cb) -> {
            Predicate predicate = cb.conjunction();

            if (filtro.getId() != null)
                predicate = cb.and(predicate, cb.equal(root.get("id"), filtro.getId()));

            if (filtro.getTipoAnomalia() != null)
                predicate = cb.and(predicate, cb.equal(root.get("tipoAnomalia"), filtro.getTipoAnomalia()));

            if (filtro.getDescricao() != null && !filtro.getDescricao().isBlank())
                predicate = cb.and(predicate, cb.like(cb.lower(root.get("descricao")), "%" + filtro.getDescricao().toLowerCase() + "%"));

            if (filtro.getLocalizacao() != null && !filtro.getLocalizacao().isBlank())
                predicate = cb.and(predicate, cb.like(cb.lower(root.get("localizacao")), "%" + filtro.getLocalizacao().toLowerCase() + "%"));

            if (filtro.getDataIdentificacaoInicio() != null)
                predicate = cb.and(predicate, cb.greaterThanOrEqualTo(root.get("dataIdentificacao"), filtro.getDataIdentificacaoInicio()));

            if (filtro.getDataIdentificacaoFim() != null)
                predicate = cb.and(predicate, cb.lessThanOrEqualTo(root.get("dataIdentificacao"), filtro.getDataIdentificacaoFim()));

            if (filtro.getGravidade() != null)
                predicate = cb.and(predicate, cb.equal(root.get("gravidade"), filtro.getGravidade()));

            if (filtro.getEstado() != null)
                predicate = cb.and(predicate, cb.equal(root.get("estado"), filtro.getEstado()));

            Set<Integer> idsCentro = filtro.getIdsCentroProducao();
            if (idsCentro != null && !idsCentro.isEmpty())
                predicate = cb.and(predicate, root.get("centroProducao").get("id").in(idsCentro));

            Set<Integer> idsFunc = filtro.getIdsFuncionario();
            if (idsFunc != null && !idsFunc.isEmpty())
                predicate = cb.and(predicate, root.get("funcionario").get("id").in(idsFunc));

            return predicate;
        };
    }
}
