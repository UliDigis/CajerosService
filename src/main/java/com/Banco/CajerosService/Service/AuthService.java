package com.Banco.CajerosService.Service;

import com.Banco.CajerosService.DAO.IUsuarioRepository;
import com.Banco.CajerosService.JPA.UsuarioJPA;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final IUsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(IUsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UsuarioJPA validateCredentials(String correo, String password) {
        UsuarioJPA user = usuarioRepository.findByCorreo(correo)
                .orElseThrow(() -> new RuntimeException("Credenciales inválidas"));

        if (!Boolean.TRUE.equals(user.getEstado())) {
            throw new RuntimeException("Usuario inactivo");
        }

        if (!passwordEncoder.matches(password, user.getPasswordHash())) {
            throw new RuntimeException("Credenciales inválidas");
        }

        return user;
    }
}
