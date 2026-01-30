package com.Banco.CajerosService.Service;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Banco.CajerosService.DAO.ICajeroAdminRepository;
import com.Banco.CajerosService.DTO.ApiResponse;

@Service
public class CajeroAdminService {

    private final ICajeroAdminRepository cajeroAdminRepository;

    public CajeroAdminService(ICajeroAdminRepository cajeroAdminRepository) {
        this.cajeroAdminRepository = cajeroAdminRepository;
    }

    /**
     * Recarga un cajero por CODIGO_CAJERO invocando SP_RECARGAR_CAJERO. El SP
     * no hace COMMIT, por eso este método va en transacción para confirmar
     * cambios.
     */
    @Transactional
    public ApiResponse recargarCajero(String codigo) {
        if (codigo == null || codigo.trim().isEmpty()) {
            return ApiResponse.error("Código de cajero requerido");
        }

        cajeroAdminRepository.recargarCajero(codigo.trim());
        return ApiResponse.ok(Map.of("codigo", codigo.trim(), "status", "RECARGADO"));
    }

    /**
     * Recarga todos los cajeros activos invocando SP_RECARGAR_TODOS_CAJEROS. El
     * SP no hace COMMIT, por eso este método va en transacción.
     */
    @Transactional
    public ApiResponse recargarTodos() {
        cajeroAdminRepository.recargarTodos();
        return ApiResponse.ok(Map.of("status", "RECARGA_MASIVA_REALIZADA"));
    }
}
