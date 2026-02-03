package com.Banco.CajerosService.Repository;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;

@Repository
public class CuentaSaldoRepository {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Ejecuta SP_OBTENER_SALDO_CUENTA y retorna saldo en centavos.
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
}
