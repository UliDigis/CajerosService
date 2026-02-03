package com.Banco.CajerosService.Service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.Banco.CajerosService.DTO.ApiResponse;
import com.Banco.CajerosService.Repository.CajeroConsultaRepository;

@Service
public class CajeroConsultaService {

    private final CajeroConsultaRepository repo;

    public CajeroConsultaService(CajeroConsultaRepository repo) {
        this.repo = repo;
    }

    /**
     * Retorna el listado de cajeros con saldo disponible en un formato dinámico
     * (Map) para evitar DTOs específicos por pantalla.
     */
    public ApiResponse obtenerCajeros() {
        List<Map<String, Object>> data = repo.obtenerCajeros();
        return ApiResponse.ok(data);
    }
}
