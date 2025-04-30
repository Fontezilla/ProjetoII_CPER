package com.example.cper_core.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "material_solicitacaomaterial")
public class MaterialSolicitacaoMaterial {

    // Attributes

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

    @Column(name = "qtd")
    private Integer qtd;

    // Getters and Setters

    public MaterialSolicitacaoMaterialId getId() {
        return id;
    }

    public void setId(MaterialSolicitacaoMaterialId id) {
        this.id = id;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public SolicitacaoMaterial getSolicitacao() {
        return solicitacaoMaterial;
    }

    public void setIdSolicitacao(SolicitacaoMaterial solicitacaoMaterial) {
        this.solicitacaoMaterial = solicitacaoMaterial;
    }

    public Integer getQtd() {
        return qtd;
    }

    public void setQtd(Integer qtd) {
        this.qtd = qtd;
    }

    public SolicitacaoMaterial getSolicitacaoMaterial() {
        return solicitacaoMaterial;
    }

    public void setSolicitacaoMaterial(SolicitacaoMaterial solicitacaoMaterial) {
        this.solicitacaoMaterial = solicitacaoMaterial;
    }
}