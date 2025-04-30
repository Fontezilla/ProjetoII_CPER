package com.example.cper_core.dtos.material_solicitacao_material;

import com.example.cper_core.dtos.material.MaterialDto;
import com.example.cper_core.dtos.solicitacao_material.SolicitacaoMaterialDto;
import com.example.cper_core.entities.MaterialSolicitacaoMaterial;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.io.Serializable;

/**
 * DTO for {@link MaterialSolicitacaoMaterial}
 */

@Data
public class MaterialSolicitacaoMaterialDto implements Serializable {
    @NotNull(message = "O material não pode ser nulo")
    private MaterialDto material;

    @NotNull(message = "A solicitação de material não pode ser nulo")
    private SolicitacaoMaterialDto solicitacaoMaterial;

    @NotNull(message = "A quantidade não pode ser nula")
    @Positive(message = "A quantidade deve ser um valor positivo")
    private Integer qtd;

    public MaterialSolicitacaoMaterialDto(MaterialDto material, SolicitacaoMaterialDto solicitacaoMaterial, Integer qtd) {
        this.material = material;
        this.solicitacaoMaterial = solicitacaoMaterial;
        this.qtd = qtd;
    }
}