package com.example.cper_core.dtos.resposta;

import com.example.cper_core.dtos.cliente.ClienteDto;
import com.example.cper_core.dtos.funcionario.FuncionarioDto;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class RespostaWithRelationshipsDto extends RespostaDto {
    // Nada adicionado aqui, apenas para manter a estrutura {futuras extensões}
}
