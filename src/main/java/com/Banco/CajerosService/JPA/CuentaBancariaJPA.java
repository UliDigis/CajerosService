package com.Banco.CajerosService.JPA;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "cuenta_bancaria")
public class CuentaBancariaJPA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cuenta", nullable = false)
    private int id_cuenta;

    @Column(name = "numero_cuneta", nullable = false)
    private String nombre_cuenta;

    @Column(name = "saldo_centavos", nullable = false)
    private int saldo_cuenta;

    @Column(name = "estado", nullable = false)
    private boolean estado;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_usuario", nullable = false)
    private UsuarioJPA id_usuario;

    public int getId_cuenta() {
        return id_cuenta;
    }

    public String getNombre_cuenta() {
        return nombre_cuenta;
    }

    public void setNombre_cuenta(String nombre_cuenta) {
        this.nombre_cuenta = nombre_cuenta;
    }

    public int getSaldo_cuenta() {
        return saldo_cuenta;
    }

    public void setSaldo_cuenta(int saldo_cuenta) {
        this.saldo_cuenta = saldo_cuenta;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public UsuarioJPA getId_usuario() {
        return id_usuario;
    }
}
