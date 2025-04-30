package com.example.cper_core.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "avaria")
public class Avaria {

    // Attributes

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "avaria_id_gen")
    @SequenceGenerator(name = "avaria_id_gen", sequenceName = "avaria_id_avaria_seq", allocationSize = 1)
    @Column(name = "id_avaria", nullable = false)
    private Integer id;

    @Column(name = "descricao", length = Integer.MAX_VALUE)
    private String descricao;

    @ColumnDefault("CURRENT_DATE")
    @Column(name = "data_inicio")
    private LocalDate dataInicio;

    @Column(name = "data_resolucao")
    private LocalDate dataResolucao;

    @Column(name = "gravidade")
    private Integer gravidade;

    @ColumnDefault("0")
    @Column(name = "impacto_producao", length = Integer.MAX_VALUE)
    private String impactoProducao;

    @Column(name = "estado")
    private Integer estado;

    // Relationships

    @OneToMany(mappedBy = "avaria")
    private Set<Nota> notas = new LinkedHashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_centro")
    private CentroProducao centroProducao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_inspecao")
    private Inspecao inspecao;

    @ManyToMany
    @JoinTable(
            name = "equipa_avaria",
            joinColumns = @JoinColumn(name = "id_avaria"),
            inverseJoinColumns = @JoinColumn(name = "id_equipa")
    )
    private Set<Equipa> equipas = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(
            name = "avaria_pedidomaterial",
            joinColumns = @JoinColumn(name = "id_avaria"),
            inverseJoinColumns = @JoinColumn(name = "id_pedido")
    )
    private Set<PedidoMaterial> pedidoMateriais = new LinkedHashSet<>();

    // Getters and Setters

    public CentroProducao getCentroProducao() {
        return centroProducao;
    }

    public void setCentroProducao(CentroProducao centroProducao) {
        this.centroProducao = centroProducao;
    }

    public Inspecao getInspecao() {
        return inspecao;
    }

    public void setInspecao(Inspecao inspecao) {
        this.inspecao = inspecao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataResolucao() {
        return dataResolucao;
    }

    public void setDataResolucao(LocalDate dataResolucao) {
        this.dataResolucao = dataResolucao;
    }

    public Integer getGravidade() {
        return gravidade;
    }

    public void setGravidade(Integer gravidade) {
        this.gravidade = gravidade;
    }

    public String getImpactoProducao() {
        return impactoProducao;
    }

    public void setImpactoProducao(String impactoProducao) {
        this.impactoProducao = impactoProducao;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    // Getter and Setter for notas
    public Set<Nota> getNotas() {
        return notas;
    }

    public void setNotas(Set<Nota> notas) {
        this.notas = notas;
    }

    public Set<Equipa> getEquipas() {
        return equipas;
    }

    public void setEquipas(Set<Equipa> equipas) {
        this.equipas = equipas;
    }

    public Set<PedidoMaterial> getPedidoMateriais() {
        return pedidoMateriais;
    }

    public void setPedidoMateriais(Set<PedidoMaterial> pedidoMateriais) {
        this.pedidoMateriais = pedidoMateriais;
    }

}