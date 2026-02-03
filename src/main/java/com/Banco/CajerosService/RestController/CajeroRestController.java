package com.Banco.CajerosService.RestController;

import org.springframework.web.bind.annotation.*;

import com.Banco.CajerosService.DTO.ApiResponse;
import com.Banco.CajerosService.Service.CajeroConsultaService;

@RestController
@RequestMapping("/cajeros")
public class CajeroRestController {

    private final CajeroConsultaService cajeroService;

    public CajeroRestController(CajeroConsultaService cajeroService) {
        this.cajeroService = cajeroService;
    }

    /**
     * Retorna el listado de cajeros vía SP_OBTENER_CAJEROS.
     */
    @GetMapping
    public ApiResponse obtenerCajeros() {
        return cajeroService.obtenerCajeros();
    }
}
