package com.example.cper_core.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "comentario")
public class Comentario {

    // Attributes

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comentario_id_gen")
    @SequenceGenerator(name = "comentario_id_gen", sequenceName = "comentario_id_comentario_seq", allocationSize = 1)
    @Column(name = "id_comentario", nullable = false)
    private Integer id;

    @Column(name = "descricao", length = Integer.MAX_VALUE)
    private String descricao;

    @Column(name = "data_criacao", nullable = false)
    private LocalDate dataCriacao;

    // Relationships

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_solicitacao")
    private SolicitacaoEnergetica solicitacaoEnergetica;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_funcionario")
    private Funcionario funcionario;

    // Lifecycle callback
    @PrePersist
    protected void onCreate() {
        if (this.dataCriacao == null) {
            this.dataCriacao = LocalDate.now();
        }
    }

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

    public SolicitacaoEnergetica getSolicitacaoEnergetica() {
        return solicitacaoEnergetica;
    }

    public void setSolicitacaoEnergetica(SolicitacaoEnergetica solicitacaoEnergetica) {
        this.solicitacaoEnergetica = solicitacaoEnergetica;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }
}