package com.example.cper_core.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "nota")
@ToString(onlyExplicitlyIncluded = true)
public class Nota {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "nota_id_gen")
    @SequenceGenerator(name = "nota_id_gen", sequenceName = "nota_id_nota_seq", allocationSize = 1)
    @EqualsAndHashCode.Include
    @ToString.Include
    @Column(name = "id_nota", nullable = false)
    private Integer id;

    @Column(name = "titulo", length = 255)
    private String titulo;

    @Column(name = "descricao", columnDefinition = "TEXT")
    private String descricao;

    @Column(name = "data_criacao", nullable = false)
    @ColumnDefault("CURRENT_DATE")
    private OffsetDateTime dataCriacao;

    @Column(name = "prioridade", nullable = false)
    @ColumnDefault("1")
    private Integer prioridade;

    @Column(name = "codigo")
    private String codigo;

    @ColumnDefault("false")
    @Column(name = "is_deleted")
    @Builder.Default
    private Boolean isDeleted = false;

    // Relationships

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_inspecao")
    private Inspecao inspecao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_anomalia")
    private Anomalia anomalia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_avaria")
    private Avaria avaria;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_solicitacao")
    private SolicitacaoEnergetica solicitacaoEnergetica;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;

        Class<?> thisClass = this instanceof HibernateProxy
                ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass()
                : getClass();

        Class<?> otherClass = o instanceof HibernateProxy
                ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass()
                : o.getClass();

        if (!thisClass.equals(otherClass)) return false;
        Nota nota = (Nota) o;
        return getId() != null && Objects.equals(getId(), nota.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy
                ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode()
                : getClass().hashCode();
    }
}