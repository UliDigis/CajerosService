package com.Banco.CajerosService.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;

@Repository
public class RetiroRepository {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Ejecuta SP_RETIRAR. Retorna saldo restante (centavos) y desglose
     * (valor_centavos, cantidad).
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
}
