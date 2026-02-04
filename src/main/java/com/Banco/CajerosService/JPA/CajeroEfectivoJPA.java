package com.Banco.CajerosService.JPA;

import jakarta.persistence.*;

/**
 * Entidad de Efectivo en Cajero
 * Cash Inventory in ATM - Junction entity
 */
@Entity
@Table(name = "CAJERO_EFECTIVO")
public class CajeroEfectivoJPA {

    @EmbeddedId
    private CajeroEfectivoId id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("idCajero")
    @JoinColumn(name = "ID_CAJERO", nullable = false)
    private CajeroJPA cajero;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("idDenominacion")
    @JoinColumn(name = "ID_DENOMINACION", nullable = false)
    private DenominacionJPA denominacion;

    @Column(name = "CANTIDAD", nullable = false)
    private Long cantidad;

    // Constructores
    public CajeroEfectivoJPA() {
    }

    public CajeroEfectivoJPA(CajeroJPA cajero, DenominacionJPA denominacion, Long cantidad) {
        this.cajero = cajero;
        this.denominacion = denominacion;
        this.cantidad = cantidad;
        this.id = new CajeroEfectivoId(
                cajero.getIdCajero(),
                denominacion.getIdDenominacion()
        );
    }

    // Getters y Setters
    public CajeroEfectivoId getId() {
        return id;
    }

    public void setId(CajeroEfectivoId id) {
        this.id = id;
    }

    public CajeroJPA getCajero() {
        return cajero;
    }

    public void setCajero(CajeroJPA cajero) {
        this.cajero = cajero;
    }

    public DenominacionJPA getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(DenominacionJPA denominacion) {
        this.denominacion = denominacion;
    }

    public Long getCantidad() {
        return cantidad;
    }

    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }
}
