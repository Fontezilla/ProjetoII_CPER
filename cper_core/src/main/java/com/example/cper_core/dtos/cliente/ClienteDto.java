package com.example.cper_core.dtos.cliente;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.sql.Update;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDto implements Serializable {
    @NotNull(groups = Update.class, message = "O ID do cliente é obrigatório")
    private Integer id;
}

