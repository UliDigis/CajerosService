package com.Banco.CajerosService.DAO;

import com.Banco.CajerosService.DTO.TarjetaAuthResult;
import java.sql.CallableStatement;
import java.sql.Types;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TarjetaAuthDAO {

    private final JdbcTemplate jdbcTemplate;

    public TarjetaAuthDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public TarjetaAuthResult autenticarTarjeta(String numeroTarjeta, String nip) {
        try {
            return jdbcTemplate.execute(con -> {
                
                CallableStatement cs = con.prepareCall("{ call SP_AUTENTICAR_TARJETA(?, ?, ?, ?, ?, ?) }");
                cs.setString(1, numeroTarjeta);
                cs.setString(2, nip);

                cs.registerOutParameter(3, Types.NUMERIC); // p_id_usuario
                cs.registerOutParameter(4, Types.NUMERIC); // p_id_cuenta
                cs.registerOutParameter(5, Types.NUMERIC); // p_id_rol
                cs.registerOutParameter(6, Types.VARCHAR); // p_nombre_rol
                return cs;
            }, (CallableStatement cs) -> {
                cs.execute();

                Long userId = cs.getLong(3);
                Long cuentaId = cs.getLong(4);
                Long rolId = cs.getLong(5);
                String rolNombre = cs.getString(6);

                return new TarjetaAuthResult(userId, cuentaId, rolId, rolNombre);
            });

        } catch (DataAccessException ex) {
            
            throw new RuntimeException("Tarjeta/NIP inválidos o tarjeta/cuenta inactiva");
        }
    }
}
