package com.example.cper_core.dtos.cliente;

import com.example.cper_core.entities.Cliente;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link Cliente}
 */

@Data
@NoArgsConstructor
public class ClienteDto implements Serializable {
    private Integer id;

    public ClienteDto(Integer id) {
        this.id = id;
    }
}