package com.example.cper_core.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

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
@Table(name = "equipa")
@ToString(onlyExplicitlyIncluded = true)
public class Equipa {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "equipa_id_gen")
    @SequenceGenerator(name = "equipa_id_gen", sequenceName = "equipa_id_equipa_seq", allocationSize = 1)
    @EqualsAndHashCode.Include
    @ToString.Include
    @Column(name = "id_equipa", nullable = false)
    private Integer id;

    @Column(name = "nome", nullable = false, length = 256)
    private String nome;

    @Column(name = "data_criacao", nullable = false)
    private OffsetDateTime dataCriacao;

    @Column(name = "area_atuacao", length = 256)
    private String areaAtuacao;

    @Column(name = "is_deleted")
    @Builder.Default
    private Boolean isDeleted = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_departamento")
    private Departamento departamento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_funcionario")
    private Funcionario funcionario;

    @ManyToMany
    @JoinTable(
            name = "funcionario_equipa",
            joinColumns = @JoinColumn(name = "id_equipa"),
            inverseJoinColumns = @JoinColumn(name = "id_funcionario")
    )
    @Builder.Default
    private Set<Funcionario> funcionarios = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(
            name = "equipa_avaria",
            joinColumns = @JoinColumn(name = "id_equipa"),
            inverseJoinColumns = @JoinColumn(name = "id_avaria")
    )
    @Builder.Default
    private Set<Avaria> avarias = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(
            name = "equipa_inspecao",
            joinColumns = @JoinColumn(name = "id_equipa"),
            inverseJoinColumns = @JoinColumn(name = "id_inspecao")
    )
    @Builder.Default
    private Set<Inspecao> inspecoes = new LinkedHashSet<>();

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy proxy ? proxy.getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy proxy ? proxy.getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Equipa equipa = (Equipa) o;
        return getId() != null && Objects.equals(getId(), equipa.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy proxy ? proxy.getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
