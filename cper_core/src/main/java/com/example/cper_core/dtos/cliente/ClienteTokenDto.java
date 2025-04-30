package com.example.cper_core.dtos.cliente;

import com.example.cper_core.dtos.IJwtUser;
import com.example.cper_core.entities.Cliente;
import com.example.cper_core.enums.JwtTipoUtilizador;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * DTO for {@link Cliente}
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class ClienteTokenDto extends ClienteDto implements Serializable, IJwtUser {
    private String nome;
    private final JwtTipoUtilizador tipoUtilizador = JwtTipoUtilizador.CLIENTE;

    public ClienteTokenDto(Integer id, String nome) {
        super(id);
        this.nome = nome;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public JwtTipoUtilizador getTipo() {
        return tipoUtilizador;
    }
}
