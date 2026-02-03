package com.Banco.CajerosService.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.Banco.CajerosService.DTO.TarjetaAuthResult;

import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;

@Repository
public class AtmSpExecutor {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Ejecuta SP_AUTENTICAR_TARJETA y retorna los identificadores asociados.
     */
    public TarjetaAuthResult autenticarTarjeta(String numeroTarjeta, String nip) {
        StoredProcedureQuery sp = entityManager.createStoredProcedureQuery("SP_AUTENTICAR_TARJETA");

        sp.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
        sp.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);

        sp.registerStoredProcedureParameter(3, Long.class, ParameterMode.OUT);    // o_id_cuenta
        sp.registerStoredProcedureParameter(4, Long.class, ParameterMode.OUT);    // o_id_usuario
        sp.registerStoredProcedureParameter(5, Long.class, ParameterMode.OUT);    // o_id_rol
        sp.registerStoredProcedureParameter(6, String.class, ParameterMode.OUT);  // o_nombre_rol

        sp.setParameter(1, numeroTarjeta);
        sp.setParameter(2, nip);
        sp.execute();

        Long cuentaId = toLong(sp.getOutputParameterValue(3));
        Long usuarioId = toLong(sp.getOutputParameterValue(4));
        Long rolId = toLong(sp.getOutputParameterValue(5));
        String rolNombre = (String) sp.getOutputParameterValue(6);

        return new TarjetaAuthResult(usuarioId, cuentaId, rolId, rolNombre);
    }

    /**
     * Ejecuta SP_OBTENER_SALDO_CUENTA y retorna saldo/estado en formato
     * dinámico.
     */
    public Map<String, Object> obtenerSaldo(Long idCuenta) {
        StoredProcedureQuery sp = entityManager.createStoredProcedureQuery("SP_OBTENER_SALDO_CUENTA");

        sp.registerStoredProcedureParameter(1, Long.class, ParameterMode.IN);         // p_id_cuenta
        sp.registerStoredProcedureParameter(2, BigDecimal.class, ParameterMode.OUT);  // o_saldo_centavos
        sp.registerStoredProcedureParameter(3, BigDecimal.class, ParameterMode.OUT);  // o_estado_cuenta

        sp.setParameter(1, idCuenta);
        sp.execute();

        BigDecimal saldoCentavos = (BigDecimal) sp.getOutputParameterValue(2);
        BigDecimal estadoCuenta = (BigDecimal) sp.getOutputParameterValue(3);

        long sc = saldoCentavos == null ? 0L : saldoCentavos.longValue();
        int ec = estadoCuenta == null ? 0 : estadoCuenta.intValue();

        return Map.of(
                "idCuenta", idCuenta,
                "estado", ec,
                "saldoCentavos", sc,
                "saldo", BigDecimal.valueOf(sc).movePointLeft(2)
        );
    }

    /**
     * Ejecuta SP_RETIRAR y retorna saldo restante y desglose de denominaciones.
     */
    public Map<String, Object> retirar(String codigoCajero, String tarjeta, String nip, long montoCentavos) {
        StoredProcedureQuery sp = entityManager.createStoredProcedureQuery("SP_RETIRAR");

        sp.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);        // p_codigo_cajero
        sp.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);        // p_numero_tarjeta
        sp.registerStoredProcedureParameter(3, String.class, ParameterMode.IN);        // p_nip_valor
        sp.registerStoredProcedureParameter(4, BigDecimal.class, ParameterMode.IN);    // p_monto_centavos
        sp.registerStoredProcedureParameter(5, BigDecimal.class, ParameterMode.OUT);   // o_saldo_restante_centavos
        sp.registerStoredProcedureParameter(6, void.class, ParameterMode.REF_CURSOR);  // o_desglose

        sp.setParameter(1, codigoCajero);
        sp.setParameter(2, tarjeta);
        sp.setParameter(3, nip);
        sp.setParameter(4, BigDecimal.valueOf(montoCentavos));
        sp.execute();

        BigDecimal saldoRestante = (BigDecimal) sp.getOutputParameterValue(5);
        long saldoRestanteCentavos = saldoRestante == null ? 0L : saldoRestante.longValue();

        @SuppressWarnings("unchecked")
        List<Object[]> rows = sp.getResultList();

        List<Map<String, Object>> desglose = new ArrayList<>();
        for (Object[] r : rows) {
            long valorCentavos = r[0] == null ? 0L : ((Number) r[0]).longValue();
            long cantidad = r[1] == null ? 0L : ((Number) r[1]).longValue();

            Map<String, Object> item = new LinkedHashMap<>();
            item.put("valorCentavos", valorCentavos);
            item.put("valor", BigDecimal.valueOf(valorCentavos).movePointLeft(2));
            item.put("cantidad", cantidad);
            desglose.add(item);
        }

        return Map.of(
                "saldoRestanteCentavos", saldoRestanteCentavos,
                "saldoRestante", BigDecimal.valueOf(saldoRestanteCentavos).movePointLeft(2),
                "desglose", desglose
        );
    }

    private Long toLong(Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof Number n) {
            return n.longValue();
        }
        return Long.valueOf(value.toString());
    }
}
