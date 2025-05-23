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
@Table(name = "solicitacao_material")
@ToString(onlyExplicitlyIncluded = true)
public class SolicitacaoMaterial {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "solicitacao_material_id_gen")
    @SequenceGenerator(name = "solicitacao_material_id_gen", sequenceName = "solicitacao material_id_solicitacao_seq", allocationSize = 1)
    @EqualsAndHashCode.Include
    @ToString.Include
    @Column(name = "id_solicitacao", nullable = false)
    private Integer id;

    @Column(name = "data_pedido")
    private OffsetDateTime dataPedido;

    @Column(name = "descricao", columnDefinition = "TEXT")
    private String descricao;

    @Column(name = "estado")
    private Integer estado;

    @Column(name = "is_deleted")
    @Builder.Default
    private Boolean isDeleted = false;

    @OneToMany(mappedBy = "solicitacaoMaterial")
    @Builder.Default
    private Set<MaterialSolicitacaoMaterial> materialSolicitacoes = new LinkedHashSet<>();

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ?
                ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ?
                ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        SolicitacaoMaterial that = (SolicitacaoMaterial) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ?
                ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
