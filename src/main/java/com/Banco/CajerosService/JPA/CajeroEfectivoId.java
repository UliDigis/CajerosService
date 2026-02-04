package com.Banco.CajerosService.JPA;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**
 * ID Compuesto para CajeroEfectivo
 * Composite ID for CajeroEfectivo
 */
@Embeddable
public class CajeroEfectivoId implements Serializable {

    @Column(name = "ID_CAJERO", nullable = false)
    private Long idCajero;

    @Column(name = "ID_DENOMINACION", nullable = false)
    private Long idDenominacion;

    public CajeroEfectivoId() {
    }

    public CajeroEfectivoId(Long idCajero, Long idDenominacion) {
        this.idCajero = idCajero;
        this.idDenominacion = idDenominacion;
    }

    public Long getIdCajero() {
        return idCajero;
    }

    public Long getIdDenominacion() {
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
