package com.Banco.CajerosService.JPA;

import jakarta.persistence.*;

@Entity
@Table(name = "CUENTA_BANCARIA")
public class CuentaBancariaJPA {

    @Id
    @Column(name = "ID_CUENTA")
    private Long idCuenta;

    @Column(name = "NUMERO_CUENTA")
    private String numeroCuenta;

    // Si tu BD guarda NIP en cuenta, usa este campo:
    // - si es hash, nómbralo NIP_HASH
    // - si es texto, igual aquí cae
    @Column(name = "NIP_HASH")
    private String nipHash;

    @Column(name = "ESTADO")
    private Integer estado; 

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_USUARIO")
    private UsuarioJPA usuario;

    public Long getIdCuenta() {
        return idCuenta;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public String getNipHash() {
        return nipHash;
    }

    public Integer getEstado() {
        return estado;
    }

    public UsuarioJPA getUsuario() {
        return usuario;
    }

    public void setIdCuenta(Long idCuenta) {
        this.idCuenta = idCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public void setNipHash(String nipHash) {
        this.nipHash = nipHash;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public void setUsuario(UsuarioJPA usuario) {
        this.usuario = usuario;
    }
}
