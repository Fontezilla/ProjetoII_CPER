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
@Table(name = "notificacao")
@ToString(onlyExplicitlyIncluded = true)
public class Notificacao {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "notificacao_id_gen")
    @SequenceGenerator(name = "notificacao_id_gen", sequenceName = "notificacao_id_notificacao_seq", allocationSize = 1)
    @EqualsAndHashCode.Include
    @ToString.Include
    @Column(name = "id_notificacao", nullable = false)
    private Integer id;

    @Column(name = "titulo", nullable = false)
    private String titulo;

    @Column(name = "mensagem", nullable = false, columnDefinition = "TEXT")
    private String mensagem;

    @Column(name = "data_criacao")
    private OffsetDateTime dataCriacao;

    @Column(name = "tipo_destinatario", nullable = false, length = 20)
    private String tipoDestinatario;

    @Column(name = "is_deleted")
    @Builder.Default
    private Boolean isDeleted = false;

    @OneToMany(mappedBy = "notificacao")
    @Builder.Default
    private Set<NotificacaoDestinatario> notificacaoUsuarios = new LinkedHashSet<>();

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ?
                ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ?
                ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Notificacao that = (Notificacao) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ?
                ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}