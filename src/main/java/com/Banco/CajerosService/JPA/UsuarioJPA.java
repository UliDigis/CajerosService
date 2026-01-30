package com.Banco.CajerosService.JPA;

import jakarta.persistence.*;

@Entity
@Table(name = "USUARIO")
public class UsuarioJPA {

    @Id
    @Column(name = "ID_USUARIO")
    private Long idUsuario;

    @Column(name = "CORREO", nullable = false)
    private String correo;

    @Column(name = "PASSWORD_HASH", nullable = false)
    private String passwordHash;

    @Column(name = "ESTADO", nullable = false)
    private Integer estado;

    @ManyToOne
    @JoinColumn(name = "ID_ROL", nullable = false)
    private RolJPA rol;

    public Long getIdUsuario() {
        return idUsuario;
    }

    public String getCorreo() {
        return correo;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public Integer getEstado() {
        return estado;
    }

    public RolJPA getRol() {
        return rol;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public void setRol(RolJPA rol) {
        this.rol = rol;
    }
}
