package com.example.cper_core.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "notificacao_destinatario")
@ToString(onlyExplicitlyIncluded = true)
public class NotificacaoDestinatario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @EqualsAndHashCode.Include
    @ToString.Include
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_notificacao", nullable = false)
    private Notificacao notificacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_funcionario")
    private Funcionario funcionario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @Column(name = "lida")
    @Builder.Default
    private Boolean lida = false;

    @PrePersist
    @PreUpdate
    private void validarDestinatario() {
        boolean funcionarioPresente = funcionario != null;
        boolean clientePresente = cliente != null;

        if (funcionarioPresente == clientePresente) {
            throw new IllegalStateException("O destinatário deve ser um funcionário OU um cliente, não ambos ou nenhum.");
        }
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ?
                ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ?
                ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        NotificacaoDestinatario that = (NotificacaoDestinatario) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ?
                ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
