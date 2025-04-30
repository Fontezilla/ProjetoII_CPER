package com.example.cper_core.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "\"pedido material\"")
public class PedidoMaterial {

    // Attributes

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pedido__id_gen")
    @SequenceGenerator(name = "pedido__id_gen", sequenceName = "pedido material_id_pedido_seq", allocationSize = 1)
    @Column(name = "id_pedido", nullable = false)
    private Integer id;

    @ColumnDefault("CURRENT_DATE")
    @Column(name = "data_criacao", nullable = false)
    private LocalDate dataCriacao;

    @Column(name = "descricao", length = Integer.MAX_VALUE)
    private String descricao;

    @Column(name = "prioridade")
    private Integer prioridade;

    @Column(name = "estado")
    private Integer estado;

    // Relationships

    @OneToMany(mappedBy = "pedidoMaterial")
    private Set<MaterialPedidoMaterial> materialPedidomateriais = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(
            name = "avaria_pedidomaterial",
            joinColumns = @JoinColumn(name = "id_pedido"),
            inverseJoinColumns = @JoinColumn(name = "id_avaria")
    )
    private Set<Avaria> avarias = new LinkedHashSet<>();

    // Getters and Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(Integer prioridade) {
        this.prioridade = prioridade;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Set<MaterialPedidoMaterial> getMaterialPedidomateriais() {
        return materialPedidomateriais;
    }

    public void setMaterialPedidomateriais(Set<MaterialPedidoMaterial> materialPedidomateriais) {
        this.materialPedidomateriais = materialPedidomateriais;
    }

    public Set<Avaria> getAvarias() {
        return avarias;
    }

    public void setAvarias(Set<Avaria> avarias) {
        this.avarias = avarias;
    }
}