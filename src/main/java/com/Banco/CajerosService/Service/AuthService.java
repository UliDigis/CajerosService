package com.Banco.CajerosService.Service;

import java.util.Map;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Banco.CajerosService.DAO.AuthDAO;
import com.Banco.CajerosService.DTO.ApiRequest;
import com.Banco.CajerosService.DTO.ApiResponse;
import com.Banco.CajerosService.JPA.UsuarioJPA;

@Service
public class AuthService {

    private final AuthDAO authDAO;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public AuthService(AuthDAO authDAO,
            JwtService jwtService,
            PasswordEncoder passwordEncoder) {
        this.authDAO = authDAO;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Autenticación de sistema usando correo y contraseña. Genera un JWT de
     * sistema con información de usuario y rol.
     */
    public ApiResponse loginSistema(ApiRequest request) {

        Map<String, Object> data = request.getData();

        String correo = (String) data.get("correo");
        String password = (String) data.get("password");

        if (correo == null || password == null) {
            throw new RuntimeException("Parámetros requeridos");
        }

        UsuarioJPA usuario = authDAO.findUsuarioActivoByCorreo(correo);

        if (usuario == null) {
            throw new RuntimeException("Credenciales inválidas");
        }

        if (!passwordEncoder.matches(password, usuario.getPasswordHash())) {
            throw new RuntimeException("Credenciales inválidas");
        }

        String token = jwtService.generateToken(
                usuario.getIdUsuario(),
                usuario.getRol().getNombreRol(),
                null
        );

        return ApiResponse.ok(Map.of("token", token));
    }
}
