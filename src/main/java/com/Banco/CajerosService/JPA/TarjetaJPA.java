package com.Banco.CajerosService.JPA;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * Entidad de Tarjeta Bancaria
 * Card Entity - Tarjeta con NIP encriptado
 */
@Entity
@Table(name = "TARJETA")
public class TarjetaJPA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TARJETA", nullable = false)
    private Long idTarjeta;

    @Column(name = "NUMERO_TARJETA", nullable = false, length = 8)
    private String numeroTarjeta;  // String de 8 dígitos (ej: "90000001")

    @Column(name = "NIP_HASH", nullable = false)
    private String nipHash;  // String con hash Bcrypt

    @Column(name = "ESTADO", nullable = false)
    private Integer estado;  // 0 = inactivo, 1 = activo

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_CUENTA", nullable = false)
    private CuentaBancariaJPA cuenta;

    // Constructores
    public TarjetaJPA() {
    }

    public TarjetaJPA(String numeroTarjeta, String nipHash, Integer estado, CuentaBancariaJPA cuenta) {
        this.numeroTarjeta = numeroTarjeta;
        this.nipHash = nipHash;
        this.estado = estado;
        this.cuenta = cuenta;
    }

    // Getters y Setters
    public Long getIdTarjeta() {
        return idTarjeta;
    }

    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public void setNumeroTarjeta(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    public String getNipHash() {
        return nipHash;
    }

    public void setNipHash(String nipHash) {
        this.nipHash = nipHash;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public CuentaBancariaJPA getCuenta() {
        return cuenta;
    }

    public void setCuenta(CuentaBancariaJPA cuenta) {
        this.cuenta = cuenta;
    }
}
