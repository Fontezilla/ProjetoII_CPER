package com.example.cper_core.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "material")
public class Material {

    // Attributes

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "material_id_gen")
    @SequenceGenerator(name = "material_id_gen", sequenceName = "material_id_material_seq", allocationSize = 1)
    @Column(name = "id_material", nullable = false)
    private Integer id;

    @Column(name = "nome", nullable = false, length = 256)
    private String nome;

    @Column(name = "descricao", length = Integer.MAX_VALUE)
    private String descricao;

    @Column(name = "categoria", length = 256)
    private String categoria;

    @Column(name = "uni_medida", length = 256)
    private String uniMedida;

    @ColumnDefault("0")
    @Column(name = "custo_uni", precision = 20, scale = 2)
    private BigDecimal custoUni;

    @ColumnDefault("0")
    @Column(name = "peso", precision = 20, scale = 2)
    private BigDecimal peso;

    @ColumnDefault("0")
    @Column(name = "volume", precision = 20, scale = 2)
    private BigDecimal volume;

    // Relationships

    @OneToMany(mappedBy = "material")
    private Set<MaterialSolicitacaoMaterial> materialSolicitacaomateriais = new LinkedHashSet<>();

    @OneToMany(mappedBy = "material")
    private Set<MaterialPedidoMaterial> materialPedidomateriais = new LinkedHashSet<>();

    @OneToMany(mappedBy = "material")
    private Set<Lote> lotes = new LinkedHashSet<>();

    // Getters and Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getUniMedida() {
        return uniMedida;
    }

    public void setUniMedida(String uniMedida) {
        this.uniMedida = uniMedida;
    }

    public BigDecimal getCustoUni() {
        return custoUni;
    }

    public void setCustoUni(BigDecimal custoUni) {
        this.custoUni = custoUni;
    }

    public BigDecimal getPeso() {
        return peso;
    }

    public void setPeso(BigDecimal peso) {
        this.peso = peso;
    }

    public BigDecimal getVolume() {
        return volume;
    }

    public void setVolume(BigDecimal volume) {
        this.volume = volume;
    }

    public Set<MaterialSolicitacaoMaterial> getMaterialSolicitacaomateriais() {
        return materialSolicitacaomateriais;
    }

    public void setMaterialSolicitacaomateriais(Set<MaterialSolicitacaoMaterial> materialSolicitacaomateriais) {
        this.materialSolicitacaomateriais = materialSolicitacaomateriais;
    }

    public Set<MaterialPedidoMaterial> getMaterialPedidomateriais() {
        return materialPedidomateriais;
    }

    public void setMaterialPedidomateriais(Set<MaterialPedidoMaterial> materialPedidomateriais) {
        this.materialPedidomateriais = materialPedidomateriais;
    }

    public Set<Lote> getLotes() {
        return lotes;
    }

    public void setLotes(Set<Lote> lotes) {
        this.lotes = lotes;
    }
}