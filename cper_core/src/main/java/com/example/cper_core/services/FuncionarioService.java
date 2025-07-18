package com.example.cper_core.services;

import com.example.cper_core.dtos.auth.UpdatePassword;
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
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.cper_core.utils.*;
import static com.example.cper_core.utils.DtoUtils.*;

@Service
@Transactional
public class FuncionarioService extends AbstractXService<
        Funcionario,
        FuncionarioDto,
        FuncionarioDetailsDto,
        FuncionarioDetailsExtendedDto,
        FuncionarioFiltroDto,
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
    public FuncionarioDetailsExtendedDto create(FuncionarioDetailsExtendedDto dto) {
        validateDto(dto);

        Funcionario entity = toEntity(dto);

        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
            entity.setPassword(PasswordUtils.hashPassword(dto.getPassword()));
        }

        return toExtendedDto(funcionarioRepository.save(entity));
    }

    @Override
    protected Funcionario toEntity(FuncionarioDetailsExtendedDto dto) {
        return funcionarioMapper.toEntity(dto);
    }

    @Override
    protected void updateEntityFromDto(FuncionarioDetailsExtendedDto dto, Funcionario entity) {
        setIfPresent(dto.getNome(), entity::setNome);
        setIfPresent(dto.getNif(), entity::setNif);
        setIfPresent(dto.getEmail(), entity::setEmail);
        setIfPresent(dto.getTelefone(), entity::setTelefone);
        setIfPresent(dto.getCargo(), entity::setCargo);
        setIfPresent(dto.getDataNascimento(), entity::setDataNascimento);
        setIfPresent(dto.getDataContratacao(), entity::setDataContratacao);
        setIfPresent(dto.getSalario(), entity::setSalario);
        setIfPresent(dto.getNPorta(), entity::setNPorta);

        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
            entity.setPassword(PasswordUtils.hashPassword(dto.getPassword()));
        }

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
    public void updatePassword(int employeeId, UpdatePassword dto) {
        Funcionario employee = funcionarioRepository.findById(employeeId)
                .orElseThrow(() -> new EntityNotFoundException("Funcionário não encontrado."));

        if (!PasswordUtils.checkPassword(dto.getCurrentPassword(), employee.getPassword())) {
            throw new IllegalArgumentException("Palavra-passe atual incorreta.");
        }

        String hashedPassword = PasswordUtils.hashPassword(dto.getNewPassword());
        employee.setPassword(hashedPassword);
        funcionarioRepository.save(employee);
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
    protected void markedDeleted(Funcionario entity) {
        entity.setIsDeleted(true);
    }

    @Transactional(readOnly = true)
    public Page<FuncionarioPublicDto> listarPublicamente(Pageable pageable, FuncionarioFiltroDto filtro) {
        Specification<Funcionario> spec = FuncionarioSpecification.filter(filtro);
        return funcionarioRepository.findAll(spec, pageable)
                .map(func -> new FuncionarioPublicDto(
                        func.getId(),
                        func.getNome(),
                        func.getCargo()
                ));
    }

    @Transactional(readOnly = true)
    public FuncionarioPublicDto getPublicById(Integer id) {
        Funcionario func = funcionarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Funcionário não encontrado"));

        return new FuncionarioPublicDto(
                func.getId(),
                func.getNome(),
                func.getCargo()
        );
    }
}
