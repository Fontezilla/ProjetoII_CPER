package com.example.cper_core.dtos.solicitacao_material;

import com.example.cper_core.entities.SolicitacaoMaterial;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link SolicitacaoMaterial}
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class SolicitacaoMaterialDetailsDto extends SolicitacaoMaterialDto implements Serializable {
    @NotNull(message = "A data do pedido não pode ser nula")
    private LocalDate dataPedido;

    @NotBlank(message = "A descrição não pode estar vazia")
    private String descricao;

    @NotBlank(message = "O estado não pode estar vazio")
    private String estado;

    public SolicitacaoMaterialDetailsDto(Integer id, LocalDate dataPedido, String descricao, String estado) {
        super(id);
        this.dataPedido = dataPedido;
        this.descricao = descricao;
        this.estado = estado;
    }
}