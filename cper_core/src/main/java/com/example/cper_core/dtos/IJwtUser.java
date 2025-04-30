package com.example.cper_core.dtos;

import com.example.cper_core.enums.JwtTipoUtilizador;

public interface IJwtUser {

    Integer getId();

    String getNome();

    JwtTipoUtilizador getTipo();
}
