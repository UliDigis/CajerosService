package com.Banco.CajerosService.JPA;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "denominacion")
public class DenominacionJPA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_denominacion")
    private int id_denominacion;

    @Column(name = "valor_centavos")
    private int valor_centavo;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "estado")
    private boolean estado;

    public int getId_denominacion() {
        return id_denominacion;
    }

    public int getValor_centavo() {
        return valor_centavo;
    }

    public void setValor_centavo(int valor_centavo) {
        this.valor_centavo = valor_centavo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

}
