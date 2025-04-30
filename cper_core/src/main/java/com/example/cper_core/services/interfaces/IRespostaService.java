package com.example.cper_core.services.interfaces;

import com.example.cper_core.dtos.resposta.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IRespostaService {

    // Generic CRUD operations

    Page<RespostaDetailsDto> listAll(Pageable pageable);

    Page<RespostaDetailsDto> listFiltered(Pageable pageable, RespostaDto filter);

    Optional<RespostaDetailsDto> getById(Integer id);

    RespostaDetailsDto create(RespostaDetailsDto dto);

    RespostaDetailsDto update(Integer id, RespostaDetailsDto dto);

    void softDelete(Integer id);
}