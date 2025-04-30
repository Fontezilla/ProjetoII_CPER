package com.example.cper_core.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "anomalia")
public class Anomalia {

    // Attributes

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "anomalia_id_gen")
    @SequenceGenerator(name = "anomalia_id_gen", sequenceName = "anomalia_id_anomalia_seq", allocationSize = 1)
    @Column(name = "id_anomalia", nullable = false)
    private Integer id;

    @Column(name = "tipo_anomalia")
    private Integer tipoAnomalia;

    @Column(name = "descricao", length = Integer.MAX_VALUE)
    private String descricao;

    @ColumnDefault("CURRENT_DATE")
    @Column(name = "data_identificacao")
    private LocalDate dataIdentificacao;

    @Column(name = "localizacao")
    private String localizacao;

    @Column(name = "gravidade")
    private Integer gravidade;

    @Column(name = "estado")
    private Integer estado;

    // Relationships

    @OneToOne(mappedBy = "anomalia")
    private Inspecao inspecao;

    @OneToMany(mappedBy = "anomalia")
    private Set<Nota> notas = new LinkedHashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_centro")
    private CentroProducao centroProducao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_funcionario")
    private Funcionario funcionario;

    // Getters and Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTipoAnomalia() {
        return tipoAnomalia;
    }

    public void setTipoAnomalia(Integer tipoAnomalia) {
        this.tipoAnomalia = tipoAnomalia;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataIdentificacao() {
        return dataIdentificacao;
    }

    public void setDataIdentificacao(LocalDate dataIdentificacao) {
        this.dataIdentificacao = dataIdentificacao;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public Integer getGravidade() {
        return gravidade;
    }

    public void setGravidade(Integer gravidade) {
        this.gravidade = gravidade;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Inspecao getInspecao() {
        return inspecao;
    }

    public void setInspecao(Inspecao inspecao) {
        this.inspecao = inspecao;
    }

    // Getter and Setter for notas
    public Set<Nota> getNotas() {
        return notas;
    }

    public void setNotas(Set<Nota> notas) {
        this.notas = notas;
    }

    // Getter and Setter for centroProducao
    public CentroProducao getCentroProducao() {
        return centroProducao;
    }

    public void setCentroProducao(CentroProducao centroProducao) {
        this.centroProducao = centroProducao;
    }
}