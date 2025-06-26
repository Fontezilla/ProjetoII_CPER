package com.example.cper_core.services.interfaces;

import com.example.cper_core.dtos.endereco.EnderecoDetailsExtendedDto;
import com.example.cper_core.dtos.morada.MoradaSimplesDto;

public interface IMoradaService {
    EnderecoDetailsExtendedDto getEnderecoExistente(MoradaSimplesDto dto);
    EnderecoDetailsExtendedDto criarEnderecoSeNecessario(MoradaSimplesDto dto);
}