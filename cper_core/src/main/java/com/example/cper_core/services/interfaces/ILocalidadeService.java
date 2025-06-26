package com.example.cper_core.services.interfaces;

import com.example.cper_core.dtos.localidade.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Set;

public interface ILocalidadeService extends IXService<
        LocalidadeDto,
        LocalidadeDetailsDto,
        LocalidadeDetailsExtendedDto,
        LocalidadeFiltroDto,
        LocalidadeWithRelationshipsDto,
        Integer
        > {

    LocalidadeDetailsExtendedDto findByCodigoPostalAndNome(String codigoPostal, String nome);
}
