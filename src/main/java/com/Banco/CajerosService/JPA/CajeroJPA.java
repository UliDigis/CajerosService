package com.Banco.CajerosService.JPA;

import jakarta.persistence.*;

/**
 * Entidad de Cajero (ATM)
 * ATM Entity
 */
@Entity
@Table(name = "CAJERO")
public class CajeroJPA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CAJERO", nullable = false)
    private Long idCajero;  // Long, NO Integer

    @Column(name = "CODIGO_CAJERO", nullable = false, unique = true, length = 30)
    private String codigoCajero;

    @Column(name = "ESTADO", nullable = false)
    private Integer estado;  // 0 = inactivo, 1 = activo

    // Constructores
    public CajeroJPA() {
    }

    public CajeroJPA(String codigoCajero, Integer estado) {
        this.codigoCajero = codigoCajero;
        this.estado = estado;
    }

    // Getters y Setters
    public Long getIdCajero() {
        return idCajero;
    }

    public String getCodigoCajero() {
        return codigoCajero;
    }

    public void setCodigoCajero(String codigoCajero) {
        this.codigoCajero = codigoCajero;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }
}
