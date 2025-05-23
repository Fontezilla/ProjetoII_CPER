package com.example.cper_core.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "material_pedidomaterial")
public class MaterialPedidoMaterial {

    @EmbeddedId
    private MaterialPedidoMaterialId id;

    @MapsId("idPedido")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_pedido", nullable = false)
    private PedidoMaterial pedidoMaterial;

    @MapsId("idMaterial")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_material", nullable = false)
    private Material material;

    @Column(name = "qtd", nullable = false)
    private Integer qtd;
}
