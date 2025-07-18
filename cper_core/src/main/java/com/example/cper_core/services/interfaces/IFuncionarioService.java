package com.example.cper_core.services.interfaces;

import com.example.cper_core.dtos.auth.UpdatePassword;
import com.example.cper_core.dtos.funcionario.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IFuncionarioService extends IXService<
        FuncionarioDto,
        FuncionarioDetailsDto,
        FuncionarioDetailsExtendedDto,
        FuncionarioFiltroDto,
        Integer
        > {
    void updatePassword(int employeeId, UpdatePassword dto);
    public FuncionarioPublicDto getPublicById(Integer id);
    public Page<FuncionarioPublicDto> listarPublicamente(Pageable pageable, FuncionarioFiltroDto filtro);
}
