package com.Banco.CajerosService.JPA;

import jakarta.persistence.*;

/**
 * Entidad de Usuario
 * User Entity
 */
@Entity
@Table(name = "USUARIO")
public class UsuarioJPA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_USUARIO", nullable = false)
    private Long idUsuario;

    @Column(name = "NOMBRES", nullable = false, length = 80)
    private String nombres;

    @Column(name = "APELLIDOS", nullable = false, length = 120)
    private String apellidos;

    @Column(name = "CORREO", nullable = false, unique = true, length = 200)
    private String correo;

    @Column(name = "PASSWORD_HASH", nullable = false)
    private String passwordHash;

    @Column(name = "ESTADO", nullable = false)
    private Integer estado;  // 0 = inactivo, 1 = activo

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_ROL", nullable = false)
    private RolJPA rol;

    // Constructores
    public UsuarioJPA() {
    }

    public UsuarioJPA(String nombres, String apellidos, String correo, String passwordHash, Integer estado, RolJPA rol) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.correo = correo;
        this.passwordHash = passwordHash;
        this.estado = estado;
        this.rol = rol;
    }

    // Getters y Setters
    public Long getIdUsuario() {
        return idUsuario;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public RolJPA getRol() {
        return rol;
    }

    public void setRol(RolJPA rol) {
        this.rol = rol;
    }
}
