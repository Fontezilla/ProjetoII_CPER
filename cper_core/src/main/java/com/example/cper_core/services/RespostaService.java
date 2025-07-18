package com.example.cper_core.services;

import com.example.cper_core.dtos.resposta.*;
import com.example.cper_core.entities.Resposta;
import com.example.cper_core.mappers.RespostaMapper;
import com.example.cper_core.repositories.RespostaRepository;
import com.example.cper_core.services.interfaces.IRespostaService;
import com.example.cper_core.specifications.RespostaSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RespostaService extends AbstractXService<
        Resposta,
        RespostaDto,
        RespostaDetailsDto,
        RespostaDetailsExtendedDto,
        RespostaFiltroDto,
        Integer
        > implements IRespostaService {

    private final RespostaMapper respostaMapper;

    public RespostaService(
            RespostaRepository respostaRepository,
            RespostaMapper respostaMapper,
            jakarta.validation.Validator validator
    ) {
        super(respostaRepository, respostaRepository, validator);
        this.respostaMapper = respostaMapper;
    }

    @Override
    protected Resposta toEntity(RespostaDetailsExtendedDto dto) {
        return respostaMapper.toEntity(dto);
    }

    @Override
    protected void updateEntityFromDto(RespostaDetailsExtendedDto dto, Resposta entity) {
        respostaMapper.updateEntityFromExtendedDto(dto, entity);
    }

    @Override
    protected RespostaDetailsExtendedDto toExtendedDto(Resposta entity) {
        return respostaMapper.toExtendedDto(entity);
    }

    @Override
    protected RespostaDetailsDto toDetailsDto(Resposta entity) {
        return respostaMapper.toDetailsDto(entity);
    }

    @Override
    protected Specification<Resposta> getSpecificationFromFiltro(RespostaFiltroDto filtro) {
        return RespostaSpecification.filter(filtro);
    }

    @Override
    protected void markedDeleted(Resposta entity) {
        entity.setIsDeleted(true);
    }
}