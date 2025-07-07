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
@Table(name = "centro_producao")
@ToString(onlyExplicitlyIncluded = true)
public class CentroProducao {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "centro_producao_id_gen")
    @SequenceGenerator(name = "centro_producao_id_gen", sequenceName = "centro_producao_id_centro_seq", allocationSize = 1)
    @EqualsAndHashCode.Include
    @ToString.Include
    @Column(name = "id_centro", nullable = false)
    private Integer id;

    @Column(name = "nome", length = 256)
    private String nome;

    @Column(name = "tipo_energia")
    private Integer tipoEnergia;

    @Column(name = "capacidade_max", nullable = false, precision = 20, scale = 2)
    private BigDecimal capacidadeMax;

    @Column(name = "capacidade_atual", precision = 20, scale = 2)
    private BigDecimal capacidadeAtual;

    @Column(name = "data_inicio", nullable = false)
    private OffsetDateTime dataInicio;

    @Column(name = "custo_operacional", nullable = false, precision = 20, scale = 2)
    private BigDecimal custoOperacional;

    @Column(name = "estado")
    private Integer estado;

    @Column(name = "codigo")
    private String codigo;

    @Column(name = "n_porta")
    private String nPorta;

    @Column(name = "is_deleted")
    @Builder.Default
    private Boolean isDeleted = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_departamento")
    private Departamento departamento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_endereco")
    private Endereco endereco;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_funcionario")
    private Funcionario responsavel;

    @OneToMany(mappedBy = "centroProducao")
    @Builder.Default
    private Set<Avaria> avarias = new LinkedHashSet<>();

    @OneToMany(mappedBy = "centroProducao")
    @Builder.Default
    private Set<Inspecao> inspecoes = new LinkedHashSet<>();

    @OneToMany(mappedBy = "centroProducao")
    @Builder.Default
    private Set<Anomalia> anomalias = new LinkedHashSet<>();

    @OneToMany(mappedBy = "centroProducao")
    @Builder.Default
    private Set<PedidoGeracao> pedidosGeracao = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(
            name = "funcionario_centro",
            joinColumns = @JoinColumn(name = "id_centro"),
            inverseJoinColumns = @JoinColumn(name = "id_funcionario")
    )
    @Builder.Default
    private Set<Funcionario> funcionarios = new LinkedHashSet<>();

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy proxy ? proxy.getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy proxy ? proxy.getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        CentroProducao that = (CentroProducao) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy proxy ? proxy.getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
