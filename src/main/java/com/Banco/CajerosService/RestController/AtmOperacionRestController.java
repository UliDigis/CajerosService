package com.Banco.CajerosService.RestController;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Banco.CajerosService.DTO.ApiRequest;
import com.Banco.CajerosService.DTO.ApiResponse;
import com.Banco.CajerosService.Service.AtmOperacionService;

@RestController
@RequestMapping("/atm")
public class AtmOperacionRestController {

    private final AtmOperacionService atmOperacionService;

    public AtmOperacionRestController(AtmOperacionService atmOperacionService) {
        this.atmOperacionService = atmOperacionService;
    }

    @PostMapping("/autenticar")
    public ResponseEntity<ApiResponse> autenticar(@RequestBody ApiRequest request) {
        return ResponseEntity.ok(atmOperacionService.autenticar(request));
    }

    @GetMapping("/saldo/{idCuenta}")
    public ResponseEntity<ApiResponse> saldo(@PathVariable Long idCuenta) {
        return ResponseEntity.ok(atmOperacionService.saldo(idCuenta));
    }

    @PostMapping("/retirar")
    public ResponseEntity<ApiResponse> retirar(@RequestBody ApiRequest request) {
        return ResponseEntity.ok(atmOperacionService.retirar(request));
    }
}
