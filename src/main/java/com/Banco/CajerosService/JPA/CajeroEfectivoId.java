package com.Banco.CajerosService.JPA;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CajeroEfectivoId implements Serializable {

    @Column(name = "id_cajero", nullable = false)
    private Integer id_cajero;

    @Column(name = "id_denominacion", nullable = false)
    private Integer id_denominacion;

    public CajeroEfectivoId() {
    }

    public CajeroEfectivoId(Integer idCajero, Integer idDenominacion) {
        this.id_cajero = idCajero;
        this.id_denominacion = idDenominacion;
    }

    public Integer getIdCajero() {
        return id_cajero;
    }

    public Integer getIdDenominacion() {
        return id_denominacion;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof CajeroEfectivoId)) {
            return false;
        }
        CajeroEfectivoId that = (CajeroEfectivoId) object;
        return Objects.equals(id_cajero, that.id_cajero)
                && Objects.equals(id_denominacion, that.id_denominacion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_cajero, id_denominacion);
    }
}
