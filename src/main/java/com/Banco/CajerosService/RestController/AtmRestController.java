package com.Banco.CajerosService.RestController;

import org.springframework.web.bind.annotation.*;

import com.Banco.CajerosService.DTO.ApiRequest;
import com.Banco.CajerosService.DTO.ApiResponse;
import com.Banco.CajerosService.Service.AtmOperacionService;
import java.util.Map;
import org.springframework.security.core.Authentication;

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
    public ApiResponse saldo(@PathVariable Long idCuenta, Authentication auth) {
        return atmService.saldo(idCuenta, getCuentaId(auth));
    }

    /**
     * Realiza retiro de efectivo. Request esperado en ApiRequest.data: -
     * codigoCajero: String - tarjeta: String - nip: String - montoCentavos:
     * Number (Long/Integer/String numérico)
     */
    @PostMapping("/retirar")
    public ApiResponse retirar(@RequestBody ApiRequest request, Authentication auth) {
        return atmService.retirar(request, getCuentaId(auth));
    }

    private Long getCuentaId(Authentication auth) {
        if (auth == null) {
            return null;
        }
        Object details = auth.getDetails();
        if (!(details instanceof Map)) {
            return null;
        }
        Object cuentaObj = ((Map<?, ?>) details).get("cuentaId");
        if (cuentaObj instanceof Number) {
            return ((Number) cuentaObj).longValue();
        }
        return null;
    }
}
