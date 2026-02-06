package com.Banco.CajerosService.Service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Banco.CajerosService.DAO.AuthDAO;
import com.Banco.CajerosService.DTO.ApiRequest;
import com.Banco.CajerosService.DTO.ApiResponse;
import com.Banco.CajerosService.DTO.Result;
import com.Banco.CajerosService.DTO.TarjetaAuthResult;
import com.Banco.CajerosService.JPA.UsuarioJPA;
import com.Banco.CajerosService.Repository.StoredProcedureExecutor;

import java.util.Map;

/**
 * Servicio de operaciones ATM
 * ATM Operations Service
 */
@Service
public class AtmOperacionService {

    private final StoredProcedureExecutor spExecutor;
    private final JwtService jwtService;
    private final AuthDAO authDAO;

    public AtmOperacionService(StoredProcedureExecutor spExecutor, JwtService jwtService, AuthDAO authDAO) {
        this.spExecutor = spExecutor;
        this.jwtService = jwtService;
        this.authDAO = authDAO;
    }

    /**
     * Autentica tarjeta con NIP encriptado
     * 
     * @param request Debe contener: tarjeta (String), nip (String)
     * @return ApiResponse con datos de autenticación
     */
    @Transactional(readOnly = true)
    public ApiResponse autenticar(ApiRequest request) {
        try {
            Map<String, Object> data = request.getData();

            String numeroTarjeta = (String) data.get("tarjeta");
            String nipPlano = (String) data.get("nip");

            if (numeroTarjeta == null || nipPlano == null) {
                return ApiResponse.error("Tarjeta y NIP requeridos");
            }

            // El SP valida el NIP, NO lo hacemos aquí
            TarjetaAuthResult resultado = spExecutor.autenticarTarjeta(numeroTarjeta, nipPlano);

            String nombre = null;
            UsuarioJPA usuario = authDAO.findUsuarioActivoById(resultado.getUsuarioId());
            if (usuario != null) {
                nombre = (usuario.getNombres() + " " + usuario.getApellidos()).trim();
            }

            String token = jwtService.generateToken(
                    resultado.getUsuarioId(),
                    resultado.getRolNombre(),
                    resultado.getCuentaId(),
                    nombre
            );

            Map<String, Object> responseData = new java.util.HashMap<>();
            responseData.put("token", token);
            responseData.put("usuarioId", resultado.getUsuarioId());
            responseData.put("cuentaId", resultado.getCuentaId());
            responseData.put("rolId", resultado.getRolId());
            responseData.put("rolNombre", resultado.getRolNombre());
            responseData.put("nombre", nombre);

            return ApiResponse.ok(responseData);

        } catch (Exception e) {
            return ApiResponse.error(e.getMessage());
        }
    }

    /**
     * Obtiene saldo de la cuenta
     * 
     * @param idCuenta ID de la cuenta
     * @return ApiResponse con saldo en centavos
     */
    @Transactional(readOnly = true)
    public ApiResponse saldo(Long idCuenta, Long tokenCuentaId) {
        try {
            if (tokenCuentaId == null) {
                return ApiResponse.error("Token sin cuenta. Autentique con tarjeta.");
            }
            if (idCuenta == null || !tokenCuentaId.equals(idCuenta)) {
                return ApiResponse.error("Acceso denegado a la cuenta solicitada.");
            }
            Map<String, Object> resultado = spExecutor.obtenerSaldoCuenta(idCuenta);
            return ApiResponse.ok(resultado);
        } catch (Exception e) {
            return ApiResponse.error(e.getMessage());
        }
    }

    /**
     * Realiza retiro de efectivo
     * 
     * @param request Debe contener: codigoCajero, tarjeta, nip, montoCentavos
     * @return ApiResponse con saldo restante y desglose de billetes
     */
    @Transactional // ← IMPORTANTE: Retiros requieren transacción
    public ApiResponse retirar(ApiRequest request, Long tokenCuentaId) {
        try {
            Map<String, Object> data = request.getData();

            String codigoCajero = (String) data.get("codigoCajero");
            String numeroTarjeta = (String) data.get("tarjeta");
            String nipPlano = (String) data.get("nip");
            Object montoObj = data.get("montoCentavos");
            Long montoCentavos = null;

            if (montoObj instanceof Number) {
                montoCentavos = ((Number) montoObj).longValue();
            } else if (montoObj instanceof String) {
                String montoStr = ((String) montoObj).trim();
                if (!montoStr.isEmpty()) {
                    montoCentavos = Long.parseLong(montoStr);
                }
            }

            if (codigoCajero == null || numeroTarjeta == null || nipPlano == null || montoCentavos == null) {
                return ApiResponse.error("Parámetros incompletos para retiro");
            }

            if (tokenCuentaId == null) {
                return ApiResponse.error("Token sin cuenta. Autentique con tarjeta.");
            }

            TarjetaAuthResult authResult = spExecutor.autenticarTarjeta(numeroTarjeta, nipPlano);
            if (authResult.getCuentaId() == null || !tokenCuentaId.equals(authResult.getCuentaId())) {
                return ApiResponse.error("Acceso denegado a la cuenta de la tarjeta.");
            }

            // Ejecuta el SP que hace toda la validación
            Result resultado = spExecutor.retirar(codigoCajero, numeroTarjeta, nipPlano, montoCentavos);

            // Crear respuesta final con estructura correcta
            Map<String, Object> responseData = new java.util.HashMap<>();
            responseData.put("saldoRestanteCentavos", resultado.getSaldoRestanteCentavos());
            responseData.put("desglose", resultado.getDesglose());

            return ApiResponse.ok(responseData);

        } catch (Exception e) {
            // Si falla, se hace ROLLBACK automático
            return ApiResponse.error(e.getMessage());
        }
    }
}
