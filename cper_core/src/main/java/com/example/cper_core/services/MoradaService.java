package com.example.cper_core.services;

import com.example.cper_core.dtos.endereco.EnderecoDetailsExtendedDto;
import com.example.cper_core.dtos.localidade.LocalidadeDetailsExtendedDto;
import com.example.cper_core.dtos.morada.MoradaSimplesDto;
import com.example.cper_core.services.interfaces.IMoradaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MoradaService implements IMoradaService {

    private final LocalidadeService localidadeService;
    private final EnderecoService enderecoService;

    public MoradaService(LocalidadeService localidadeService, EnderecoService enderecoService) {
        this.localidadeService = localidadeService;
        this.enderecoService = enderecoService;
    }

    @Override
    public EnderecoDetailsExtendedDto getEnderecoExistente(MoradaSimplesDto dto) {
        LocalidadeDetailsExtendedDto localidade = localidadeService.findByCodigoPostalAndNome(
                dto.getCodigoPostal().trim(), dto.getNomeLocalidade().trim());

        if (localidade == null) return null;

        return enderecoService.findByRuaAndLocalidade(dto.getRua().trim(), localidade.getId());
    }

    @Override
    public EnderecoDetailsExtendedDto criarEnderecoSeNecessario(MoradaSimplesDto dto) {
        EnderecoDetailsExtendedDto existente = getEnderecoExistente(dto);
        if (existente != null) return existente;

        LocalidadeDetailsExtendedDto localidade = localidadeService.findByCodigoPostalAndNome(
                dto.getCodigoPostal().trim(), dto.getNomeLocalidade().trim());

        if (localidade == null) {
            LocalidadeDetailsExtendedDto novaLocalidade = new LocalidadeDetailsExtendedDto();
            novaLocalidade.setCodigoPostal(dto.getCodigoPostal().trim());
            novaLocalidade.setNome(dto.getNomeLocalidade().trim());
            localidade = localidadeService.create(novaLocalidade);
        }

        EnderecoDetailsExtendedDto novoEndereco = new EnderecoDetailsExtendedDto();
        novoEndereco.setRua(dto.getRua().trim());
        novoEndereco.setLocalidade(localidade);
        return enderecoService.create(novoEndereco);
    }
}