package com.Banco.CajerosService.JPA;

import jakarta.persistence.*;

@Entity
@Table(name = "cajero_efectivo")
public class CajeroEfectivoJPA {

    @EmbeddedId
    private CajeroEfectivoId id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("id_cajero") 
    @JoinColumn(name = "id_cajero", nullable = false)
    private CajeroJPA cajero;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("id_denominacion")  
    @JoinColumn(name = "id_denominacion", nullable = false)
    private DenominacionJPA denominacion;

    @Column(name = "cantidad", nullable = false)
    private Long cantidad;

    protected CajeroEfectivoJPA() {
    }

    public CajeroEfectivoJPA(CajeroJPA cajero, DenominacionJPA denominacion, Long cantidad) {
        this.cajero = cajero;
        this.denominacion = denominacion;
        this.cantidad = cantidad;
        this.id = new CajeroEfectivoId(cajero.getId_cajero(), denominacion.getId_denominacion());
    }

    public CajeroEfectivoId getId() {
        return id;
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
