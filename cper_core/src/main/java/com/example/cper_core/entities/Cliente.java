package com.example.cper_core.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "cliente")
public class Cliente {

    // Attributes

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cliente_id_gen")
    @SequenceGenerator(name = "cliente_id_gen", sequenceName = "cliente_id_cliente_seq", allocationSize = 1)
    @Column(name = "id_cliente", nullable = false)
    private Integer id;

    @ColumnDefault("0")
    @Column(name = "demanda_contratada", precision = 20, scale = 2)
    private BigDecimal demandaContratada;

    @Column(name = "nome", length = 256)
    private String nome;

    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    @Column(name = "nif", length = 15)
    private String nif;

    @Column(name = "email", length = 256)
    private String email;

    @Column(name = "telefone", length = 20)
    private String telefone;

    @ColumnDefault("CURRENT_DATE")
    @Column(name = "data_cadastro")
    private LocalDate dataCadastro;

    @ColumnDefault("0")
    @Column(name = "consumo_medio", precision = 20, scale = 2)
    private BigDecimal consumoMedio;

    @Column(name = "cat_consumo")
    private Integer catConsumo;

    @Column(name = "tipo_energia")
    private Integer tipoEnergia;

    @ColumnDefault("1")
    @Column(name = "estado", nullable = false)
    private Integer estado;

    @Column(name = "password", length = 256, nullable = false)
    private String password;

    @Column(name = "n_porta", nullable = false)
    private Integer nPorta;

    // Relationships

    @OneToMany(mappedBy = "cliente")
    private Set<PerfilConsumo> perfilConsumos = new LinkedHashSet<>();

    @OneToMany(mappedBy = "cliente")
    private Set<SolicitacaoEnergetica> solicitacaoEnergeticas = new LinkedHashSet<>();

    @OneToMany(mappedBy = "cliente")
    private Set<Ticket> tickets = new LinkedHashSet<>();

    @OneToMany(mappedBy = "cliente")
    private Set<Resposta> respostas = new LinkedHashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_endereco")
    private Endereco endereco;

    // Getters and Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getDemandaContratada() {
        return demandaContratada;
    }

    public void setDemandaContratada(BigDecimal demandaContratada) {
        this.demandaContratada = demandaContratada;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public BigDecimal getConsumoMedio() {
        return consumoMedio;
    }

    public void setConsumoMedio(BigDecimal consumoMedio) {
        this.consumoMedio = consumoMedio;
    }

    public Integer getCatConsumo() {
        return catConsumo;
    }

    public void setCatConsumo(Integer catConsumo) {
        this.catConsumo = catConsumo;
    }

    public Integer getTipoEnergia() {
        return tipoEnergia;
    }

    public void setTipoEnergia(Integer TipoEnergia) {
        this.tipoEnergia = TipoEnergia;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Set<PerfilConsumo> getPerfilConsumos() {
        return perfilConsumos;
    }

    public void setPerfilConsumos(Set<PerfilConsumo> perfilConsumos) {
        this.perfilConsumos = perfilConsumos;
    }

    public Set<SolicitacaoEnergetica> getSolicitacaoEnergeticas() {
        return solicitacaoEnergeticas;
    }

    public void setSolicitacaoEnergeticas(Set<SolicitacaoEnergetica> solicitacaoEnergeticas) {
        this.solicitacaoEnergeticas = solicitacaoEnergeticas;
    }

    public Set<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Set<Ticket> tickets) {
        this.tickets = tickets;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getnPorta() {
        return nPorta;
    }

    public void setnPorta(Integer nPorta) {
        this.nPorta = nPorta;
    }

    public Set<Resposta> getRespostas() {
        return respostas;
    }

    public void setRespostas(Set<Resposta> respostas) {
        this.respostas = respostas;
    }
}