package com.Banco.CajerosService.JPA;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "platilla_carga_estandar")
public class PlantillaCajeroJPA {

    @Column(name = "cantidad_estandar", nullable = false)
    private int cantidad_estandar;

    @OneToOne(optional = false)
    @JoinColumn(name = "id_denominacion", nullable = false)
    private DenominacionJPA id_denominacion;

    public int getCantidad_estandar() {
        return cantidad_estandar;
    }

    public void setCantidad_estandar(int cantidad_estandar) {
        this.cantidad_estandar = cantidad_estandar;
    }

    public DenominacionJPA getId_denominacion() {
        return id_denominacion;
    }
}
