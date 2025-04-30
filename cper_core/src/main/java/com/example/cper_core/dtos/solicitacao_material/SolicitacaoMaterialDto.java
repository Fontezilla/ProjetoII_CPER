package com.example.cper_core.dtos.solicitacao_material;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link com.example.cper_core.entities.SolicitacaoMaterial}
 */

@Data
@NoArgsConstructor
public class SolicitacaoMaterialDto implements Serializable {
    private Integer id;

    public SolicitacaoMaterialDto(Integer id) {
        this.id = id;
    }
}