package com.example.cper_core.dtos.cliente;

import com.example.cper_core.entities.Cliente;
import lombok.Data;

import java.io.Serializable;

/**
 * DTO for {@link Cliente}
 */

@Data
public class ClienteLoginDto implements Serializable {
    private String password;
    private String email;

    public ClienteLoginDto(String password, String email) {
        this.password = password;
        this.email = email;
    }
}
