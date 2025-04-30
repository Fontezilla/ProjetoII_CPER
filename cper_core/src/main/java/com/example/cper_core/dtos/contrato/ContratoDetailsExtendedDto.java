package com.example.cper_core.dtos.contrato;

import com.example.cper_core.dtos.endereco.EnderecoDto;
import com.example.cper_core.dtos.funcionario.FuncionarioDto;
import com.example.cper_core.dtos.solicitacao_energetica.SolicitacaoEnergeticaDto;
import com.example.cper_core.entities.Contrato;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * DTO for {@link Contrato}
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class ContratoDetailsExtendedDto extends ContratoDetailsDto implements Serializable {
    @NotNull(message = "O campo data de início não pode ser nulo")
    private LocalDate dataInicio;

    @NotNull(message = "O campo data de fim não pode ser nulo")
    private LocalDate dataFim;

    @NotNull(message = "O campo prazo de pagamento não pode ser nulo")
    @Positive(message = "O prazo de pagamento deve ser um valor positivo")
    private Integer prazoPagamento;

    @NotNull(message = "O campo multa por atraso não pode ser nulo")
    @Positive(message = "A multa por atraso deve ser um valor positivo")
    private Integer multaAtraso;

    @NotNull(message = "O campo número da porta não pode ser nulo")
    @Positive(message = "O número da porta deve ser um valor positivo")
    private Integer nPorta;

    @NotNull(message = "O campo solicitação energética não pode ser nulo")
    private SolicitacaoEnergeticaDto solicitacaoEnergetica;

    @NotNull(message = "O campo funcionário não pode ser nulo")
    private FuncionarioDto funcionario;

    @NotNull(message = "O campo endereço não pode ser nulo")
    private EnderecoDto endereco;

    public ContratoDetailsExtendedDto(Integer id, String tipoContrato, BigDecimal qtdEnergia, String estado, LocalDate dataInicio, LocalDate dataFim, Integer prazoPagamento, Integer multaAtraso, Integer nPorta, SolicitacaoEnergeticaDto solicitacaoEnergetica, FuncionarioDto funcionario, EnderecoDto endereco) {
        super(id, tipoContrato, qtdEnergia, estado);
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.prazoPagamento = prazoPagamento;
        this.multaAtraso = multaAtraso;
        this.nPorta = nPorta;
        this.solicitacaoEnergetica = solicitacaoEnergetica;
        this.funcionario = funcionario;
        this.endereco = endereco;
    }
}