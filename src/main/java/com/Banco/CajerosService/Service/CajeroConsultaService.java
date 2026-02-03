package com.Banco.CajerosService.Service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.Banco.CajerosService.DTO.ApiResponse;
import com.Banco.CajerosService.Repository.CajeroSpExecutor;

@Service
public class CajeroConsultaService {

    private final CajeroSpExecutor cajeroSp;

    public CajeroConsultaService(CajeroSpExecutor cajeroSp) {
        this.cajeroSp = cajeroSp;
    }

    /**
     * Retorna listado de cajeros consultando el SP.
     */
    public ApiResponse obtenerCajeros() {
        List<Map<String, Object>> data = cajeroSp.obtenerCajeros();
        return ApiResponse.ok(data);
    }
}
