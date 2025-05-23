package com.example.cper_core.services;

import com.example.cper_core.dtos.funcionario.*;
import com.example.cper_core.entities.Departamento;
import com.example.cper_core.entities.Endereco;
import com.example.cper_core.entities.Funcionario;
import com.example.cper_core.mappers.FuncionarioMapper;
import com.example.cper_core.repositories.DepartamentoRepository;
import com.example.cper_core.repositories.EnderecoRepository;
import com.example.cper_core.repositories.FuncionarioRepository;
import com.example.cper_core.services.interfaces.IFuncionarioService;
import com.example.cper_core.specifications.FuncionarioSpecification;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FuncionarioService extends AbstractXService<
        Funcionario,
        FuncionarioDto,
        FuncionarioDetailsDto,
        FuncionarioDetailsExtendedDto,
        FuncionarioFiltroDto,
        FuncionarioWithRelationshipsDto,
        Integer
        > implements IFuncionarioService {

    private final FuncionarioMapper funcionarioMapper;
    private final FuncionarioRepository funcionarioRepository;
    private final DepartamentoRepository departamentoRepository;
    private final EnderecoRepository enderecoRepository;

    public FuncionarioService(
            FuncionarioRepository funcionarioRepository,
            FuncionarioMapper funcionarioMapper,
            jakarta.validation.Validator validator,
            DepartamentoRepository departamentoRepository, EnderecoRepository enderecoRepository) {
        super(funcionarioRepository, funcionarioRepository, validator);
        this.funcionarioMapper = funcionarioMapper;
        this.funcionarioRepository = funcionarioRepository;
        this.departamentoRepository = departamentoRepository;
        this.enderecoRepository = enderecoRepository;
    }

    @Override
    protected Funcionario toEntity(FuncionarioDetailsExtendedDto dto) {
        return funcionarioMapper.toEntity(dto);
    }

    @Override
    protected void updateEntityFromDto(FuncionarioDetailsExtendedDto dto, Funcionario entity) {
        if (dto.getNome() != null) entity.setNome(dto.getNome());
        if (dto.getNif() != null) entity.setNif(dto.getNif());
        if (dto.getEmail() != null) entity.setEmail(dto.getEmail());
        if (dto.getPassword() != null) entity.setPassword(dto.getPassword());
        if (dto.getTelefone() != null) entity.setTelefone(dto.getTelefone());
        if (dto.getCargo() != null) entity.setCargo(dto.getCargo());
        if (dto.getDataNascimento() != null) entity.setDataNascimento(dto.getDataNascimento());
        if (dto.getDataContratacao() != null) entity.setDataContratacao(dto.getDataContratacao());
        if (dto.getSalario() != null) entity.setSalario(dto.getSalario());
        if (dto.getNPorta() != null) entity.setNPorta(dto.getNPorta());

        if (dto.getDepartamento() != null) {
            Departamento departamento = departamentoRepository.findById(dto.getDepartamento().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Departamento não encontrado"));
            entity.setDepartamento(departamento);
        }

        if (dto.getEndereco() != null) {
            Endereco endereco = enderecoRepository.findById(dto.getEndereco().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Endereço não encontrado"));
            entity.setEndereco(endereco);
        }
    }

    @Override
    protected FuncionarioDetailsExtendedDto toExtendedDto(Funcionario entity) {
        return funcionarioMapper.toExtendedDto(entity);
    }

    @Override
    protected FuncionarioDetailsDto toDetailsDto(Funcionario entity) {
        return funcionarioMapper.toDetailsDto(entity);
    }

    @Override
    protected Specification<Funcionario> getSpecificationFromFiltro(FuncionarioFiltroDto filtro) {
        return FuncionarioSpecification.filter(filtro);
    }

    @Override
    protected void marcarComoEliminado(Funcionario entity) {
        entity.setIsDeleted(true);
    }
}
