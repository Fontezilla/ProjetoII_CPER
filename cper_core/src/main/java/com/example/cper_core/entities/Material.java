package com.example.cper_core.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "material")
@ToString(onlyExplicitlyIncluded = true)
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "material_id_gen")
    @SequenceGenerator(name = "material_id_gen", sequenceName = "material_id_material_seq", allocationSize = 1)
    @EqualsAndHashCode.Include
    @ToString.Include
    @Column(name = "id_material", nullable = false)
    private Integer id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "descricao", columnDefinition = "TEXT")
    private String descricao;

    @Column(name = "categoria")
    private String categoria;

    @Column(name = "uni_medida_peso", nullable = false)
    private Integer uniMedidaPeso;

    @Column(name = "uni_medida_volume", nullable = false)
    private Integer uniMedidaVolume;

    @Column(name = "custo_uni", precision = 20, scale = 2)
    private BigDecimal custoUni;

    @Column(name = "peso", precision = 20, scale = 2)
    private BigDecimal peso;

    @Column(name = "volume", precision = 20, scale = 2)
    private BigDecimal volume;

    @Column(name = "codigo")
    private String codigo;

    @Column(name = "is_deleted")
    @Builder.Default
    private Boolean isDeleted = false;

    // Relacionamentos

    @OneToMany(mappedBy = "material")
    @Builder.Default
    private Set<MaterialSolicitacaoMaterial> materiaisSolicitacaoMateriais = new LinkedHashSet<>();

    @OneToMany(mappedBy = "material")
    @Builder.Default
    private Set<MaterialPedidoMaterial> materiaisPedidoMateriais = new LinkedHashSet<>();

    @OneToMany(mappedBy = "material")
    @Builder.Default
    private Set<Lote> lotes = new LinkedHashSet<>();

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
        Material material = (Material) o;
        return getId() != null && Objects.equals(getId(), material.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy proxy
                ? proxy.getHibernateLazyInitializer().getPersistentClass().hashCode()
                : getClass().hashCode();
    }
}
