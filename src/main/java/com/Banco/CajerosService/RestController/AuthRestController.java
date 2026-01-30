package com.Banco.CajerosService.RestController;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Banco.CajerosService.DTO.ApiRequest;
import com.Banco.CajerosService.DTO.ApiResponse;
import com.Banco.CajerosService.Service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthRestController {

    private final AuthService authService;

    public AuthRestController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * Login de sistema (correo y contraseña).
     */
    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@RequestBody ApiRequest request) {
        return ResponseEntity.ok(authService.loginSistema(request));
    }
}
