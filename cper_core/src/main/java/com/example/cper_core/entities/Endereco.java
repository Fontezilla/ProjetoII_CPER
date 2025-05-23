package com.example.cper_core.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "endereco")
@ToString(onlyExplicitlyIncluded = true)
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "endereco_id_gen")
    @SequenceGenerator(name = "endereco_id_gen", sequenceName = "endereco_id_endereco_seq", allocationSize = 1)
    @EqualsAndHashCode.Include
    @ToString.Include
    @Column(name = "id_endereco", nullable = false)
    private Integer id;

    @Column(name = "rua", nullable = false, length = 150)
    private String rua;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_localidade")
    private Localidade localidade;

    @OneToMany(mappedBy = "endereco")
    @Builder.Default
    private Set<Cliente> clientes = new LinkedHashSet<>();

    @OneToMany(mappedBy = "endereco")
    @Builder.Default
    private Set<Funcionario> funcionarios = new LinkedHashSet<>();

    @OneToMany(mappedBy = "endereco")
    @Builder.Default
    private Set<Armazem> armazens = new LinkedHashSet<>();

    @OneToMany(mappedBy = "endereco")
    @Builder.Default
    private Set<CentroProducao> centrosProducao = new LinkedHashSet<>();

    @OneToMany(mappedBy = "endereco")
    @Builder.Default
    private Set<Contrato> contratos = new LinkedHashSet<>();

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy proxy ? proxy.getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy proxy ? proxy.getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Endereco endereco = (Endereco) o;
        return getId() != null && Objects.equals(getId(), endereco.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy proxy ? proxy.getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
