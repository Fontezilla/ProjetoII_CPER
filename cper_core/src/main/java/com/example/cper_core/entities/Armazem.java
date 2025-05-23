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
@Table(name = "armazem")
@ToString(onlyExplicitlyIncluded = true)
public class Armazem {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "armazem_id_gen")
    @SequenceGenerator(name = "armazem_id_gen", sequenceName = "armazem_id_armazem_seq", allocationSize = 1)
    @EqualsAndHashCode.Include
    @ToString.Include
    @Column(name = "id_armazem", nullable = false)
    private Integer id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "data_criacao")
    private OffsetDateTime dataCriacao;

    @Column(name = "data_update")
    private OffsetDateTime dataUpdate;

    @Column(name = "capacidade_total")
    private BigDecimal capacidadeTotal;

    @Column(name = "capacidade_ocupada")
    private BigDecimal capacidadeOcupada;

    @Column(name = "n_porta")
    private String nPorta;

    @Column(name = "estado")
    private Integer estado;

    @Column(name = "is_deleted")
    @Builder.Default
    private Boolean isDeleted = false;

    // --- Relationships ---

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_departamento")
    private Departamento departamento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_endereco")
    private Endereco endereco;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_funcionario")
    private Funcionario responsavel;

    @OneToMany(mappedBy = "armazem")
    @Builder.Default
    private Set<ArmazemLote> lotes = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(
            name = "funcionario_armazem",
            joinColumns = @JoinColumn(name = "id_armazem"),
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
        Armazem armazem = (Armazem) o;
        return getId() != null && Objects.equals(getId(), armazem.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy proxy ? proxy.getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}