package com.Banco.CajerosService.RestController;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Banco.CajerosService.DTO.ApiResponse;
import com.Banco.CajerosService.Service.CajeroAdminService;

@RestController
@RequestMapping("/admin/cajeros")
public class CajeroAdminRestController {

    private final CajeroAdminService cajeroAdminService;

    public CajeroAdminRestController(CajeroAdminService cajeroAdminService) {
        this.cajeroAdminService = cajeroAdminService;
    }

    /**
     * Recarga un cajero específico por CODIGO_CAJERO (SP_RECARGAR_CAJERO).
     */
    @PostMapping("/{codigo}/recargar")
    public ResponseEntity<ApiResponse> recargarCajero(@PathVariable("codigo") String codigo) {
        return ResponseEntity.ok(cajeroAdminService.recargarCajero(codigo));
    }

    /**
     * Recarga todos los cajeros activos (SP_RECARGAR_TODOS_CAJEROS).
     */
    @PostMapping("/recargar-todos")
    public ResponseEntity<ApiResponse> recargarTodos() {
        return ResponseEntity.ok(cajeroAdminService.recargarTodos());
    }
}
