package com.example.cper_core.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "stock")
public class Stock {

    // Attributes

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stock_id_gen")
    @SequenceGenerator(name = "stock_id_gen", sequenceName = "stock_id_stock_seq", allocationSize = 1)
    @Column(name = "id_stock", nullable = false)
    private Integer id;

    @ColumnDefault("0")
    @Column(name = "qtd_minima", nullable = false)
    private Integer qtdMinima;

    @Column(name = "qtd_maxima")
    private Integer qtdMaxima;

    @Column(name = "localizacao", length = 256)
    private String localizacao;

    @ColumnDefault("CURRENT_DATE")
    @Column(name = "data_adicao", nullable = false)
    private LocalDate dataAdicao;

    // Relationships

    @OneToMany(mappedBy = "stock")
    private Set<Lote> lotes = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(
            name = "armazem_stock",
            joinColumns = @JoinColumn(name = "id_stock"),
            inverseJoinColumns = @JoinColumn(name = "id_armazem")
    )
    private Set<Armazem> armazems = new LinkedHashSet<>();

    // Getters and Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQtdMinima() {
        return qtdMinima;
    }

    public void setQtdMinima(Integer qtdMinima) {
        this.qtdMinima = qtdMinima;
    }

    public Integer getQtdMaxima() {
        return qtdMaxima;
    }

    public void setQtdMaxima(Integer qtdMaxima) {
        this.qtdMaxima = qtdMaxima;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public LocalDate getDataAdicao() {
        return dataAdicao;
    }

    public void setDataAdicao(LocalDate dataAdicao) {
        this.dataAdicao = dataAdicao;
    }

    public Set<Lote> getLotes() {
        return lotes;
    }

    public void setLotes(Set<Lote> lotes) {
        this.lotes = lotes;
    }

    public Set<Armazem> getArmazems() {
        return armazems;
    }

    public void setArmazems(Set<Armazem> armazems) {
        this.armazems = armazems;
    }
}