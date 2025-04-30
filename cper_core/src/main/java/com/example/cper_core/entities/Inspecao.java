package com.example.cper_core.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "inspecao")
public class Inspecao {

    // Attributes

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "inspecao_id_gen")
    @SequenceGenerator(name = "inspecao_id_gen", sequenceName = "inspecao_id_inspecao_seq", allocationSize = 1)
    @Column(name = "id_inspecao", nullable = false)
    private Integer id;

    @Column(name = "descricao", length = Integer.MAX_VALUE)
    private String descricao;

    @ColumnDefault("CURRENT_DATE")
    @Column(name = "data", nullable = false)
    private LocalDate data;

    @Column(name = "tipo")
    private Integer tipo;

    @Column(name = "area_inspecionada", length = Integer.MAX_VALUE)
    private String areaInspecionada;

    @Column(name = "resultados", length = Integer.MAX_VALUE)
    private String resultados;

    @Column(name = "estado")
    private Integer estado;

    // Relationships

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_anomalia", unique = true)
    private Anomalia anomalia;

    @OneToMany(mappedBy = "inspecao")
    private Set<Nota> notas = new LinkedHashSet<>();

    @OneToMany(mappedBy = "inspecao")
    private Set<Avaria> avarias = new LinkedHashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_centro")
    private CentroProducao centroProducao;

    @ManyToMany
    @JoinTable(
            name = "equipa_inspecao",
            joinColumns = @JoinColumn(name = "id_inspecao"),
            inverseJoinColumns = @JoinColumn(name = "id_equipa")
    )
    private Set<Equipa> equipas = new LinkedHashSet<>();

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

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public String getAreaInspecionada() {
        return areaInspecionada;
    }

    public void setAreaInspecionada(String areaInspecionada) {
        this.areaInspecionada = areaInspecionada;
    }

    public String getResultados() {
        return resultados;
    }

    public void setResultados(String resultados) {
        this.resultados = resultados;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Anomalia getAnomalia() {
        return anomalia;
    }

    public void setIdAnomalia(Anomalia anomalia) {
        this.anomalia = anomalia;
    }

    public CentroProducao getCentro() {
        return centroProducao;
    }

    public void setCentro(CentroProducao centroProducao) {
        this.centroProducao = centroProducao;
    }

    public void setAnomalia(Anomalia anomalia) {
        this.anomalia = anomalia;
    }

    public Set<Nota> getNotas() {
        return notas;
    }

    public void setNotas(Set<Nota> notas) {
        this.notas = notas;
    }

    public Set<Avaria> getAvarias() {
        return avarias;
    }

    public void setAvarias(Set<Avaria> avarias) {
        this.avarias = avarias;
    }

    public CentroProducao getCentroProducao() {
        return centroProducao;
    }

    public void setCentroProducao(CentroProducao centroProducao) {
        this.centroProducao = centroProducao;
    }

    public Set<Equipa> getEquipas() {
        return equipas;
    }

    public void setEquipas(Set<Equipa> equipas) {
        this.equipas = equipas;
    }
}