package com.example.cper_core.dtos.centro_producao;

import com.example.cper_core.dtos.departamento.DepartamentoDto;
import com.example.cper_core.dtos.endereco.EnderecoDto;
import com.example.cper_core.dtos.funcionario.FuncionarioDto;
import com.example.cper_core.entities.CentroProducao;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/**
 * DTO for {@link CentroProducao}
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class CentroProducaoDetailsExtendedDto extends CentroProducaoDetailsDto implements Serializable {
    @NotNull(message = "O campo data de início não pode ser nulo")
    private LocalDate dataInicio;

    @NotNull(message = "O campo custo operacional não pode ser nulo")
    @PositiveOrZero(message = "O custo operacional deve ser zero ou positivo")
    private BigDecimal custoOperacional;

    private DepartamentoDto departamento;

    private FuncionarioDto funcionario;

    public CentroProducaoDetailsExtendedDto(Integer id, String nome, String tipoEnergia, BigDecimal capacidadeMax,
                                            String estado, Integer nPorta, EnderecoDto endereco, LocalDate dataInicio,
                                            BigDecimal custoOperacional, DepartamentoDto departamento,
                                            FuncionarioDto funcionario) {
        super(id, nome, tipoEnergia, capacidadeMax, estado, nPorta, endereco);
        this.dataInicio = dataInicio != null ? dataInicio : LocalDate.now();
        this.custoOperacional = custoOperacional;
        this.departamento = departamento;
        this.funcionario = funcionario;
    }
}