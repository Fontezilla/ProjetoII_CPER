package com.example.cper_core.dtos.armazem;

import com.example.cper_core.dtos.departamento.DepartamentoDto;
import com.example.cper_core.dtos.endereco.EnderecoDto;
import com.example.cper_core.dtos.funcionario.FuncionarioDto;
import com.example.cper_core.entities.Armazem;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link Armazem}
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class ArmazemDetailsExtendedDto extends ArmazemDetailsDto implements Serializable {

    @NotNull(message = "O campo data de criação não pode ser nulo")
    private LocalDate dataCriacao;

    @NotNull(message = "O campo data de atualização não pode ser nulo")
    private LocalDate dataUpdate;

    private DepartamentoDto departamento;

    private FuncionarioDto responsavel;

    public ArmazemDetailsExtendedDto(Integer id, String nome, Integer capacidadeTotal, Integer capacidadeOcupada,
                                     Integer nPorta, EnderecoDto endereco, LocalDate dataCriacao,
                                     LocalDate dataUpdate, DepartamentoDto departamento,
                                     FuncionarioDto responsavel, String estado) {
        super(id, nome, capacidadeTotal, capacidadeOcupada, nPorta, endereco, estado);
        this.dataCriacao = dataCriacao != null ? dataCriacao : LocalDate.now();
        this.dataUpdate = dataUpdate != null ? dataUpdate : LocalDate.now();
        this.departamento = departamento;
        this.responsavel = responsavel;
    }
}