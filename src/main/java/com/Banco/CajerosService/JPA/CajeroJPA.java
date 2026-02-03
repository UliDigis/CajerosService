package com.Banco.CajerosService.JPA;

import jakarta.persistence.*;

@Entity
@Table(name = "CAJERO")
public class CajeroJPA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CAJERO", nullable = false)
    private Integer id_cajero;

    @Column(name = "CODIGO_CAJERO", nullable = false, length = 30)
    private String codigo_cajero;

    @Column(name = "ESTADO", nullable = false)
    private Integer estado;

    public Integer getId_cajero() {
        return id_cajero;
    }

    public String getCodigo_cajero() {
        return codigo_cajero;
    }

    public void setCodigo_cajero(String codigo_cajero) {
        this.codigo_cajero = codigo_cajero;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }
}
