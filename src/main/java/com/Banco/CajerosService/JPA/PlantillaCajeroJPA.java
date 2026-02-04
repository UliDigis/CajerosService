package com.Banco.CajerosService.JPA;

import jakarta.persistence.*;

/**
 * Entidad de Plantilla de Carga Estándar
 * Standard Loading Template Entity
 */
@Entity
@Table(name = "PLANTILLA_CARGA_ESTANDAR")
public class PlantillaCajeroJPA {

    @Id
    @Column(name = "ID_DENOMINACION", nullable = false)
    private Long idDenominacion;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId
    @JoinColumn(name = "ID_DENOMINACION", nullable = false)
    private DenominacionJPA denominacion;

    @Column(name = "CANTIDAD_ESTANDAR", nullable = false)
    private Long cantidadEstandar;  // Long, NO Integer

    // Constructores
    protected PlantillaCajeroJPA() {
    }

    public PlantillaCajeroJPA(DenominacionJPA denominacion, Long cantidadEstandar) {
        this.denominacion = denominacion;
        this.cantidadEstandar = cantidadEstandar;
        this.idDenominacion = denominacion.getIdDenominacion();
    }

    // Getters y Setters
    public Long getIdDenominacion() {
        return idDenominacion;
    }

    public DenominacionJPA getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(DenominacionJPA denominacion) {
        this.denominacion = denominacion;
    }

    public Long getCantidadEstandar() {
        return cantidadEstandar;
    }

    public void setCantidadEstandar(Long cantidadEstandar) {
        this.cantidadEstandar = cantidadEstandar;
    }
}
