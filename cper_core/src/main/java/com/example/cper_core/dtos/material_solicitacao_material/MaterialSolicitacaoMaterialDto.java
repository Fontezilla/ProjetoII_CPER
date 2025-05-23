package com.example.cper_core.dtos.material_solicitacao_material;

import com.example.cper_core.entities.MaterialPedidoMaterialId;
import com.example.cper_core.entities.MaterialSolicitacaoMaterial;
import com.example.cper_core.entities.MaterialSolicitacaoMaterialId;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MaterialSolicitacaoMaterialDto implements Serializable {

    @Valid
    @NotNull(message = "O identificador composto é obrigatório")
    private MaterialSolicitacaoMaterialId id;
}