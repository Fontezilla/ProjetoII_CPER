package com.example.cper_core.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;

@Entity
@Table(name = "lote")
public class Lote {
    @EmbeddedId
    private LoteId id;

    @MapsId("idStock")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_stock", nullable = false)
    private Stock stock;

    @MapsId("idMaterial")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_material", nullable = false)
    private Material material;

    @ColumnDefault("0")
    @Column(name = "qtd")
    private Integer qtd;

    @Column(name = "data_validade")
    private LocalDate dataValidade;

    @ColumnDefault("CURRENT_DATE")
    @Column(name = "data_adicao", nullable = false)
    private LocalDate dataAdicao;

    public LoteId getId() {
        return id;
    }

    public void setId(LoteId id) {
        this.id = id;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Integer getQtd() {
        return qtd;
    }

    public void setQtd(Integer qtd) {
        this.qtd = qtd;
    }

    public LocalDate getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(LocalDate dataValidade) {
        this.dataValidade = dataValidade;
    }

    public LocalDate getDataAdicao() {
        return dataAdicao;
    }

    public void setDataAdicao(LocalDate dataAdicao) {
        this.dataAdicao = dataAdicao;
    }


}