package com.Banco.CajerosService.Controller;

import org.springframework.web.bind.annotation.*;

import com.Banco.CajerosService.DTO.ApiRequest;
import com.Banco.CajerosService.DTO.ApiResponse;
import com.Banco.CajerosService.Service.AtmOperacionService;

@RestController
@RequestMapping("/atm")
public class AtmRestController {

    private final AtmOperacionService atmService;

    public AtmRestController(AtmOperacionService atmService) {
        this.atmService = atmService;
    }

    /**
     * Autentica tarjeta y nip. Request esperado en ApiRequest.data: - tarjeta:
     * String - nip: String
     */
    @PostMapping("/autenticar")
    public ApiResponse autenticar(@RequestBody ApiRequest request) {
        return atmService.autenticar(request);
    }

    /**
     * Obtiene el saldo de una cuenta por idCuenta.
     */
    @GetMapping("/saldo/{idCuenta}")
    public ApiResponse saldo(@PathVariable Long idCuenta) {
        return atmService.saldo(idCuenta);
    }

    /**
     * Realiza retiro de efectivo. Request esperado en ApiRequest.data: -
     * codigoCajero: String - tarjeta: String - nip: String - montoCentavos:
     * Number (Long/Integer/String numérico)
     */
    @PostMapping("/retirar")
    public ApiResponse retirar(@RequestBody ApiRequest request) {
        return atmService.retirar(request);
    }
}
