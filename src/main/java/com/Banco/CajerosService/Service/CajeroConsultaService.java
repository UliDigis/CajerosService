package com.Banco.CajerosService.Service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Banco.CajerosService.DTO.ApiResponse;
import com.Banco.CajerosService.Repository.StoredProcedureExecutor;

/**
 * Servicio de consulta de Cajeros (solo lectura)
 * ATM Consultation Service (read-only)
 */
@Service
public class CajeroConsultaService {

    private final StoredProcedureExecutor spExecutor;

    public CajeroConsultaService(StoredProcedureExecutor spExecutor) {
        this.spExecutor = spExecutor;
    }

    /**
     * Obtiene listado de todos los cajeros con saldo disponible
     * Retorna listado de cajeros consultando el SP.
     * 
     * @return ApiResponse con lista de cajeros
     */
    @Transactional(readOnly = true)  // ← Consultas usan readOnly = true
    public ApiResponse obtenerCajeros() {
        try {
            List<Map<String, Object>> data = spExecutor.obtenerCajeros();
            return ApiResponse.ok(data);
        } catch (Exception e) {
            return ApiResponse.error(e.getMessage());
        }
    }
}
