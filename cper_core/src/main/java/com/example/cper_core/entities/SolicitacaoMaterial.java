package com.example.cper_core.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "\"solicitacao material\"")
public class SolicitacaoMaterial {

    // Attributes

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "solicitacao_material_id_gen")
    @SequenceGenerator(name = "solicitacao_material_id_gen", sequenceName = "solicitacao material_id_solicitacao_seq", allocationSize = 1)
    @Column(name = "id_solicitacao", nullable = false)
    private Integer id;

    @ColumnDefault("CURRENT_DATE")
    @Column(name = "data_pedido", nullable = false)
    private LocalDate dataPedido;

    @Column(name = "descricao", length = Integer.MAX_VALUE)
    private String descricao;

    @Column(name = "estado")
    private Integer estado;

    // Relationships

    @OneToMany(mappedBy = "solicitacaoMaterial")
    private Set<MaterialSolicitacaoMaterial> materialSolicitacaomateriais = new LinkedHashSet<>();

    // Getters and Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(LocalDate dataPedido) {
        this.dataPedido = dataPedido;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Set<MaterialSolicitacaoMaterial> getMaterialSolicitacaomateriais() {
        return materialSolicitacaomateriais;
    }

    public void setMaterialSolicitacaomateriais(Set<MaterialSolicitacaoMaterial> materialSolicitacaomateriais) {
        this.materialSolicitacaomateriais = materialSolicitacaomateriais;
    }
}