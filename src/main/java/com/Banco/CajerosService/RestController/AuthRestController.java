package com.Banco.CajerosService.RestController;

import com.Banco.CajerosService.DTO.LoginCuentaRequest;
import com.Banco.CajerosService.DTO.LoginEmailRequest;
import com.Banco.CajerosService.DTO.LoginResponse;
import com.Banco.CajerosService.DTO.LoginTarjetaRequest;
import com.Banco.CajerosService.Service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthRestController {

    private final AuthService authService;

    public AuthRestController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login/cuenta")
    public ResponseEntity<LoginResponse> loginCuenta(@Valid @RequestBody LoginCuentaRequest request) {
        return ResponseEntity.ok(authService.loginByCuenta(request));
    }

    @PostMapping("/login/email")
    public ResponseEntity<LoginResponse> loginEmail(@Valid @RequestBody LoginEmailRequest request) {
        return ResponseEntity.ok(authService.loginByEmail(request));
    }

    @PostMapping("/login/tarjeta")
    public ResponseEntity<LoginResponse> loginTarjeta(@Valid @RequestBody LoginTarjetaRequest request) {
        return ResponseEntity.ok(authService.loginByTarjeta(request));
    }
}
