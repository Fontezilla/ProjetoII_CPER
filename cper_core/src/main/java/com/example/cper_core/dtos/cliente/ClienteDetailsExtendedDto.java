package com.example.cper_core.dtos.cliente;

import com.example.cper_core.dtos.OnCreate;
import com.example.cper_core.dtos.endereco.EnderecoDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ClienteDetailsExtendedDto extends ClienteDetailsDto {

    private BigDecimal demandaContratada;

    @NotNull(groups = OnCreate.class, message = "A data de nascimento é obrigatória")
    private OffsetDateTime dataNascimento;

    @NotNull(groups = OnCreate.class, message = "A data de cadastro é obrigatória")
    private OffsetDateTime dataCadastro;

    private BigDecimal consumoMedio;

    @NotNull(groups = OnCreate.class, message = "O número da porta é obrigatório")
    @Size(groups = OnCreate.class, max = 10, message = "O número da porta deve ter no máximo 10 caracteres")
    private String nPorta;

    @NotNull(groups = OnCreate.class, message = "O endereço é obrigatório")
    @Valid
    private EnderecoDto endereco;

    private Boolean isDeleted;
}
