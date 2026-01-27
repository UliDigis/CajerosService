package com.Banco.CajerosService.JPA;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CajeroEfectivoId implements Serializable {

    @Column(name = "id_cajero", nullable = false)
    private Integer idCajero;

    @Column(name = "id_denominacion", nullable = false)
    private Integer idDenominacion;

    public CajeroEfectivoId() {
    }

    public CajeroEfectivoId(Integer idCajero, Integer idDenominacion) {
        this.idCajero = idCajero;
        this.idDenominacion = idDenominacion;
    }

    public Integer getIdCajero() {
        return idCajero;
    }

    public Integer getIdDenominacion() {
        return idDenominacion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CajeroEfectivoId that)) {
            return false;
        }
        return Objects.equals(idCajero, that.idCajero)
                && Objects.equals(idDenominacion, that.idDenominacion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCajero, idDenominacion);
    }
}
