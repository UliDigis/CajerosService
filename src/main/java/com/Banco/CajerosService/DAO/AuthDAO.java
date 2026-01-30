package com.Banco.CajerosService.DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.Banco.CajerosService.JPA.UsuarioJPA;

@Repository
public class AuthDAO {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Busca un usuario activo por correo. Retorna null si no existe o está
     * inactivo.
     */
    public UsuarioJPA findUsuarioActivoByCorreo(String correo) {

        TypedQuery<UsuarioJPA> query = entityManager.createQuery(
                "SELECT u FROM UsuarioJPA u WHERE u.correo = :correo AND u.estado = 1",
                UsuarioJPA.class
        );

        query.setParameter("correo", correo);

        return query.getResultStream().findFirst().orElse(null);
    }
}
