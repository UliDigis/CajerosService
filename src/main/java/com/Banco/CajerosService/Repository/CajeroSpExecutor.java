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
public class CajeroSpExecutor {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Ejecuta SP_OBTENER_CAJEROS y retorna una lista de mapas para evitar DTOs
     * específicos.
     */
    public List<Map<String, Object>> obtenerCajeros() {
        StoredProcedureQuery sp = entityManager.createStoredProcedureQuery("SP_OBTENER_CAJEROS");
        sp.registerStoredProcedureParameter("o_result", void.class, ParameterMode.REF_CURSOR);
        sp.execute();

        @SuppressWarnings("unchecked")
        List<Object[]> rows = sp.getResultList();

        List<Map<String, Object>> data = new ArrayList<>();
        for (Object[] r : rows) {
            Map<String, Object> item = new LinkedHashMap<>();
            item.put("idCajero", toLong(r[0]));
            item.put("codigoCajero", r[1] == null ? null : String.valueOf(r[1]));
            item.put("estado", toInteger(r[2]));
            item.put("saldoCentavos", r[3] == null ? 0L : toLong(r[3]));
            item.put("saldo", toBigDecimal(r[4]));
            data.add(item);
        }

        return data;
    }

    private Long toLong(Object v) {
        if (v == null) {
            return null;
        }
        return ((Number) v).longValue();
    }

    private Integer toInteger(Object v) {
        if (v == null) {
            return null;
        }
        return ((Number) v).intValue();
    }

    private BigDecimal toBigDecimal(Object v) {
        if (v == null) {
            return BigDecimal.ZERO;
        }
        if (v instanceof BigDecimal) {
            return (BigDecimal) v;
        }
        return new BigDecimal(v.toString());
    }
}
