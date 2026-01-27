package com.Banco.CajerosService.JPA;

import jakarta.persistence.*;

@Entity
@Table(name = "ROL")
public class RolJPA {

    @Id
    @Column(name = "ID_ROL")
    private Long idRol;

    @Column(name = "NOMBRE_ROL")
    private String nombreRol;

    public Long getIdRol() {
        return idRol;
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public void setIdRol(Long idRol) {
        this.idRol = idRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }
}
