package com.example.cper_core.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "contrato")
@ToString(onlyExplicitlyIncluded = true)
public class Contrato {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contrato_id_gen")
    @SequenceGenerator(name = "contrato_id_gen", sequenceName = "contrato_id_contrato_seq", allocationSize = 1)
    @EqualsAndHashCode.Include
    @ToString.Include
    @Column(name = "id_contrato", nullable = false)
    private Integer id;

    @Column(name = "data_inicio", nullable = false)
    private OffsetDateTime dataInicio;

    @Column(name = "data_fim")
    private OffsetDateTime dataFim;

    @Column(name = "tipo_contrato")
    private Integer tipoContrato;

    @Column(name = "qtd_energia", nullable = false, precision = 20, scale = 2)
    private BigDecimal qtdEnergia;

    @Column(name = "qtd_energia_h", nullable = false, precision = 20, scale = 2)
    private BigDecimal qtdEnergiaH;

    @Column(name = "prazo_pagamento")
    private Integer prazoPagamento;

    @Column(name = "multa_atraso")
    private Integer multaAtraso;

    @Column(name = "estado")
    private Integer estado;

    @Column(name = "n_porta", length = 10)
    private String nPorta;

    @Column(name = "v_electricidade", nullable = false)
    private BigDecimal vElectricidade;

    @Column(name = "taxa", nullable = false)
    private Integer taxa;

    @Column(name = "requer_validacao", nullable = false)
    private Boolean requerValidacao;

    @Column(name = "codigo")
    private String codigo;

    @Column(name = "is_deleted")
    @Builder.Default
    private Boolean isDeleted = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_funcionario")
    private Funcionario funcionario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_endereco")
    private Endereco endereco;

    @OneToOne(mappedBy = "contrato")
    private SolicitacaoEnergetica solicitacaoEnergetica;

    @OneToMany(mappedBy = "contrato")
    @Builder.Default
    private Set<Fatura> faturas = new LinkedHashSet<>();

    @OneToMany(mappedBy = "contrato")
    @Builder.Default
    private Set<PedidoGeracao> pedidosGeracao = new LinkedHashSet<>();

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy proxy ? proxy.getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy proxy ? proxy.getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Contrato contrato = (Contrato) o;
        return getId() != null && Objects.equals(getId(), contrato.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy proxy ? proxy.getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
