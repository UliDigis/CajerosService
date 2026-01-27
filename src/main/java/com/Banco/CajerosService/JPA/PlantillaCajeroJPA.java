package com.Banco.CajerosService.JPA;

import jakarta.persistence.*;

@Entity
@Table(name = "platilla_carga_estandar")
public class PlantillaCajeroJPA {

    @Id
    @Column(name = "id_denominacion", nullable = false)
    private Integer idDenominacion;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId 
    @JoinColumn(name = "id_denominacion", nullable = false)
    private DenominacionJPA denominacion;

    @Column(name = "cantidad_estandar", nullable = false)
    private Integer cantidadEstandar;

    protected PlantillaCajeroJPA() {
    }

    public Integer getIdDenominacion() {
        return idDenominacion;
    }

    public DenominacionJPA getDenominacion() {
        return denominacion;
    }

    public Integer getCantidadEstandar() {
        return cantidadEstandar;
    }

    public void setCantidadEstandar(Integer cantidadEstandar) {
        this.cantidadEstandar = cantidadEstandar;
    }
}
