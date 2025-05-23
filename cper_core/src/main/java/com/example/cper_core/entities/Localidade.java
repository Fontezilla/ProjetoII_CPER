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
@Table(name = "localidade")
@ToString(onlyExplicitlyIncluded = true)
public class Localidade {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "localidade_id_gen")
    @SequenceGenerator(name = "localidade_id_gen", sequenceName = "localidade_id_localidade_seq", allocationSize = 1)
    @EqualsAndHashCode.Include
    @ToString.Include
    @Column(name = "id_localidade", nullable = false)
    private Integer id;

    @Column(name = "nome", length = 100, nullable = false)
    private String nome;

    @Column(name = "codigo_postal", length = 20, nullable = false)
    private String codigoPostal;

    @Column(name = "distrito", length = 100, nullable = false)
    private String distrito;

    @Column(name = "pais", length = 100, nullable = false)
    private String pais;

    @OneToMany(mappedBy = "localidade")
    @Builder.Default
    private Set<Endereco> enderecos = new LinkedHashSet<>();

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy proxy
                ? proxy.getHibernateLazyInitializer().getPersistentClass()
                : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy proxy
                ? proxy.getHibernateLazyInitializer().getPersistentClass()
                : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Localidade that = (Localidade) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy proxy
                ? proxy.getHibernateLazyInitializer().getPersistentClass().hashCode()
                : getClass().hashCode();
    }
}
