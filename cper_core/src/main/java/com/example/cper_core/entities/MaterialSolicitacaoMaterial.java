package com.example.cper_core.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "material_solicitacaomaterial")
public class MaterialSolicitacaoMaterial {

    @EmbeddedId
    private MaterialSolicitacaoMaterialId id;

    @MapsId("idMaterial")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_material", nullable = false)
    private Material material;

    @MapsId("idSolicitacao")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_solicitacao", nullable = false)
    private SolicitacaoMaterial solicitacaoMaterial;

    @Column(name = "qtd", nullable = false)
    private Integer qtd;
}