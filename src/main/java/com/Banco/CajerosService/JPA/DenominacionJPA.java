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
    private Integer id_denominacion;

    @Column(name = "valor_centavos")
    private Integer valor_centavo;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "estado")
    private Boolean estado;

    public Integer getId_denominacion() {
        return id_denominacion;
    }

    public Integer getValor_centavo() {
        return valor_centavo;
    }

    public void setValor_centavo(Integer valor_centavo) {
        this.valor_centavo = valor_centavo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Boolean isEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

}
