package com.Banco.CajerosService.RestController;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Banco.CajerosService.DTO.ApiResponse;
import com.Banco.CajerosService.Service.CajeroAdminService;

/**
 * RestController para administración de Cajeros
 * ATM Admin RestController
 */
@RestController
@RequestMapping("/admin/cajeros")
public class CajeroAdminRestController {

    private final CajeroAdminService cajeroAdminService;

    public CajeroAdminRestController(CajeroAdminService cajeroAdminService) {
        this.cajeroAdminService = cajeroAdminService;
    }

    /**
     * Recarga un cajero específico por CODIGO_CAJERO
     * Ejecuta SP_RECARGAR_CAJERO
     * 
     * @param codigo Código del cajero (ej: "ATM-001")
     * @return ApiResponse exitosa o con error
     */
    @PostMapping("/{codigo}/recargar")
    public ResponseEntity<ApiResponse> recargarCajero(@PathVariable("codigo") String codigo) {
        return ResponseEntity.ok(cajeroAdminService.recargarCajero(codigo));
    }

    /**
     * Recarga todos los cajeros activos
     * Ejecuta SP_RECARGAR_TODOS_CAJEROS
     * 
     * @return ApiResponse exitosa o con error
     */
    @PostMapping("/recargar-todos")
    public ResponseEntity<ApiResponse> recargarTodos() {
        return ResponseEntity.ok(cajeroAdminService.recargarTodos());
    }
}
