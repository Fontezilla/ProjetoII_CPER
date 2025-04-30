package com.example.cper_core.dtos.pedido_material;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * DTO for {@link com.example.cper_core.entities.PedidoMaterial}
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class PedidoMaterialDetailsDto extends PedidoMaterialDto implements Serializable {

    @NotNull(message = "A data de criação não pode ser nula")
    private LocalDate dataCriacao;

    @NotBlank(message = "A descrição não pode estar vazia")
    private String descricao;

    @NotBlank(message = "A prioridade não pode estar vazia")
    private String prioridade;

    @NotBlank(message = "O estado não pode estar vazio")
    private String estado;

    public PedidoMaterialDetailsDto(Integer id, LocalDate dataCriacao, String descricao, String prioridade, String estado) {
        super(id);
        this.dataCriacao = dataCriacao;
        this.descricao = descricao;
        this.prioridade = prioridade;
        this.estado = estado;
    }
}