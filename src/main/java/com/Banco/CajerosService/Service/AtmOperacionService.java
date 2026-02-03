package com.Banco.CajerosService.Service;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.Banco.CajerosService.DTO.ApiRequest;
import com.Banco.CajerosService.DTO.ApiResponse;
import com.Banco.CajerosService.DTO.TarjetaAuthResult;
import com.Banco.CajerosService.Repository.AtmSpExecutor;

@Service
public class AtmOperacionService {

    private final AtmSpExecutor atmSp;

    public AtmOperacionService(AtmSpExecutor atmSp) {
        this.atmSp = atmSp;
    }

    /**
     * Autentica tarjeta con SP_AUTENTICAR_TARJETA.
     */
    public ApiResponse autenticar(ApiRequest request) {
        Map<String, Object> data = request.getData();

        String tarjeta = data.get("tarjeta") == null ? null : data.get("tarjeta").toString();
        String nip = data.get("nip") == null ? null : data.get("nip").toString();

        if (tarjeta == null || nip == null) {
            throw new IllegalArgumentException("Parámetros requeridos: tarjeta, nip");
        }

        TarjetaAuthResult r = atmSp.autenticarTarjeta(tarjeta, nip);

        return ApiResponse.ok(Map.of(
                "idCuenta", r.getCuentaId(),
                "idUsuario", r.getUsurioId(),
                "idRol", r.getRolId(),
                "rol", r.getRolNombre()
        ));
    }

    /**
     * Obtiene saldo con SP_OBTENER_SALDO_CUENTA.
     */
    public ApiResponse saldo(Long idCuenta) {
        return ApiResponse.ok(atmSp.obtenerSaldo(idCuenta));
    }

    /**
     * Retira efectivo con SP_RETIRAR.
     */
    public ApiResponse retirar(ApiRequest request) {
        Map<String, Object> data = request.getData();

        String codigoCajero = data.get("codigoCajero") == null ? null : data.get("codigoCajero").toString();
        String tarjeta = data.get("tarjeta") == null ? null : data.get("tarjeta").toString();
        String nip = data.get("nip") == null ? null : data.get("nip").toString();

        Object montoRaw = data.get("montoCentavos");
        Long montoCentavos = montoRaw == null ? null : Long.valueOf(montoRaw.toString());

        if (codigoCajero == null || tarjeta == null || nip == null || montoCentavos == null) {
            throw new IllegalArgumentException("Parámetros requeridos: codigoCajero, tarjeta, nip, montoCentavos");
        }

        Map<String, Object> r = atmSp.retirar(codigoCajero, tarjeta, nip, montoCentavos);

        return ApiResponse.ok(Map.of(
                "codigoCajero", codigoCajero,
                "montoCentavos", montoCentavos,
                "monto", BigDecimal.valueOf(montoCentavos).movePointLeft(2),
                "saldoRestanteCentavos", r.get("saldoRestanteCentavos"),
                "saldoRestante", r.get("saldoRestante"),
                "desglose", r.get("desglose")
        ));
    }
}
