package com.example.cper_core.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "\"perfil consumo\"")
public class PerfilConsumo {

    // Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "perfil_consumo_id_gen")
    @SequenceGenerator(name = "perfil_consumo_id_gen", sequenceName = "perfil consumo_id_perfil_seq", allocationSize = 1)
    @Column(name = "id_perfil", nullable = false)
    private Integer id;

    @ColumnDefault("CURRENT_DATE")
    @Column(name = "data_registo")
    private LocalDate dataRegisto;

    @ColumnDefault("0")
    @Column(name = "qtd_consumida", precision = 20, scale = 2)
    private BigDecimal qtdConsumida;

    // Relationships

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    // Getters and Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDataRegisto() {
        return dataRegisto;
    }

    public void setDataRegisto(LocalDate dataRegisto) {
        this.dataRegisto = dataRegisto;
    }

    public BigDecimal getQtdConsumida() {
        return qtdConsumida;
    }

    public void setQtdConsumida(BigDecimal qtdConsumida) {
        this.qtdConsumida = qtdConsumida;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }


}