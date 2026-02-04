package com.Banco.CajerosService.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.Banco.CajerosService.DTO.TarjetaAuthResult;
import com.Banco.CajerosService.DTO.Result;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Ejecutor centralizado de StoredProcedures
 * Centralized StoredProcedure Executor
 */
@Repository
public class StoredProcedureExecutor {

    @Autowired
    private EntityManager entityManager;

    // ==================== AUTENTICACIÓN / AUTHENTICATION ====================

    /**
     * Ejecuta: SP_AUTENTICAR_TARJETA
     * Autentica tarjeta y NIP, devuelve datos de cuenta y usuario
     */
    public TarjetaAuthResult autenticarTarjeta(String numeroTarjeta, String nipValor) {
        try {
            StoredProcedureQuery query = entityManager.createStoredProcedureQuery("SP_AUTENTICAR_TARJETA");

            // Parámetros IN (entrada)
            query.registerStoredProcedureParameter("p_numero_tarjeta", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("p_nip_valor", String.class, ParameterMode.IN);

            // Parámetros OUT (salida)
            query.registerStoredProcedureParameter("o_id_cuenta", Long.class, ParameterMode.OUT);
            query.registerStoredProcedureParameter("o_id_usuario", Long.class, ParameterMode.OUT);
            query.registerStoredProcedureParameter("o_id_rol", Long.class, ParameterMode.OUT);
            query.registerStoredProcedureParameter("o_nombre_rol", String.class, ParameterMode.OUT);

            // Asignar valores
            query.setParameter("p_numero_tarjeta", numeroTarjeta);
            query.setParameter("p_nip_valor", nipValor);

            // Ejecutar
            query.execute();

            // Obtener resultados
            Long idCuenta = (Long) query.getOutputParameterValue("o_id_cuenta");
            Long idUsuario = (Long) query.getOutputParameterValue("o_id_usuario");
            Long idRol = (Long) query.getOutputParameterValue("o_id_rol");
            String nombreRol = (String) query.getOutputParameterValue("o_nombre_rol");

            return new TarjetaAuthResult(idCuenta, idUsuario, idRol, nombreRol);

        } catch (Exception e) {
            throw new RuntimeException("Error en autenticación de tarjeta: " + e.getMessage(), e);
        }
    }

    // ==================== SALDO / BALANCE ====================

    /**
     * Ejecuta: SP_OBTENER_SALDO_CUENTA
     * Obtiene el saldo actual de una cuenta
     */
    public Map<String, Object> obtenerSaldoCuenta(Long idCuenta) {
        try {
            StoredProcedureQuery query = entityManager.createStoredProcedureQuery("SP_OBTENER_SALDO_CUENTA");

            // Parámetros IN
            query.registerStoredProcedureParameter("p_id_cuenta", Long.class, ParameterMode.IN);

            // Parámetros OUT
            query.registerStoredProcedureParameter("o_saldo_centavos", Long.class, ParameterMode.OUT);
            query.registerStoredProcedureParameter("o_estado_cuenta", Long.class, ParameterMode.OUT);

            // Asignar valores
            query.setParameter("p_id_cuenta", idCuenta);

            // Ejecutar
            query.execute();

            // Obtener resultados
            Long saldoCentavos = (Long) query.getOutputParameterValue("o_saldo_centavos");
            Long estadoCuenta = (Long) query.getOutputParameterValue("o_estado_cuenta");

            Map<String, Object> resultado = new HashMap<>();
            resultado.put("saldoCentavos", saldoCentavos);
            resultado.put("estadoCuenta", estadoCuenta);

            return resultado;

        } catch (Exception e) {
            throw new RuntimeException("Error al obtener saldo: " + e.getMessage(), e);
        }
    }

    // ==================== RETIRO / WITHDRAWAL ====================

    /**
     * Ejecuta: SP_RETIRAR
     * Realiza el retiro de efectivo con validaciones y desglose
     */
    public Result retirar(String codigoCajero, String numeroTarjeta,
            String nipValor, Long montoCentavos) {
        try {
            StoredProcedureQuery query = entityManager.createStoredProcedureQuery("SP_RETIRAR");

            // Parámetros IN
            query.registerStoredProcedureParameter("p_codigo_cajero", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("p_numero_tarjeta", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("p_nip_valor", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("p_monto_centavos", Long.class, ParameterMode.IN);

            // Parámetros OUT
            query.registerStoredProcedureParameter("o_saldo_restante_centavos", Long.class, ParameterMode.OUT);
            query.registerStoredProcedureParameter("o_desglose", void.class, ParameterMode.REF_CURSOR);

            // Asignar valores
            query.setParameter("p_codigo_cajero", codigoCajero);
            query.setParameter("p_numero_tarjeta", numeroTarjeta);
            query.setParameter("p_nip_valor", nipValor);
            query.setParameter("p_monto_centavos", montoCentavos);

            // Ejecutar
            query.execute();

            // Obtener saldo restante
            Long saldoRestante = (Long) query.getOutputParameterValue("o_saldo_restante_centavos");

            // Obtener desglose del cursor
            List<Map<String, Object>> desglose = new ArrayList<>();
            try {
                Object desgloseObj = query.getOutputParameterValue("o_desglose");
                
                if (desgloseObj instanceof ResultSet) {
                    ResultSet rs = (ResultSet) desgloseObj;
                    while (rs.next()) {
                        Map<String, Object> map = new HashMap<>();
                        map.put("valorCentavos", rs.getLong("VALOR_CENTAVOS"));
                        map.put("cantidad", rs.getLong("CANTIDAD"));
                        desglose.add(map);
                    }
                    rs.close();
                }
            } catch (Exception e) {
                System.out.println("Nota: Desglose podría estar vacío - " + e.getMessage());
            }

            Result resultado = new Result();
            resultado.setSaldoRestanteCentavos(saldoRestante);
            resultado.setDesglose(desglose);

            return resultado;

        } catch (Exception e) {
            throw new RuntimeException("Error en retiro: " + e.getMessage(), e);
        }
    }

    // ==================== RECARGA / RELOAD ====================

    /**
     * Ejecuta: SP_RECARGAR_CAJERO
     * Recarga un cajero específico con la plantilla estándar
     */
    public void recargarCajero(String codigoCajero) {
        try {
            StoredProcedureQuery query = entityManager.createStoredProcedureQuery("SP_RECARGAR_CAJERO");

            query.registerStoredProcedureParameter("p_codigo_cajero", String.class, ParameterMode.IN);
            query.setParameter("p_codigo_cajero", codigoCajero);

            query.execute();

        } catch (Exception e) {
            throw new RuntimeException("Error al recargar cajero: " + e.getMessage(), e);
        }
    }

    /**
     * Ejecuta: SP_RECARGAR_TODOS_CAJEROS
     * Recarga todos los cajeros activos con la plantilla estándar
     */
    public void recargarTodosCajeros() {
        try {
            StoredProcedureQuery query = entityManager.createStoredProcedureQuery("SP_RECARGAR_TODOS_CAJEROS");
            query.execute();
        } catch (Exception e) {
            throw new RuntimeException("Error al recargar todos los cajeros: " + e.getMessage(), e);
        }
    }

    // ==================== CONSULTAS / QUERIES ====================

    /**
     * Ejecuta: SP_OBTENER_CAJEROS
     * Obtiene lista de todos los cajeros con su saldo
     */
    public List<Map<String, Object>> obtenerCajeros() {
        try {
            StoredProcedureQuery query = entityManager.createStoredProcedureQuery("SP_OBTENER_CAJEROS");
            query.registerStoredProcedureParameter("o_result", void.class, ParameterMode.REF_CURSOR);

            List<Map<String, Object>> cajeros = new ArrayList<>();
            
            Object resultObj = query.getOutputParameterValue("o_result");
            
            if (resultObj instanceof ResultSet) {
                ResultSet rs = (ResultSet) resultObj;
                while (rs.next()) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("idCajero", rs.getLong("ID_CAJERO"));
                    map.put("codigoCajero", rs.getString("CODIGO_CAJERO"));
                    map.put("estado", rs.getLong("ESTADO"));
                    map.put("saldoCentavos", rs.getLong("SALDO_CENTAVOS"));
                    map.put("saldo", rs.getDouble("SALDO"));
                    cajeros.add(map);
                }
                rs.close();
            }

            return cajeros;

        } catch (Exception e) {
            throw new RuntimeException("Error al obtener cajeros: " + e.getMessage(), e);
        }
    }
}