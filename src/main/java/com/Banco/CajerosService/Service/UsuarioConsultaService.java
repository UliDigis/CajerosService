package com.Banco.CajerosService.Service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Banco.CajerosService.DTO.ApiResponse;
import com.Banco.CajerosService.Repository.StoredProcedureExecutor;

/**
 * Servicio de consulta de Usuarios (solo lectura)
 */
@Service
public class UsuarioConsultaService {

    private final StoredProcedureExecutor spExecutor;

    public UsuarioConsultaService(StoredProcedureExecutor spExecutor) {
        this.spExecutor = spExecutor;
    }

    /**
     * Obtiene listado de usuarios vía SP_OBTENER_USUARIOS.
     */
    @Transactional(readOnly = true)
    public ApiResponse obtenerUsuarios() {
        try {
            List<Map<String, Object>> data = spExecutor.obtenerUsuarios();
            return ApiResponse.ok(data);
        } catch (Exception e) {
            return ApiResponse.error(e.getMessage());
        }
    }
}

