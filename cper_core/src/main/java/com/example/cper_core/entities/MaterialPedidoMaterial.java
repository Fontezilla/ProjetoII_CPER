package com.example.cper_core.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "material_pedidomaterial")
public class MaterialPedidoMaterial {

    // Attributes

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

    @Column(name = "qtd")
    private Integer qtd;

    // Getters and Setters

    public MaterialPedidoMaterialId getId() {
        return id;
    }

    public void setId(MaterialPedidoMaterialId id) {
        this.id = id;
    }

    public PedidoMaterial getPedido() {
        return pedidoMaterial;
    }

    public void setPedido(PedidoMaterial pedidomaterial) {
        this.pedidoMaterial = pedidomaterial;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material idMaterial) {
        this.material = material;
    }

    public Integer getQtd() {
        return qtd;
    }

    public void setQtd(Integer qtd) {
        this.qtd = qtd;
    }

    public PedidoMaterial getPedidoMaterial() {
        return pedidoMaterial;
    }

    public void setPedidoMaterial(PedidoMaterial pedidoMaterial) {
        this.pedidoMaterial = pedidoMaterial;
    }
}