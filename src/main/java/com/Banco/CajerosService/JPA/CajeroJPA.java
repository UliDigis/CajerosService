package com.Banco.CajerosService.JPA;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "cajero")
public class CajeroJPA {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cajero", nullable = false)
    private Integer id_cajero;
    
    @Column(name = "codigo_cajero", nullable = false)
    private String codigo_cajero;
    
    @Column(name = "estado", nullable = false)
    private Boolean estado;

    public Integer getId_cajero() {
        return id_cajero;
    }
    
    public String getCodigo_cajero() {
        return codigo_cajero;
    }

    public void setCodigo_cajero(String codigo_cajero) {
        this.codigo_cajero = codigo_cajero;
    }

    public Boolean isEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
    
    
    
}
