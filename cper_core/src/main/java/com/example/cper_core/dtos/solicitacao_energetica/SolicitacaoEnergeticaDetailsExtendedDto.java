package com.example.cper_core.dtos.solicitacao_energetica;

import com.example.cper_core.dtos.OnCreate;
import com.example.cper_core.dtos.cliente.ClienteDto;
import com.example.cper_core.dtos.contrato.ContratoDto;
import com.example.cper_core.dtos.funcionario.FuncionarioDto;
import com.example.cper_core.dtos.nota.NotaDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SolicitacaoEnergeticaDetailsExtendedDto extends SolicitacaoEnergeticaDetailsDto {

    @NotNull(groups = OnCreate.class, message = "A data de solicitação é obrigatória")
    private OffsetDateTime dataSolicitacao;

    private OffsetDateTime prazoEntrega;

    @Valid
    private ClienteDto cliente;

    @Valid
    private ContratoDto contrato;

    private Boolean isDeleted;
}
