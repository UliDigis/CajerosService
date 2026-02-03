package com.Banco.CajerosService.Service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.Banco.CajerosService.DTO.ApiRequest;
import com.Banco.CajerosService.DTO.ApiResponse;
import com.Banco.CajerosService.DTO.TarjetaAuthResult;
import com.Banco.CajerosService.Repository.CuentaSaldoRepository;
import com.Banco.CajerosService.Repository.RetiroRepository;
import com.Banco.CajerosService.Repository.TarjetaAuthRepository;

@Service
public class AtmOperacionService {

    private final TarjetaAuthRepository tarjetaAuthRepository;
    private final CuentaSaldoRepository cuentaSaldoRepository;
    private final RetiroRepository retiroRepository;

    public AtmOperacionService(TarjetaAuthRepository tarjetaAuthRepository,
            CuentaSaldoRepository cuentaSaldoRepository,
            RetiroRepository retiroRepository) {
        this.tarjetaAuthRepository = tarjetaAuthRepository;
        this.cuentaSaldoRepository = cuentaSaldoRepository;
        this.retiroRepository = retiroRepository;
    }

    /**
     * Autentica tarjeta y nip vía SP_AUTENTICAR_TARJETA.
     */
    public ApiResponse autenticar(ApiRequest request) {
        Map<String, Object> data = request.getData();

        String tarjeta = (String) data.get("tarjeta");
        String nip = (String) data.get("nip");

        if (tarjeta == null || nip == null) {
            throw new RuntimeException("Parámetros requeridos");
        }

        TarjetaAuthResult r = tarjetaAuthRepository.autenticarTarjeta(tarjeta, nip);

        return ApiResponse.ok(Map.of(
                "idCuenta", r.getCuentaId(),
                "idUsuario", r.getUsurioId(),
                "idRol", r.getRolId(),
                "rol", r.getRolNombre()
        ));
    }

    /**
     * Obtiene saldo vía SP_OBTENER_SALDO_CUENTA.
     */
    public ApiResponse saldo(Long idCuenta) {
        return ApiResponse.ok(cuentaSaldoRepository.obtenerSaldo(idCuenta));
    }

    /**
     * Retira efectivo vía SP_RETIRAR.
     */
    public ApiResponse retirar(ApiRequest request) {
        Map<String, Object> data = request.getData();

        String codigoCajero = (String) data.get("codigoCajero");
        String tarjeta = (String) data.get("tarjeta");
        String nip = (String) data.get("nip");

        Object montoCentavosRaw = data.get("montoCentavos");
        Long montoCentavos = montoCentavosRaw == null ? null : Long.valueOf(montoCentavosRaw.toString());

        if (codigoCajero == null || tarjeta == null || nip == null || montoCentavos == null) {
            throw new RuntimeException("Parámetros requeridos");
        }

        Map<String, Object> r = retiroRepository.retirar(codigoCajero, tarjeta, nip, montoCentavos);

        return ApiResponse.ok(Map.of(
                "codigoCajero", codigoCajero,
                "montoCentavos", montoCentavos,
                "monto", java.math.BigDecimal.valueOf(montoCentavos).movePointLeft(2),
                "saldoRestanteCentavos", r.get("saldoRestanteCentavos"),
                "saldoRestante", r.get("saldoRestante"),
                "desglose", r.get("desglose")
        ));
    }
}
