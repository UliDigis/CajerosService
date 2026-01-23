package com.Banco.CajerosService.DAO;

import com.Banco.CajerosService.JPA.UsuarioJPA;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUsuarioRepository extends JpaRepository<UsuarioJPA, Integer> {

    Optional<UsuarioJPA> findByCorreo(String correo);
}
