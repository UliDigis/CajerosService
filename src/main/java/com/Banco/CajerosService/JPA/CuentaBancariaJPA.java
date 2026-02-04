package com.Banco.CajerosService.JPA;

import jakarta.persistence.*;

/**
 * Entidad de Cuenta Bancaria
 * Bank Account Entity
 */
@Entity
@Table(name = "CUENTA_BANCARIA")
public class CuentaBancariaJPA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CUENTA", nullable = false)
    private Long idCuenta;

    @Column(name = "NUMERO_CUENTA", nullable = false, unique = true, length = 30)
    private String numeroCuenta;

    @Column(name = "SALDO_CENTAVOS", nullable = false)
    private Long saldoCentavos;  // Long en centavos (NO Double)

    @Column(name = "ESTADO", nullable = false)
    private Integer estado;  // 0 = inactivo, 1 = activo

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_USUARIO", nullable = false)
    private UsuarioJPA usuario;

    // Constructores
    public CuentaBancariaJPA() {
    }

    public CuentaBancariaJPA(String numeroCuenta, Long saldoCentavos, Integer estado, UsuarioJPA usuario) {
        this.numeroCuenta = numeroCuenta;
        this.saldoCentavos = saldoCentavos;
        this.estado = estado;
        this.usuario = usuario;
    }

    // Getters y Setters
    public Long getIdCuenta() {
        return idCuenta;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public Long getSaldoCentavos() {
        return saldoCentavos;
    }

    public void setSaldoCentavos(Long saldoCentavos) {
        this.saldoCentavos = saldoCentavos;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public UsuarioJPA getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioJPA usuario) {
        this.usuario = usuario;
    }
}
