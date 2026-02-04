package com.Banco.CajerosService.Service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Banco.CajerosService.DTO.ApiResponse;
import com.Banco.CajerosService.Repository.StoredProcedureExecutor;

/**
 * Servicio de administración de Cajeros
 * ATM Administration Service
 */
@Service
public class CajeroAdminService {

    private final StoredProcedureExecutor spExecutor;

    public CajeroAdminService(StoredProcedureExecutor spExecutor) {
        this.spExecutor = spExecutor;
    }

    /**
     * Recarga un cajero específico con la plantilla estándar
     * 
     * @param codigoCajero Código del cajero (ej: "ATM-001")
     * @return ApiResponse exitosa o con error
     */
    @Transactional
    public ApiResponse recargarCajero(String codigoCajero) {
        try {
            if (codigoCajero == null || codigoCajero.trim().isEmpty()) {
                return ApiResponse.error("Código de cajero requerido");
            }

            spExecutor.recargarCajero(codigoCajero);

            return ApiResponse.ok("Cajero recargado exitosamente");

        } catch (Exception e) {
            return ApiResponse.error(e.getMessage());
        }
    }

    /**
     * Recarga todos los cajeros activos con la plantilla estándar
     * 
     * @return ApiResponse exitosa o con error
     */
    @Transactional
    public ApiResponse recargarTodos() {
        try {
            spExecutor.recargarTodosCajeros();

            return ApiResponse.ok("Todos los cajeros han sido recargados exitosamente");

        } catch (Exception e) {
            return ApiResponse.error(e.getMessage());
        }
    }
}