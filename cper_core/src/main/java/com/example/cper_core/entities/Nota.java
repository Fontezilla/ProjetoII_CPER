package com.example.cper_core.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;

@Entity
@Table(name = "nota")
public class Nota {

    // Attributes

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "nota_id_gen")
    @SequenceGenerator(name = "nota_id_gen", sequenceName = "nota_id_nota_seq", allocationSize = 1)
    @Column(name = "id_nota", nullable = false)
    private Integer id;

    @Column(name = "descricao", length = Integer.MAX_VALUE)
    private String descricao;

    @ColumnDefault("CURRENT_DATE")
    @Column(name = "data_criacao", nullable = false)
    private LocalDate dataCriacao;

    @ColumnDefault("1")
    @Column(name = "prioridade", nullable = false)
    private Integer prioridade;

    @Column(name = "titulo")
    private String titulo;

    // Relationships

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_inspecao")
    private Inspecao inspecao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_anomalia")
    private Anomalia anomalia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_avaria")
    private Avaria avaria;

    // Getters and Setters

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

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Integer getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(Integer prioridade) {
        this.prioridade = prioridade;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Anomalia getAnomalia() {
        return anomalia;
    }

    public void setAnomalia(Anomalia anomalia) {
        this.anomalia = anomalia;
    }

    public Inspecao getInspecao() {
        return inspecao;
    }

    public void setInspecao(Inspecao inspecao) {
        this.inspecao = inspecao;
    }

    public Avaria getAvaria() {
        return avaria;
    }

    public void setAvaria(Avaria avaria) {
        this.avaria = avaria;
    }

}