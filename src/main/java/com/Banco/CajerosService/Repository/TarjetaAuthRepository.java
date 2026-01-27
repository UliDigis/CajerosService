package com.Banco.CajerosService.Repository;

import com.Banco.CajerosService.DTO.TarjetaAuthResult;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.stereotype.Repository;

@Repository
public class TarjetaAuthRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public TarjetaAuthResult autenticarTarjeta(String numeroTarjeta, String nip) {

        StoredProcedureQuery sp = entityManager.createStoredProcedureQuery("SP_AUTENTICAR_TARJETA");

        // IN
        sp.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
        sp.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);

        // OUT (ORDEN REAL DEL SP)
        sp.registerStoredProcedureParameter(3, Long.class, ParameterMode.OUT);    // o_id_cuenta
        sp.registerStoredProcedureParameter(4, Long.class, ParameterMode.OUT);    // o_id_usuario
        sp.registerStoredProcedureParameter(5, Long.class, ParameterMode.OUT);    // o_id_rol
        sp.registerStoredProcedureParameter(6, String.class, ParameterMode.OUT);  // o_nombre_rol

        sp.setParameter(1, numeroTarjeta);
        sp.setParameter(2, nip);

        try {
            sp.execute();

            Long cuentaId = toLong(sp.getOutputParameterValue(3));
            Long usuarioId = toLong(sp.getOutputParameterValue(4));
            Long rolId = toLong(sp.getOutputParameterValue(5));
            String rolNombre = (String) sp.getOutputParameterValue(6);

            return new TarjetaAuthResult(usuarioId, cuentaId, rolId, rolNombre);

        } catch (Exception e) {
            throw new RuntimeException("Tarjeta/NIP inválidos o tarjeta/cuenta inactiva");
        }
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
