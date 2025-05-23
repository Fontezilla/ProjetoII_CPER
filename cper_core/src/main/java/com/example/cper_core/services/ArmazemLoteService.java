package com.example.cper_core.services;

import com.example.cper_core.dtos.armazem_lote.*;
import com.example.cper_core.entities.Armazem;
import com.example.cper_core.entities.ArmazemLote;
import com.example.cper_core.entities.ArmazemLoteId;
import com.example.cper_core.entities.Lote;
import com.example.cper_core.mappers.ArmazemLoteMapper;
import com.example.cper_core.repositories.ArmazemLoteRepository;
import com.example.cper_core.repositories.ArmazemRepository;
import com.example.cper_core.repositories.LoteRepository;
import com.example.cper_core.services.interfaces.IArmazemLoteService;
import com.example.cper_core.specifications.ArmazemLoteSpecification;
import jakarta.validation.Validator;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ArmazemLoteService extends AbstractXService<
        ArmazemLote,
        ArmazemLoteDto,
        ArmazemLoteDetailsDto,
        ArmazemLoteDetailsExtendedDto,
        ArmazemLoteFiltroDto,
        ArmazemLoteWithRelationshipsDto,
        ArmazemLoteId
        > implements IArmazemLoteService {

    private final ArmazemLoteRepository armazemLoteRepository;
    private final ArmazemLoteMapper armazemLoteMapper;
    private final ArmazemRepository armazemRepository;
    private final LoteRepository loteRepository;

    public ArmazemLoteService(
            ArmazemLoteRepository armazemLoteRepository,
            ArmazemLoteMapper armazemLoteMapper,
            ArmazemRepository armazemRepository,
            LoteRepository loteRepository,
            Validator validator
    ) {
        super(armazemLoteRepository, armazemLoteRepository, validator);
        this.armazemLoteRepository = armazemLoteRepository;
        this.armazemLoteMapper = armazemLoteMapper;
        this.armazemRepository = armazemRepository;
        this.loteRepository = loteRepository;
    }

    @Override
    protected ArmazemLote toEntity(ArmazemLoteDetailsExtendedDto dto) {
        ArmazemLote entity = armazemLoteMapper.toEntity(dto);

        Armazem armazem = armazemRepository.findById(dto.getId().getIdArmazem())
                .orElseThrow(() -> new RuntimeException("Armazém não encontrado"));
        Lote lote = loteRepository.findById(dto.getId().getIdLote())
                .orElseThrow(() -> new RuntimeException("Lote não encontrado"));

        entity.setArmazem(armazem);
        entity.setLote(lote);

        return entity;
    }

    @Override
    protected void updateEntityFromDto(ArmazemLoteDetailsExtendedDto dto, ArmazemLote entity) {
        entity.setQuantidadeArmazenada(dto.getQuantidadeArmazenada());
        // armazem e lote não são atualizados pois fazem parte da chave primária
    }

    @Override
    protected ArmazemLoteDetailsExtendedDto toExtendedDto(ArmazemLote entity) {
        return armazemLoteMapper.toExtendedDto(entity);
    }

    @Override
    protected ArmazemLoteDetailsDto toDetailsDto(ArmazemLote entity) {
        return armazemLoteMapper.toDetailsDto(entity);
    }

    @Override
    protected Specification<ArmazemLote> getSpecificationFromFiltro(ArmazemLoteFiltroDto filtro) {
        return ArmazemLoteSpecification.filter(filtro);
    }

    @Override
    protected void marcarComoEliminado(ArmazemLote entity) {
        throw new UnsupportedOperationException("Operação de eliminação não suportada para ArmazemLote.");
    }
}