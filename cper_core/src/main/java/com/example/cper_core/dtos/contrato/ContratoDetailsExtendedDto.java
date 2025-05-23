package com.example.cper_core.dtos.contrato;

import com.example.cper_core.dtos.OnCreate;
import com.example.cper_core.dtos.endereco.EnderecoDto;
import com.example.cper_core.dtos.funcionario.FuncionarioDto;
import com.example.cper_core.dtos.solicitacao_energetica.SolicitacaoEnergeticaDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ContratoDetailsExtendedDto extends ContratoDetailsDto {

    @NotNull(groups = OnCreate.class, message = "A data de início é obrigatória")
    private OffsetDateTime dataInicio;

    private OffsetDateTime dataFim;

    private Integer prazoPagamento;

    private Integer multaAtraso;

    @NotNull(groups = OnCreate.class, message = "A solicitação energética é obrigatória")
    @Valid
    private SolicitacaoEnergeticaDto solicitacaoEnergetica;

    @NotNull(groups = OnCreate.class, message = "O funcionário é obrigatório")
    @Valid
    private FuncionarioDto funcionario;

    @NotBlank(groups = OnCreate.class, message = "O número da porta é obrigatório")
    @Size(groups = OnCreate.class, max = 10, message = "O número da porta deve ter no máximo 10 caracteres")
    private String nPorta;

    @NotNull(groups = OnCreate.class, message = "O endereço é obrigatório")
    @Valid
    private EnderecoDto endereco;

    private Boolean isDeleted;
}

