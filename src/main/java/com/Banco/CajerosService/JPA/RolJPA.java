package com.Banco.CajerosService.JPA;

import jakarta.persistence.*;

/**
 * Entidad de Rol
 * Role Entity
 */
@Entity
@Table(name = "ROL")
public class RolJPA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ROL", nullable = false)
    private Long idRol;

    @Column(name = "NOMBRE_ROL", nullable = false, unique = true, length = 30)
    private String nombreRol;

    @Column(name = "ESTADO", nullable = false)
    private Integer estado;  // 0 = inactivo, 1 = activo

    // Constructores
    public RolJPA() {
    }

    public RolJPA(String nombreRol, Integer estado) {
        this.nombreRol = nombreRol;
        this.estado = estado;
    }

    // Getters y Setters
    public Long getIdRol() {
        return idRol;
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }
}
