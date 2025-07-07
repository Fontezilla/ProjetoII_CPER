package com.example.cper_core.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.time.OffsetDateTime;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "resposta")
@ToString(onlyExplicitlyIncluded = true)
public class Resposta {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "resposta_id_gen")
    @SequenceGenerator(name = "resposta_id_gen", sequenceName = "resposta_id_resposta_seq", allocationSize = 1)
    @EqualsAndHashCode.Include
    @ToString.Include
    @Column(name = "id_resposta", nullable = false)
    private Integer id;

    @Column(name = "resposta", nullable = false, columnDefinition = "TEXT")
    private String resposta;

    @Column(name = "data_resposta")
    private OffsetDateTime dataResposta;

    @Column(name = "is_cliente")
    @Builder.Default
    private Boolean isCliente = false;

    @Column(name = "codigo")
    private String codigo;

    @Column(name = "is_deleted")
    @Builder.Default
    private Boolean isDeleted = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ticket", nullable = false)
    private Ticket ticket;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_funcionario")
    private Funcionario funcionario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ?
                ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ?
                ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Resposta resposta = (Resposta) o;
        return getId() != null && Objects.equals(getId(), resposta.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ?
                ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
