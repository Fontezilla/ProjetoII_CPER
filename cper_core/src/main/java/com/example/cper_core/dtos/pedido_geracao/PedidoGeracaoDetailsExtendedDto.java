package com.example.cper_core.dtos.pedido_geracao;

import com.example.cper_core.dtos.contrato.ContratoDto;
import com.example.cper_core.dtos.funcionario.FuncionarioDto;
import com.example.cper_core.dtos.centro_producao.CentroProducaoDto;
import jakarta.persistence.Column;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PedidoGeracaoDetailsExtendedDto extends PedidoGeracaoDetailsDto {

    private OffsetDateTime dataPrevisao;

    private BigDecimal qtdEnergiaProduzida;

    private BigDecimal qtdEnergiaProduzidaH;

    private String obs;

    private String relatorioFinal;

    @Valid
    private ContratoDto contrato;

    @Valid
    private CentroProducaoDto centroProducao;

    @Valid
    private FuncionarioDto funcionario;

    private Boolean isDeleted;
}