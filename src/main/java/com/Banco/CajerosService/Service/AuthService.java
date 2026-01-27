package com.Banco.CajerosService.Service;

import com.Banco.CajerosService.DTO.*;
import com.Banco.CajerosService.JPA.CuentaBancariaJPA;
import com.Banco.CajerosService.JPA.UsuarioJPA;
import com.Banco.CajerosService.Repository.TarjetaAuthRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.Banco.CajerosService.Repository.ICuentaBancariaRepository;

@Service
public class AuthService {

    private final JwtService jwtService;
    private final TarjetaAuthRepository tarjetaAuthRepository;
    private final ICuentaBancariaRepository cuentaRepo;
    private final PasswordEncoder passwordEncoder;
    private final long expirationMinutes;

    public AuthService(JwtService jwtService,
            TarjetaAuthRepository tarjetaAuthRepository,
            ICuentaBancariaRepository cuentaRepo,
            PasswordEncoder passwordEncoder,
            @Value("${security.jwt.expiration-minutes}") long expirationMinutes) {
        this.jwtService = jwtService;
        this.tarjetaAuthRepository = tarjetaAuthRepository;
        this.cuentaRepo = cuentaRepo;
        this.passwordEncoder = passwordEncoder;
        this.expirationMinutes = expirationMinutes;
    }

    public LoginResponse loginByEmail(LoginEmailRequest request) {
        throw new RuntimeException("Falta implementar loginByEmail");
    }

    public LoginResponse loginByTarjeta(LoginTarjetaRequest request) {
        TarjetaAuthResult r = tarjetaAuthRepository.autenticarTarjeta(
                request.getNumeroTarjeta(),
                request.getNip()
        );

        String token = jwtService.generateToken(r.getUsurioId(), r.getRolNombre(), r.getCuentaId());
        return new LoginResponse(token, expirationMinutes * 60, r.getUsurioId(), r.getCuentaId(), r.getRolNombre());
    }

    public LoginResponse loginByCuenta(LoginCuentaRequest request) {
        CuentaBancariaJPA cuenta = cuentaRepo.findByNumeroCuenta(request.getNumeroCuenta())
                .orElseThrow(() -> new RuntimeException("Cuenta no existe"));

        if (cuenta.getEstado() == null) {
            throw new RuntimeException("Cuenta inactiva");
        }

        UsuarioJPA usuario = cuenta.getUsuario();
        if (usuario == null) {
            throw new RuntimeException("Cuenta sin usuario");
        }
        if (usuario.getEstado() == null || !usuario.getEstado().equalsIgnoreCase("ACTIVO")) {
            throw new RuntimeException("Usuario inactivo");
        }

        boolean nipOk = passwordEncoder.matches(request.getNip(), cuenta.getNipHash());

        if (!nipOk) {
            throw new RuntimeException("NIP incorrecto");
        }

        String role = usuario.getRol() != null ? usuario.getRol().getNombreRol() : "CLIENTE";

        String token = jwtService.generateToken(usuario.getIdUsuario(), role, cuenta.getIdCuenta());
        return new LoginResponse(token, expirationMinutes * 60, usuario.getIdUsuario(), cuenta.getIdCuenta(), role);
    }
}
