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
@Table(name = "departamento")
@ToString(onlyExplicitlyIncluded = true)
public class Departamento {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "departamento_id_gen")
    @SequenceGenerator(name = "departamento_id_gen", sequenceName = "departamento_id_departamento_seq", allocationSize = 1)
    @EqualsAndHashCode.Include
    @ToString.Include
    @Column(name = "id_departamento", nullable = false)
    private Integer id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "setor")
    private Integer setor;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "data_criacao")
    private OffsetDateTime dataCriacao;

    @Column(name = "num_funcionarios")
    private Integer numFuncionarios;

    @Column(name = "orcamento")
    private BigDecimal orcamento;

    @Column(name = "is_deleted")
    @Builder.Default
    private Boolean isDeleted = false;

    @OneToMany(mappedBy = "departamento")
    @Builder.Default
    private Set<CentroProducao> centrosProducao = new LinkedHashSet<>();

    @OneToMany(mappedBy = "departamento")
    @Builder.Default
    private Set<Equipa> equipas = new LinkedHashSet<>();

    @OneToMany(mappedBy = "departamento")
    @Builder.Default
    private Set<Funcionario> funcionarios = new LinkedHashSet<>();

    @OneToMany(mappedBy = "departamento")
    @Builder.Default
    private Set<Armazem> armazens = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(
            name = "funcionario_departamento",
            joinColumns = @JoinColumn(name = "id_departamento"),
            inverseJoinColumns = @JoinColumn(name = "id_funcionario")
    )
    @Builder.Default
    private Set<Funcionario> responsaveis = new LinkedHashSet<>();

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy proxy ? proxy.getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy proxy ? proxy.getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Departamento that = (Departamento) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy proxy ? proxy.getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
