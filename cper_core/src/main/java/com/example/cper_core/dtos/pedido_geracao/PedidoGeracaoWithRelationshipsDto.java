package com.example.cper_core.dtos.pedido_geracao;

import com.example.cper_core.dtos.contrato.ContratoDto;
import com.example.cper_core.dtos.funcionario.FuncionarioDto;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PedidoGeracaoWithRelationshipsDto extends PedidoGeracaoDto {
    // Nada adicionado aqui, apenas para manter a estrutura {futuras extensões}
}
