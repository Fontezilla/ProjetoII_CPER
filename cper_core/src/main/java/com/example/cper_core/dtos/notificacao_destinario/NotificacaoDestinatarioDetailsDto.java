package com.example.cper_core.dtos.notificacao_destinario;

import com.example.cper_core.dtos.OnCreate;
import com.example.cper_core.dtos.cliente.ClienteDto;
import com.example.cper_core.dtos.funcionario.FuncionarioDto;
import com.example.cper_core.dtos.notificacao.NotificacaoDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class NotificacaoDestinatarioDetailsDto extends NotificacaoDestinatarioDto {

    @NotNull(groups = OnCreate.class, message = "A notificação é obrigatória")
    @Valid
    private Integer notificacao;

    @Valid
    private Integer funcionario;

    @Valid
    private Integer cliente;

    private Boolean lida;
}